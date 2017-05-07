/* Foodordering helping functions */

/**************************************************************
 * Present numbers 0,1,2 ... as 00, 01, 02 ... 
 */
function formatNumber(inData)
{
	if (inData == NaN)
		return '00';
	if (String(inData).length < 2) 
		return String("0" + inData);
	else 
		return String(inData);
}
var date_set = 0;
/************************************************************** 
 * Update date in res input
 *	mode = 0 - selecting new date
 *	mode = 1 - attaching to existing order
 */
function update_date(mode) {
	var res = '';
	if(mode == 0)
	{
		date = $('#datepicker').val();
		hrs = formatNumber($('#hrs').val());
		mins = formatNumber($('#mins').val());
		res = date + ' ' + hrs + ':' + mins;
	}
	else
	{
		res = $('#attach_list').val();
	}
	$('#res').val(res);
	date_set = 1;
}

/**************************************************************
 * Show time selection div
 * max 			- number of options
 * bre 			- line break after 'bre' numbers
 * selection_txt 	- name of selection div
 * inFIeld_txt 		- field to change after selection
 * e 			- mouse coords
 * special_mod 		- set additional class to cells which % special_mod == 0
 * title 		- title of selection
 */
function showSelection(max, bre, selection_txt, inField_txt, e, special_mod, title) {
	var selection = $(selection_txt);
	var inField = $(inField_txt);
	x = e.pageX - $('#food').offset().left;
	y = e.pageY - $('#food').offset().top;
	selection.css("left", x).css("top",y);
	content = "<table class='pw_time_select'><tr><th colspan='";
	content += bre;
	content +="'>";
	content += title;
	content += "</th></tr><tr class='pw_row_odd'>";
	tog = 0;
	for (a = 0; a<max; a++)	{
		content += "<td class='pw_cell";
		if(special_mod != -1 && (a%special_mod) == 0 ) {
			content += " special_cell";
		}
		content += "'>" + a + "</td>";
		if( ( a+1 ) % bre == 0 ) {
			tog = !tog;
			content += "</tr>"
			if ( a != max ) {
				if (tog == 1)
					content += "<tr class='pw_row_even'>"
				else
					content += "<tr class='pw_row_odd'>"
			}
		}
	}
	content += "</table>";
	selection.html(content).fadeIn(function() {
			$('.pw_cell').click(function() { 
				selection.fadeOut();
				ret = $(this).text();
				inField.val(ret).change(); 
				});
			});
}
var disabled_mode = 0;
var selected_order = 0; /* left = 1, right = 0*/
$(document).ready(function() 
 {
	var now = new Date();

	$('#change_today').click(function() {
		$('#change_today').fadeOut('fast', function() {
			$('#datepicker').fadeIn('fast');
		});

	});

	$('#datepicker').val(now.format("yyyy-mm-dd"));
	$('#current_date').text(now.format("yyyy-mm-dd"));
	$('#datepicker').datepicker({dateFormat: 'yy-mm-dd', altField: '#actualDate' });
	
	$('#datepicker').change(function() { update_date(0); });
	$('#hrs').change(function() { update_date(0); });
	$('#mins').change(function() { update_date(0); });

	$('#hrs').click(function(e) {
		showSelection(24,4,'#selection','#hrs',e, -1, 'Hour');
	});
	$('#mins').click(function(e) {
		showSelection(60,10,'#selection','#mins',e, 15, 'Minutes');
	});
// 	$('#hrs').change();
// 	$('div#create').fadeIn();

	$('#attach_date').change(function() { update_date(1); });

// 	$('.left').mouseover(function() {
// 		if (disabled_mode == 0) {
// 			$('div#create').fadeOut();
// 			$('div#attach').fadeIn();
// 			update_date(1);
// 		}
// 	});
// 	$('.right').mouseover(function() {
// 		if (disabled_mode == 0) {
// 			$('div#attach').fadeOut();
// 			$('div#create').fadeIn();
// 			update_date(0);
// 		}
// 	});

	$('#accept_button').click(function() {
		if (date_set == 1)
		{
			$('#accept_button').fadeOut(function() {
				$('#please_wait').fadeIn(function() {
					$('#dateDishId').submit();
				}); 
			});
		}
	});
// 	$('#attach_date').css('left', ($('td.left').width()/2)-($('#attach_list').width()/2));
// 	$('#create_date').css('left', ($('td.right').width()/2)-($('#change_today').width()/2));

	$('.select_left').mouseover(function() {
		selected_order = 1;
		$('.select_right').css('background','url("/foodordering/images/select.jpg")');
		$(this).css('background','url("/foodordering/images/select_s.jpg")');
	
		/* revert */
		$('#attach_list').css('background-color','#D2FFA7').css('color','#000000');
		$('td.select_left').css('color','#000000');
		/* change other */
		$('#hrs').css('background-color','#e6eddc').css('color','#abc485');
		$('#change_today').css('background-color','#e6eddc').css('color','#abc485');
		$('#datepicker').css('background-color','#e6eddc').css('color','#abc485');
		$('#mins').css('background-color','#e6eddc').css('color','#abc485');
		$('td.select_right').css('color','#abc485');
		update_date(1);
	});
	$('.select_right').mouseover(function() {
		selected_order = 0;
		$('.select_left').css('background','url("/foodordering/images/select.jpg")');
		$(this).css('background','url("/foodordering/images/select_s.jpg")');

		/* revert */
		$('#hrs').css('background-color','#D2FFA7').css('color','#000000');
		$('#change_today').css('background-color','#D2FFA7').css('color','#000000');
		$('#datepicker').css('background-color','#D2FFA7').css('color','#000000');
		$('#mins').css('background-color','#D2FFA7').css('color','#000000');
		$('td.select_right').css('color','#000000');
		/* change other */
		$('#attach_list').css('background-color','#e6eddc').css('color','#abc485');
		$('td.select_left').css('color','#abc485');
		update_date(0);
	});

	$('span#accept_button').mouseover(function() {
		//Only new order
		if($('.select_left').size() != 0) {
			if (selected_order == 1) { /* left */
				$('td.accept_button_cell').css('background','url("/foodordering/images/select_button_ls.jpg") center center no-repeat');
			} else { /* right */
				$('td.accept_button_cell').css('background','url("/foodordering/images/select_button_rs.jpg") center center no-repeat');
			}
		}
	}).mouseout(function() {
		$('td.accept_button_cell').css('background','url("/foodordering/images/select_button.jpg") center center no-repeat');
	});
	
	/*  -------------- orders -----------*/
//	$('#change_date').click(function() {
//		$('#date_no_change').slideUp('slow');
//		$('#date_change').slideDown('slow');
//	});

//	$('#datepicker').val(now.format("yyyy-mm-dd"));
//	$('#current_date').text(now.format("yyyy-mm-dd"));
//	$('#datepicker').datepicker({dateFormat: 'yy-mm-dd'});
	
//	$('#makeOrder').click(function() {	$(this).fadeOut(); });
	
	if($('.select_left').size() != 0) {
		$('.select_left').mouseover();
		selected_order = 1;
	} else {
		$('.select_right').mouseover();
		selected_order = 0
	}
});
