function Cinema(){
	var self = this;
	
	/**
	 * Spusti se po nacteni DOM.
	 */
	self.init = function(){
		self.startSlider();
		setInterval("cinema.printActualTime()", 250);
		self.printActualTime();
		
		self.registerDeleteProgramItem();
	};
	
	/**
	 * Spusti slider s filmy.
	 */
	self.startSlider = function(){
		if($('.frame').length > 0){
			var $frame = $('#effects');
			var $wrap  = $frame.parent();

			// Call Sly on frame
			$frame.sly({
				horizontal: 1,
				itemNav: 'forceCentered',
				smart: 1,
				activateMiddle: 1,
				activateOn: 'click',
				mouseDragging: 1,
				touchDragging: 1,
				releaseSwing: 1,
				startAt: 1,
				scrollBar: $wrap.find('.scrollbar'),
				scrollBy: 1,
				speed: 300,
				elasticBounds: 1,
				easing: 'swing',
				dragHandle: 1,
				dynamicHandle: 1,
				clickBar: 1,
				// Cycling
				cycleBy: 'items',
				cycleInterval: 5000,
				pauseOnHover: 1,
				// Buttons
				prev: $wrap.find('.prev'),
				next: $wrap.find('.next')
			});
		}
	};
	
	/**
	 * Vygeneruje sedacky v sale dle zadanych parametru.
	 * @param rows pocet rad v sale
	 * @param columns pocet sedadel v jedne rade
	 */
	self.generateHall = function(rows, columns){
		var hall = $("#hall");
		
		hall.append("<div>Zde je plátno.</div>");
		for ( var i = 1; i <= rows; i++) {
			for(var j = 1; j <= columns; j++){
				hall.append("<span class='seat' data-taken='false' data-row='"+i+"' data-column='"+j+"' title='Řada "+i+". Sedadlo "+j+".'>"+j+"</span>");
			}
			hall.append("<span>"+i+".</span>");
			hall.append("<br />");
		}
		
		$(".seat").click(function(){
			var row = $(this).data("row");
			var column = $(this).data("column");
			var taken = $(this).data("taken");
			
			if(!taken && !$(this).hasClass("reserved")){
				$(this).addClass("selected");
				$(this).data('taken', true);
				self.addProgramItem(this);
				
				console.log("Vybráno sedadlo číslo " + column + " z řady " + row + ".");
			}else if(!$(this).hasClass("reserved")){
				if($(this).hasClass('selected') == true){
					$(this).removeClass('selected');
					$(this).data('taken', false);
					self.removeProgramItem(this);
				}else{
					console.log("Sedadlo uz je vybrane.");
				}
			}else{
				console.log("Nelze objednat toto sedadlo.");
			}
		});
	};
	
	/**
	 * 
	 * @param seat
	 */
	self.addProgramItem = function(seat, row, column, idProgram){
		var s = $(seat).data();
		
		var noInput = false;
		var programId = $("#programId").val();
		
		if(seat == null){
			s = {row: row, column: column, idProgram: idProgram};
			noInput = false;
		}
		if(typeof s.idProgram == "undefined"){
			s.idProgram = programId;
		}
		
		var input = "<input class='"+s.row +"-"+s.column +"' type='hidden' name='programItems' value='"+s.row +","+s.column +","+s.idProgram+"' />";
		var block = "<div class='"+s.row +"-"+s.column +"'>Rezervováno sedadlo "+s.column+" z řady "+s.row+".</div>";
		
		if(programId == idProgram){
			$("span.seat[data-row='"+row+"'][data-column='"+column+"']").addClass("selected");
			$("span.seat[data-row='"+row+"'][data-column='"+column+"']").data("taken", true);
		}
		
		if(!noInput) $("#reserveForm").append(input);
		$("#reserve").append(block);
		console.log("adding reservation item");
	};
	
	/**
	 * 
	 * @param seat
	 */
	self.removeProgramItem = function(seat){
		var s = $(seat).data();
		
		$("." + s.row +"-"+s.column).remove();
		console.log("removing reservation item");
	};
	
	self.registerDeleteProgramItem = function(){
		$(".reservationDelete").click(function(){
			$(this).parent().parent().remove();
			return false;
		});
	};
	
	self.setReserved = function(data){		
		for ( var int = 0; int < data.data.length; int++) {
			var item = data.data[int];
			self.markAsReserved(item.row, item.column);
		}
	};
	
	self.markAsReserved = function(row, column){
		console.log(row, column);
		
		$("span.seat[data-row='"+row+"'][data-column='"+column+"']").addClass("reserved");
		console.log($("span.seat[data-row='"+row+"'] span.seat[data-column='"+column+"']"));
	};
	
	/**
	 * 
	 */
	self.printActualTime = function(){
		if($("#timestamp").length > 0){
			//For todays date;
			Date.prototype.today = function(){ 
			    return ((this.getDate() < 10)?"0":"") + this.getDate() +"."+(((this.getMonth()+1) < 10)?"0":"") + (this.getMonth()+1) +"."+ this.getFullYear() 
			};
			//For the time now
			Date.prototype.timeNow = function(){
			     return ((this.getHours() < 10)?"0":"") + this.getHours() +":"+ ((this.getMinutes() < 10)?"0":"") + this.getMinutes() +":"+ ((this.getSeconds() < 10)?"0":"") + this.getSeconds();
			};
			
			var newDate = new Date();
			var datetime = newDate.today() + " " + newDate.timeNow();
			
			console.log("time testing...");
			$("#timestamp").html(datetime);
		}
	};
	
}

var cinema = new Cinema();

$(function(){
	cinema.init();
});