module.exports = function( baseDir , repositoriesPath , tag ) {

    var shell               = require("shelljs");
    var chalk               = require("chalk");
    var Q                   = require('q');
    var fs                  = require("fs");
    var deferred            = Q.defer();


    var bootstrapRepoExists = fs.existsSync(baseDir + "/" + repositoriesPath + "/bootstrap");


    if( bootstrapRepoExists ){
        shell.cd(repositoriesPath);
        shell.cd("bootstrap");
        shell.exec("git fetch --tags");
        shell.exec("git checkout " + tag);

        deferred.resolve( );
    }else{

        shell.mkdir( repositoriesPath );

        shell.cd( repositoriesPath );

        console.log( chalk.green("About to download bootstrap lean back and grab a coffee") );
        shell.exec("git clone https://github.com/twbs/bootstrap.git");

        deferred.resolve( );
    }

    return deferred.promise;
}
