/* Foodordering helping functions */

function validateInt(elemId) {
   var elem = document.getElementById(elemId);
   var val = parseInt(elem.value);
   if (isNaN(val)) {
	   elem.value = '0';
   } else {
	   elem.value = val.toString();
   }
 } 

function closeLoginForm() {
	$('.close').fadeOut('fast', function() {
		$('#loginDiv').fadeOut('normal', function() {
			$('#back').fadeOut('fast');
			$('#login_result').hide();
		});
	});
}

function formatNumber(inData)
{
	if (inData == NaN)
		return '00';
	if (String(inData).length < 2) 
		return String("0" + inData);
	else 
		return String(inData);
}

function update_date_field(dateParam) {
	$('#res').val(dateParam);
	$('#makeOrder').fadeIn();
}

function update_date() {
	date = $('#datepicker').val();
	hrs = formatNumber($('#hrs').val());
	mins = formatNumber($('#mins').val());

	update_date_field(date + ' ' + hrs + ':' + mins);
}

function update_attach() {
	update_date_field($('#attach_date').val());
}

function connectLogin()
{
	$('.loginLink').click(function(){
		$('#back').fadeIn('normal',function() {
			main_w = $(window).width();
			main_h = $(window).height();
			div_w = $('#loginDiv').width();
			div_h = $('#loginDiv').height();
			ypos = main_h/2-div_h/2;
			xpos = main_w/2-div_w/2;
			$('#loginDiv').css("top",ypos).css("left",xpos);
			$('#loginDiv').fadeIn('normal', function() {
				if ($('#loginDiv').find('.login_title').size() == 0) {
					$('#back').fadeOut('fast');
					$('#loginDiv').fadeOut('slow');
				} else {
					$('.close').fadeIn('slow');
					$('#loginDiv').find('input').get(0).focus();
				}
			});
		});
	});
}

function loadFooter() {
	$.get('/foodordering/ajaxGetFooter.do', function(data) {
		$('#footer').html(data)
		connectLogin();
	});
}
$(document).ready(function(){

	loadFooter();
	
	$('h1.rest_toggle').mouseover(function(){
		$(this).css("background-color","#204120");	
	}).mouseout(function(){
		$(this).css("background-color","#336633");	
	});

	$('h1.group_toggle').mouseover(function(){
		$(this).css("background-color","#36001B");	
	}).mouseout(function(){
		$(this).css("background-color","#660033");	
	});

	$('div.dish_toggle').mouseover(function(){
		$(this).css("background-color","#D5D5D5");
		$(this).next().css("background-color","#F0F0F0");		
	}).mouseout(function(){
		$(this).css("background-color","#E7E7E7");
		$(this).next().css("background-color","#F0F0F0");		
	});

 	$('h1.rest_toggle').click(function(){
		$(this).next().slideToggle('slow');	
	});
	
	$('h1.group_toggle').click(function(){
		var me = $(this);
		$(this).next().slideToggle('slow',function(){
			$('.edit_light').show();
// 			me.children().find('.edit_light').show();
		});	
	});

	$('div.dish_toggle').click(function(){
		$(this).next().slideToggle('slow');
	});

	$('#old_orders').click(function() {
		$('#old_orders_content').slideToggle('slow');
	});
	
	$('#loginDiv').keypress(function(e) {
			if(e.keyCode == 27) {
				closeLoginForm();
			}
	});

	$('#back').click(function() { $('.close').fadeOut('fast', closeLoginForm()); });
	$('.close').click(function(){ $('.close').fadeOut('fast', closeLoginForm()); });

	$('#loginDiv').draggable();
	
	
	
	$('a#exp').click(function() {
		$('.rest_content').show();
		$('.group_content').show();
	});
	$('a#exp_back').click(function() {
		$('.rest_content').hide();
		$('.group_content').hide();
	});
	

	$('#go_back').mouseover(function(e) {
		$('#tooltip').css('left',e.pageX - $('#food').offset().left + 20).css('top',e.pageY - $('#food').offset().top + 20).show();
	}).mousemove(function(e) {
		$('#tooltip').css('left',e.pageX - $('#food').offset().left + 20).css('top',e.pageY - $('#food').offset().top + 20);
	}).mouseout(function() {
		$('#tooltip').hide();
	});
});
