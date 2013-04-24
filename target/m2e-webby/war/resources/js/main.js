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
	
});
