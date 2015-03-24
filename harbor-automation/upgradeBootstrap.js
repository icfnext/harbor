var argv    = require('yargs').argv;

var updater = require('./libs/bootstrapUpdate/bootstrapUpdate');

updater( argv.tag , __dirname );
