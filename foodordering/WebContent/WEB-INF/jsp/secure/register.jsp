<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.pw.foodordering.facebook.FBConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	FBConnection fbConnection = new FBConnection();
%> 
<!DOCTYPE html>
<html lang="en">
<form:form modelAttribute="user">
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Welcome in FoodClock!!</title>


        <!-- CSS -->
       <link href="<c:url value="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500" />" rel="stylesheet">
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/bootstrap/css/form-elements.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/bootstrap/css/style.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/bootstrap/css/social-buttons.css" />" rel="stylesheet">
        
    
        
       

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="<c:url value="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js" />"></script>
                <script src="<c:url value="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js" />"></script>
             
        <![endif]-->

        <!-- Favicon and touch icons -->
		
		 <script src="<c:url value="/resources/bootstrap/js/jquery-1.12.4.js" />"></script>
		    <script src="<c:url value="/resources/bootstrap/js/jquery.backstretch.min.js" />"></script>
                    <script src="<c:url value="/resources/bootstrap/js/scripts.js" />"></script>
              <script src="<c:url value="/resources/bootstrap/js/bootstrap.js" />"></script>
              
        
        
        
        
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<c:url value="/resources/ico/apple-touch-icon-144-precomposed.png"/>">
         <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<c:url value="/resources/ico/favicon.png"/>">
          <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<c:url value="/resources/ico/apple-touch-icon-72-precomposed.png"/>">
            <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<c:url value="/resources/ico/apple-touch-icon-57-precomposed.png"/>">
            
      
 
 

    </head>

    <body>
    

        <!-- Top content -->
        <div class="top-content" id ="diwakar">
        	
            <div class="inner-bg">
                <div class="container">
                	
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>FoodClock</strong> Login &amp; Register Forms</h1>
                            
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-sm-5">
                        	
                        	<div class="form-box">
	                        	<div class="form-top">
	                        		<div class="form-top-left">
	                        			<h3>Login to our site</h3>
	                            		<p>Enter username and password to log on:</p>
	                        		</div>
	                        		<div class="form-top-right">
	                        			<i class="fa fa-lock"></i>
	                        		</div>
	                            </div>
	                            <div id="loginDiv">
	                            <div class="form-bottom">
				                    <form role="form" action="" method="post" class="login-form">
				                    	
				                        
				                        <div class="form-group">
				                    		
				                        <label class="sr-only" for="form-login_id">Login id</label>
				                        	<input type="text"  placeholder="Your name..." class="form-your-name form-control" name="j_login_id" id="j_login_id">
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="form-password">Password</label>
				                        	<input type="password"  placeholder="Password..." class="form-password form-control" name="j_password" id="j_password">
				                        </div>
				                        
				                     <!--    <button type="submit" class="btn" id ="ajax_login_new">Sign in!</button> -->
				                        <input type="button" value="Login" id="ajax_login" >
				                    </form>
				                   <%--  <div id="login_in_progress"><center><img src='<c:url value="/images/ajax-loader-2.gif"/>'/></center></div> --%>
									<div id="login_result"></div>
			                    </div>
			                    </div>
		                    </div>
		                
		                	<div class="social-login">
	                        	<h3>...or login with:</h3>
	                        	<div class="social-login-buttons">
		                        	<a href="<%=fbConnection.getFBAuthUrl()%>"> 
		                        		<i class="fa fa-facebook"></i> Facebook
		                        	</a>
		                        	<a class="btn btn-link-2" href="#">
		                        		<i class="fa fa-twitter"></i> Twitter
		                        	</a>
		                        	<a	href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://www.foodclock.in/oauth2callback&response_type=code&client_id=941585455904-a6eq9hoqoa9sbt5ofmssp1fcjektc13n.apps.googleusercontent.com&approval_prompt=force"
	class="btn btn-lg btn-social btn-google"> 
		                        		<i class="fa fa-google-plus"></i> Google Plus
		                        	</a>
	                        	</div>
	                        	
	     						
	                        </div>
	                        
                        </div>
                        
                        <div class="col-sm-1 middle-border"></div>
                        <div class="col-sm-1"></div>
                        	
                        <div class="col-sm-5">
                        	
                        	<div class="form-box">
                        		<div class="form-top">
	                        		<div class="form-top-left">
	                        			<h3>Sign up now</h3>
	                            		<p>Fill in the form below to get instant access:</p>
	                        		</div>
	                        		<div class="form-top-right">
	                        			<i class="fa fa-pencil"></i>
	                        		</div>
	                            </div>
	                            <div class="form-bottom">
				                    <form role="form" action="" modelAttribute="user"method="post" class="registration-form" id="form">
				                    	<div class="form-group">
				                    		<label class="sr-only" for="first_name">name</label>
				                        	<input type="text" name="first_name" placeholder="Your name..." class="form-your-name form-control" id="first_name">
				                        </div>
				                        
				                        <div class="form-group">
				                        	<label class="sr-only" for="login_id" type ='email'>email</label>
				                        	<input type="text" name="login_id" placeholder="Email..." class="form-email form-control" id="login_id">
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="phone">mobileno</label>
				                        	<input type="text" name="phone" placeholder="mobile no" class="form-mobileno form-control" id="phone">
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="password">password</label>
				                        	<input type="text" name="password" placeholder="password" class="form-password form-control" id="password">
				                        </div>
				                       <!-- <div class="form-group">
				                        	<label class="sr-only" for="password2">password2</label>
				                        	<input type="text" name="password2" placeholder="password again..." class="form-password2 form-control" id="password2">
				                        </div> -->
				                        <button type="submit" class="btn" onclick="validate()" value="register">Register</button>
				                       
				                    </form>
			                    </div>
                        	</div>
                        	
                        </div>
                    </div>
                    
                </div>
            </div>
            
        </div>

<script>

function doAjaxLogin()
{debugger;
var login_res;
	$('#login_in_progress').show();
	console.log("login");
	$.post('/foodordering/j_spring_security_check', { j_login_id: $('#j_login_id').val(), j_password: $('#j_password').val() }, 
		function(data) {
			console.log(data);
			login_res = $('#login_result');
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
			/* $('#back').fadeOut('slow', function() {
				$('#loginDiv').fadeOut();
			}); */
	});
	console.log("login result isn:::" + login_res);
}

$(document).ready(function() {
	$('#login_result').ajaxError(function(e, xhr, settings, exception) {
		  //if (settings.url == 'ajax/missing.html') {
				$(this).text('AJAX error');
				console.log("======" + $(this).text('AJAX error'));
			//}
		}
	);
	//$('#ajax_login').click(function() {	doAjaxLogin(); });
	$('#ajax_login').click(function() {	doAjaxLogin(); });
	$('#j_login_id').keydown(function(e) {if(e.keyCode == 13){doAjaxLogin();}});
	$('#j_password').keydown(function(e) {if(e.keyCode == 13){doAjaxLogin();}});
});


function validate(login_id) {
    var emailText = document.getElementById('login_id').value;
    var mob = document.getElementById('phone').value;
    var pattern = /^[a-zA-Z0-9\-_]+(\.[a-zA-Z0-9\-_]+)*@[a-z0-9]+(\-[a-z0-9]+)*(\.[a-z0-9]+(\-[a-z0-9]+)*)*\.[a-z]{2,4}$/;
    var phoneno = /^\+?([0-9]{2})\)?[-. ]?([0-9]{4})[-. ]?([0-9]{4})$/;
    if (pattern.test(emailText)) {
    	if(phoneno.test(mob)){
    		return true;
    	} else {
    	    alert('invalid mobno:');
    	    return false;
    	} 
        return true;
    } else {
        alert('invalid email:');
        return false;
    } 
}
window.onload = function() {
    document.getElementById('form').onsubmit = validate;
}



//-->
</script>

        
  
    </body>
    </form:form>
    </html>
