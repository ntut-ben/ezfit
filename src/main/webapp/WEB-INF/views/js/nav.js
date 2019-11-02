$(document).ready(function() {    
    'use strict';
    $("#a").hide();
    $("#d").hide();
    var c, currentScrollTop = 0,
    navbar = $('#navbar-top');    
    $(window).scroll(function() {           
    var a = $(window).scrollTop();
    var b = navbar.height();
    currentScrollTop = a;
    if(window.innerWidth > 992){
        if (c < currentScrollTop && a > b + b) {
            navbar.slideUp(300);            
            $("#a").fadeIn(200);
            $("#b").fadeOut(200);
            $("#c").fadeOut(200);
            $("#d").fadeIn(200);
        } else if (c > currentScrollTop && !(a <= b)){
            navbar.slideDown(300);
            $("#a").fadeOut(200);
            $("#b").fadeIn(200);
            $("#c").fadeIn(200);
            $("#d").fadeOut(200);
           
        }    
        c = currentScrollTop;
    }else{
        navbar.hide();
        $("#a").show();
        $("#d").hide();
    }
    });

    $(document).ready( function() {                

        /* $(selector).hover( inFunction, outFunction ) */
        $('.dropdown').hover( 
            function() {                        
                $(this).find('.dropdown-menu').css({
                    "display": "block",
                    "margin-top": 0
                });                        
            }, 
            function() {                        
                $(this).find('.dropdown-menu').css({
                    "display": "none",
                    "margin-top": 0
                });                        
            } 
        );

    });
});