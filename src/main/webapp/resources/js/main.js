function setContentSize(){
	$(".content").width($(window).width() - $(".leftMenu").width() - 30);
}

$(function(){
		
	setContentSize();
	$(window).resize(function() {
	  setContentSize();
	});
	
	$(".confirm").click(function(){
		if(confirm("Opravdu chcete provést požadovanou akci?")){
			return true;
		}else{
			return false;
		}
	});
	
	/**
	 * Otevre jquery UI dialog s formularem pro pridani filmu pomoci vyhledavani na csfd.
	 */
	$( "#dialog" ).dialog({
	      autoOpen: false,
	      height: 250,
	      width: 525,
	      show: {
	        effect: "blind",
	        duration: 1000
	      },
	      hide: {
	        effect: "explode",
	        duration: 1000
	      }
	    });
	 
	    $( "#opener" ).click(function() {
	      $( "#dialog" ).dialog( "open" );
	      return false;
	    });
	
	/**
	 * Autocomplete pro vyhledavani na csfd.
	 */
	$( "#searchCsfd" ).autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	          url: "http://csfdapi.cz/movie?search=" + $("#searchCsfd").val(),
	          //dataType: "jsonp",
	          success: function( data ) {
	        	  console.log(data);
	            response( $.map( data, function( item ) {
	              return {
	                label: item.names.cs,
	                value: item.id
	              }
	            }));
	          }
	        });
	      },
	      minLength: 2,
	      select: function( event, ui ) {
	         
	         $("#csfdId").val(ui.item.value);
	         $("#movieTitle").html(ui.item.label);
	         $("#price").focus();
	      },
	      open: function() {
	        $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
	      },
	      close: function() {
	        $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
	      }
	    });
	
});
