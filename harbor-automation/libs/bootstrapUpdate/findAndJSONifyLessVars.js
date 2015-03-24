module.exports = function( baseDir , repositoriesPath , filename , variableLibPath, tag){

    var chalk               = require( "chalk" );
    var LineByLineReader    = require( "line-by-line" );
    var fs                  = require( "fs" );
    var Q                   = require( "q" );

    var ln                  = new LineByLineReader( filename );
    var lessVars            = [];
    var deferred            = Q.defer();


    console.log( "\n" + chalk.green.bold('Grabbing Bootstrap Less Variables'));

    ln.on("line", function(line){

        var varNameRegex  = /\@(.*?)\:/g
        var varValueRegex = /\:(.*?)\;/g

        var varNameMatch  = line.match(varNameRegex);
        var varValueMatch = line.match(varValueRegex);

        if( varNameMatch ){
            var name = varNameMatch[0].substr(0, varNameMatch[0].indexOf(":")).trim();
        }

        if( varValueMatch ){
            var value = varValueMatch[0].substr(1, varValueMatch[0].indexOf(";")-1).trim();
        }

        if( value && name ){

            lessVars.push( { "name" : name , "value" : value } );

        }

    });

    ln.on("end", function(){

        var harborBrandVars = "/** \n * Variables for bootstrap " + tag + " \n */ \n\n";

        console.log( chalk.green( "Found " + lessVars.length + " less variables." ) + "\n" );

        var varsJSON = JSON.stringify(lessVars, null, 4);

        fs.writeFileSync( baseDir + "/" + repositoriesPath + "/bootstrap/vars.json", varsJSON );

        for( var i in lessVars ){
            var lessVar = lessVars[i];
            harborBrandVars += lessVar.name + " : <% " + lessVar.name + " %>; \n";
        }

        fs.writeFileSync( variableLibPath , harborBrandVars );

        console.log( chalk.green( "Created new harbor brand vars file here:" ) );
        console.log( chalk.gray( variableLibPath  ) );

        deferred.resolve( varsJSON );

    });

    return deferred.promise;
};
