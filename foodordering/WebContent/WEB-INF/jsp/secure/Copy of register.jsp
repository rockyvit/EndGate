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
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/form-elements.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
        
        
       

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="<c:url value="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js" />"></script>
                <script src="<c:url value="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js" />"></script>
             
        <![endif]-->

        <!-- Favicon and touch icons -->
        
        
        
        
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<c:url value="/resources/ico/apple-touch-icon-144-precomposed.png"/>">
         <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<c:url value="/resources/ico/favicon.png"/>">
          <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<c:url value="/resources/ico/apple-touch-icon-72-precomposed.png"/>">
            <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<c:url value="/resources/ico/apple-touch-icon-57-precomposed.png"/>">
            
      

    </head>

    <body>
    

        <!-- Top content -->
        <div class="top-content">
        	
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
	                            <div class="form-bottom">
				                    <form role="form" action="" method="post" class="login-form">
				                    	<div class="form-group">
				                    		<label class="sr-only" for="form-username">Username</label>
				                        	<input type="text" name="form-username" placeholder="Mobile No./Email..." class="form-username form-control" id="form-username">
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="form-password">Password</label>
				                        	<input type="password" name="form-password" placeholder="Password..." class="form-password form-control" id="form-password">
				                        </div>
				                        <button type="submit" class="btn">Sign in!</button>
				                    </form>
			                    </div>
		                    </div>
		                
		                	<div class="social-login">
	                        	<h3>...or login with:</h3>
	                        	<div class="social-login-buttons">
		                        	<a class="btn btn-link-2" href="#">
		                        		<i class="fa fa-facebook"></i> Facebook
		                        	</a>
		                        	<a class="btn btn-link-2" href="#">
		                        		<i class="fa fa-twitter"></i> Twitter
		                        	</a>
		                        	<a class="btn btn-link-2" href="#">
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
				                    <form role="form" action="" modelAttribute="user"method="post" class="registration-form">
				                    	<div class="form-group">
				                    		<label class="sr-only" for="username">name</label>
				                        	<input type="text" name="username" placeholder="Your name..." class="form-your-name form-control" id="username">
				                        </div>
				                        
				                        <div class="form-group">
				                        	<label class="sr-only" for="email">email</label>
				                        	<input type="text" name="email" placeholder="Email..." class="form-email form-control" id="email">
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="mobileno">mobileno</label>
				                        	<input type="text" name="mobileno" placeholder="mobile no" class="form-mobileno form-control" id="mobileno">
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="password">password</label>
				                        	<input type="text" name="password" placeholder="password" class="form-password form-control" id="password">
				                        </div>
				                       <div class="form-group">
				                        	<label class="sr-only" for="password2">password2</label>
				                        	<input type="text" name="password2" placeholder="password again..." class="form-password2 form-control" id="password2">
				                        </div>
				                        <button type="submit" class="btn" value="register">Register</button>
				                       
				                    </form>
			                    </div>
                        	</div>
                        	
                        </div>
                    </div>
                    
                </div>
            </div>
            
        </div>

        <!-- Footer -->
        <footer>
        	<div class="container">
        		<div class="row">
        			
        			<div class="col-sm-8 col-sm-offset-2">
        				<div class="footer-border"></div>
        				<p>Made by Anli Zaimi at <a href="http://azmind.com" target="_blank"><strong>AZMIND</strong></a> 
        					having a lot of fun. <i class="fa fa-smile-o"></i></p>
        			</div>
        			
        		</div>
        	</div>
        </footer>

        <!-- Javascript -->
        
           <script src="<c:url value="/resources/js/jquery-1.11.1.js" />"></script>
              <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
                 <script src="<c:url value="/resources/js/jquery.backstretch.min.js" />"></script>
                    <script src="<c:url value="/resources/js/scripts.js" />"></script>
                       
        
        
        <!--[if lt IE 10]>
            <script src="<c:url value="/resources/js/placeholder.js" />"></script>
        <![endif]-->
        
      
        </script>

    </body>
    </form:form>

</html>