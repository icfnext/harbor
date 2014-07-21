module.exports = function( baseDir , repositoriesPath , filename ){

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

        console.log( chalk.green( "Found " + lessVars.length + " less variables." ) + "\n" );

        var varsJSON = JSON.stringify(lessVars, null, 4);

        fs.writeFileSync( baseDir + "/" + repositoriesPath + "/bootstrap/vars.json", varsJSON );



        deferred.resolve( varsJSON );

    });

    return deferred.promise;
};