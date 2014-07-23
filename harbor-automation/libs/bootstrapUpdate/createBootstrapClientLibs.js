module.exports = function ( grunt , initialRepoPath , clientLibsRoot , baseClientLibsCatagory , jqueryCategory) {

    var fs                  = require("fs");
    var _                   = require("underscore");
    var LineByLineReader    = require( "line-by-line" );
    var Q                   = require('q');
    var chalk               = require("chalk");
    var deferred            = Q.defer();
    var lessPath            = initialRepoPath + "/less";
    var jsPath              = initialRepoPath + "/js";
    var templateDir         = __dirname + "/templates/clientlibsTemplate/";
    var ln                  = new LineByLineReader( lessPath + "/bootstrap.less" );
    var libs                = [];

    ln.on("line", function(line){

        var fileNameRegex  = /\@import\ \"(.*?)\;/g


        var varFileNameMatch  = line.match(fileNameRegex);

        if( varFileNameMatch ){

            var clientLibExists = grunt.file.isDir( clientLibsRoot );
            var name            = varFileNameMatch[0].substr(9, varFileNameMatch[0].lastIndexOf('"') - 9 ).trim();


            if( clientLibExists ){

                var nameWithoutExtension    = name.substr( 0 , name.length - 5 );
                var clientLibsPath          = clientLibsRoot + "/" + nameWithoutExtension;
                var deps                    = [ baseClientLibsCatagory + ".bootstrap.variables" , baseClientLibsCatagory + ".bootstrap.mixins" ];
                var depsString              = "[" + deps.join() + "]";
                var cssFileList             = [ name ];

                if( nameWithoutExtension == "variables" ){
                    depsString = "";
                } else if( nameWithoutExtension == "mixins" ){
                    depsString = baseClientLibsCatagory + ".bootstrap.variables";
                    cssFileList = processMixins( grunt , lessPath , clientLibsPath , name);
                }

                var clientLibsDataOptions = {
                    dependencies            : depsString,
                    clientLibsCategories    : baseClientLibsCatagory + ".bootstrap." + nameWithoutExtension
                };

                grunt.file.mkdir( clientLibsPath );
                grunt.file.mkdir( clientLibsPath + "/" + "css" );
                grunt.file.mkdir( clientLibsPath + "/" + "js" );

                grunt.file.copy( templateDir + "emptyFolder.content.xml" , clientLibsPath + "/" + "css/.content.xml" );
                grunt.file.copy( templateDir + "emptyFolder.content.xml" , clientLibsPath + "/" + "js/.content.xml" );
                grunt.file.copy( lessPath + "/" + name , clientLibsPath + "/" + "css/" + name );

                var clientLibsContentDotXml = grunt.template.process( grunt.file.read( templateDir + "_clientlibs.content.xml" ) , { data: clientLibsDataOptions } );
                var cssDotTxt               = grunt.template.process( grunt.file.read( templateDir + "_css.txt" ) , { data : { cssFileList : cssFileList.join( "\n" ) } } );

                grunt.file.write( clientLibsPath + "/.content.xml" , clientLibsContentDotXml );
                grunt.file.write( clientLibsPath + "/css.txt" , cssDotTxt );

                libs.push( nameWithoutExtension );

            } else {

                throw chalk.bgRed.black.bold("\n  Please specify, in the package.json, a client library root folder that exists!  \n");

            }

        }





    });

    ln.on("end", function(){

        libs = processJS( grunt , jsPath , clientLibsRoot , libs , templateDir , baseClientLibsCatagory , jqueryCategory , libs);


        console.log( chalk.green.bold( "Created " + libs.length + " libraries" ) );
        console.log( chalk.green(" --- under the category " + baseClientLibsCatagory + ".bootstrap" ) );
        console.log( chalk.green( " ------ " + baseClientLibsCatagory + ".bootstrap." + libs.join( "\n ------ " + baseClientLibsCatagory + ".bootstrap." ) ) );



        deferred.resolve(  );

    });



    return deferred.promise;

};

function processMixins ( grunt , lessPath , clientLibsPath ) {

    var LineByLineReader = require("line-by-line");
    var mixinsLn = new LineByLineReader(lessPath + "/mixins.less");
    var cssList = [];
    var _ = require("underscore");


    var text = grunt.file.read(lessPath + "/mixins.less");


    _.each( text.match(/^.*((\r\n|\n|\r)|$)/gm) , function( line , index ){

        var fileNameRegex  = /\@import\ \"(.*?)\;/g;
        var varFileNameMatch  = line.match(fileNameRegex);

        if( varFileNameMatch ) {

            var name = varFileNameMatch[0].substr(9, varFileNameMatch[0].lastIndexOf('"') - 9).trim();

            cssList.push(name.substr(7).trim());

            grunt.file.copy(lessPath + "/" + name, clientLibsPath + "/" + "css/" + name.substr(7).trim());

        }

    } );

    return cssList;

}

function processJS ( grunt , jsPath , clientLibsRoot , cssLibs , templateDir , baseClientLibsCatagory , jqueryCategory , libs){

    var fs = require( "fs" );
    var _  = require( "underscore" );

    var jsFiles = fs.readdirSync( jsPath );

    _.each( jsFiles , function( jsFile , index ){

        if( jsFile != ".jscsrc" && jsFile != ".jshintrc" && jsFile != "tests" ){

            var foundAExistingLib = false;

            _.each( cssLibs , function( cssLib , index ) {

                var cssLibName = cssLib.substr( 0 , cssLib.length - 1 );
                var jsFileName = jsFile.substr( 0 , jsFile.length - 3);

                //console.log( cssLibName , jsFileName , "\n" );

                if( cssLibName == jsFile.substr( 0 , jsFile.length - 3 ) ){

                    foundAExistingLib = true;

                    grunt.file.copy( jsPath + "/" + jsFile , clientLibsRoot + "/" + cssLib + "/js/" + jsFile );

                    var jsDotTxt = grunt.template.process( grunt.file.read( templateDir + "_js.txt" ) , { data : { jsFileList : jsFile } } );

                    grunt.file.write( clientLibsRoot + "/" + cssLib + "/js.txt" , jsDotTxt );

                }

            } )

            if( !foundAExistingLib ){

                var jsFileName      = jsFile.substr( 0 , jsFile.length - 3);
                var clientLibsPath  = clientLibsRoot + "/" + jsFileName;

                grunt.file.mkdir( clientLibsPath );
                grunt.file.mkdir( clientLibsPath + "/" + "js" );

                grunt.file.copy( templateDir + "emptyFolder.content.xml" , clientLibsPath + "/" + "js/.content.xml" );
                grunt.file.copy( jsPath + "/" + jsFile , clientLibsPath + "/" + "js/" + jsFile );

                var clientLibsDataOptions = {
                    dependencies            : "[" + jqueryCategory + "]",
                    clientLibsCategories    : baseClientLibsCatagory + ".bootstrap." + jsFileName
                };

                var clientLibsContentDotXml = grunt.template.process( grunt.file.read( templateDir + "_clientlibs.content.xml" ) , { data: clientLibsDataOptions } );

                grunt.file.write( clientLibsPath + "/.content.xml" , clientLibsContentDotXml );

                libs.push( jsFileName );

            }

        }

    } );



    return libs;

}