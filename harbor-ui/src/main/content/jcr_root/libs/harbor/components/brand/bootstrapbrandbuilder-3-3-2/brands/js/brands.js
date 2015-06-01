"use strict";

$(document).ready(function(){
    $('body').scrollspy({ target: '.bs-docs-sidebar' });

    console.log("test");

    $('.bs-docs-sidebar').affix({
        offset: {
            top: 100
        }
    })
})