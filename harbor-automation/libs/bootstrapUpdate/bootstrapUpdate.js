module.exports = function( grunt , task , tag , baseDir ){

    var shell            = require("shelljs");
    var fs               = require("fs");
    var chalk            = require("chalk");
    var LineByLineReader = require("line-by-line");


    var bootstrapPath     = grunt.config.data.pkg.options.clientlibsPaths.bootstrap;
    var repositoriesPath  = grunt.config.data.pkg.options.repositoriesPath;
    var done = task.async();

    shell.cd(repositoriesPath);

    var bootstrapRepoExists = fs.existsSync(baseDir + "/" + repositoriesPath + "/bootstrap");

    if( bootstrapRepoExists ){
        shell.cd("bootstrap");
        shell.exec("git fetch --tags");
        shell.exec("git checkout " + tag)
    }else{
        console.log( chalk.green("About to download bootstrap lean back and grab a coffee") );
        shell.exec("git clone https://github.com/twbs/bootstrap.git");
    }





    var filename = baseDir + "/" + repositoriesPath + "/bootstrap/less/variables.less";

    var ln = new LineByLineReader( filename );
    var lessVars = [];

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

        fs.writeFileSync(baseDir + "/" + repositoriesPath + "/bootstrap/vars.json", varsJSON);

        done();
    });
}