


		<br></br>
		
		<div id="footer"></div>

	</div>
</div></div></div></div>
</div></div></div></div>
</div></div>

<div id="back"></div>

<script type="text/javascript">
<!--
function doAjaxLogin()
{
	$('#login_in_progress').show();
	$.post('/foodordering/j_spring_security_check', { j_username: $('#j_username').val(), j_password: $('#j_password').val() }, 
		function(data) {
			var login_res = $('#login_result');
			$('#login_in_progress').hide();
			login_res.hide();
			login_res.html(data);
			var le = login_res.find('#login_error');
			if ((le.size() == 1) && (le.text() == '1')) {
				login_res.show();
				return;
			}
			try { resfreshCurrentView(); } catch(err) {/*so what :)*/}
			try { loadFooter(); } catch(err) {/*so what :)*/}
			
			$('#loginDiv').html(data);
			$('#back').fadeOut('slow', function() {
				$('#loginDiv').fadeOut();
			});
	});
}

$(document).ready(function() {
	$('#login_result').ajaxError(function(e, xhr, settings, exception) {
		  //if (settings.url == 'ajax/missing.html') {
				$(this).text('AJAX error');
			//}
		}
	);
	$('#ajax_login').click(function() {	doAjaxLogin(); });
	$('#ajax_login_new').click(function() {	doAjaxLogin(); });
	$('#j_username').keydown(function(e) {if(e.keyCode == 13){doAjaxLogin();}});
	$('#j_password').keydown(function(e) {if(e.keyCode == 13){doAjaxLogin();}});
});


//-->
</script>
<div id="loginDiv">
	<div class="login_title">...::: Login :::...<div class="close">X</div></div>
		<table>
		<tr><td>Mobile NO:</td><td><input type="text" tabindex="1" size="20" maxlength="30" name="j_username" id="j_username"/></td></tr>
		<tr><td>Password:</td><td><input type="password" tabindex="2" size="20" maxlength="20" name="j_password" id="j_password"/></td></tr>
		<tr><td><input type="checkbox" name="_spring_security_remember_me"></td><td>Don't ask for my password for two weeks</td></tr>	
		<tr><td><input type="reset" name="reset" tabindex="4"/></td><td align="right"><input type="button" value="Login" id="ajax_login" tabindex="3"/></td></tr>
		</table>
	<div id="login_in_progress"><center><img src='<c:url value="/images/ajax-loader-2.gif"/>'/></center></div>
	<div id="login_result"></div>
</div>
	
</body>
</html>
