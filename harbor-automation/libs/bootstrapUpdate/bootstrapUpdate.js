module.exports = function( tag , baseDir ){

    var config                      = require('./../../config');

    var harborLessVarsPath          = config.harborLessVarsPath;
    var repositoriesPath            = config.repositoriesPath;
    var lessVarsFileName            = baseDir + "/" + repositoriesPath + "/bootstrap/less/variables.less";

    var checkOutBootstrapTag        = require( "./checkOutBootstrapTag.js" );
    var JSONifyLessVars             = require( "./findAndJSONifyLessVars.js" );
    var Q                           = require( "q" );


    var checkOutBootstrapTagFinished    = checkOutBootstrapTag( baseDir , repositoriesPath , tag );
    var jsonIfyLessVars                 = JSONifyLessVars( baseDir , repositoriesPath , lessVarsFileName , harborLessVarsPath, tag);



    Q.all( [ checkOutBootstrapTagFinished , jsonIfyLessVars ] ).spread(function (jsonIfyLessVarsResult ) {

        // write less file out

    });

};
