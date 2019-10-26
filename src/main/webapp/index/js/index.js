$('.carousel').carousel({
    interval: 2000
  });

  $(document).ready( function() { 
    $('.grocery-card').hover( 
        function() {                        
            $(this).find('.grocery-card-text').slideToggle();       
                                   
        }
    );

});