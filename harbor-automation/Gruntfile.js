module.exports = function(grunt) {




    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json')
    });

    grunt.registerTask('bootstrapUpdateTo', function(tag){

        new require("./libs/bootstrapUpdate/bootstrapUpdate.js")( grunt , this , tag , __dirname);


    });

}