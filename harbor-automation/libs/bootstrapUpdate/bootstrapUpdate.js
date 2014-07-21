module.exports = function( grunt , task , tag , baseDir ){

    var bootstrapPath               = grunt.config.data.pkg.options.clientlibsPaths.bootstrap;
    var repositoriesPath            = grunt.config.data.pkg.options.repositoriesPath;
    var baseClientLibsCategory      = grunt.config.data.pkg.options.baseCategory;
    var jqueryCategory              = grunt.config.data.pkg.options.jqueryCategory;
    var lessVarsFileName            = baseDir + "/" + repositoriesPath + "/bootstrap/less/variables.less";

    var checkOutBootstrapTag        = require( "./checkOutBootstrapTag.js" );
    var JSONifyLessVars             = require( "./findAndJSONifyLessVars.js" );
    var createBootstrapClientLibs   = require( "./createBootstrapClientLibs.js" );
    var Q                           = require( "q" );
    var done                        = task.async( );


    var checkOutBootstrapTagFinished    = checkOutBootstrapTag( baseDir , repositoriesPath , tag );
    var jsonIfyLessVars                 = JSONifyLessVars( baseDir , repositoriesPath , lessVarsFileName );
    var bootstrapClientLibsCreated      = createBootstrapClientLibs( grunt , baseDir + "/" + repositoriesPath + "/bootstrap" , bootstrapPath , baseClientLibsCategory , jqueryCategory );



    Q.all( [ checkOutBootstrapTagFinished , jsonIfyLessVars, bootstrapClientLibsCreated ] ).spread(function (jsonIfyLessVarsResult, bootstrapClientLibsCreatedResult) {

        done( );

    });

};
