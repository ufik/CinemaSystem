function setContentSize(){
	$(".content").width($(window).width() - $(".leftMenu").width() - 30);
}

$(function(){
		
	setContentSize();
	$(window).resize(function() {
	  setContentSize();
	});
	
});
