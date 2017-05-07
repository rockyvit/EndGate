<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
    
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <script src="//maps.googleapis.com/maps/api/js"
        type="text/javascript"></script>
  <link type="text/css" rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" />
  <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css" type="text/css">  
  <link type="text/css" rel="stylesheet" href="<c:url value="/resources/bootstrap/css/jquery-ui.css" />" />
 


  <style>
  .badge-notify{
    background:red;
    position:relative;
    top: -20px;
    right: 10px;
  }
  .my-cart-icon-affix {
    position: fixed;
    z-index: 999;
  }
  
  </style>
  

    <title>FoodClock - Delicious and Healthy Food, In No-Time!</title>

    <!-- Bootstrap Core CSS -->
    

    <!-- Plugin CSS -->
    <link rel="stylesheet" href="resources/bootstrap/css/animate.min.css" type="text/css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="resources/bootstrap/css/grayscale.css" type="text/css">
    
    <!-- Custom Fonts -->
    <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="resources/bootstrap/css/flaticon.css"> 
    <link href="http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
     
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Begin MailChimp Signup Form -->
    <link href="//cdn-images.mailchimp.com/embedcode/horizontal-slim-10_7.css" rel="stylesheet" type="text/css">
    <style type="text/css"> 
        /*#mc_embed_signup{background:none; clear:left; font:14px Helvetica,Arial,sans-serif; width:100%;}*/
        /* Add your own MailChimp form style overrides in your site stylesheet or in this style block.
           We recommend moving this block and the preceding CSS link to the HEAD of your HTML file. */
    </style>

<import org.pw.foodordering.elements.db>
</head>
<left>welcome</left>
<c:out value="${user.first_name}" />
<%-- <span class="message">Welcome ${user.getFirst_name())}</span> --%>
<body class="container" id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">

    <!-- Navigation -->
    <nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
        <div class="container1">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                    <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top">
                    <i class="fa fa-play-circle"></i>  <span class="light">Start</span> FoodClock
                </a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
                <ul class="nav navbar-nav">
                    <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#about">About</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#how">How it Works</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#moreTime">More time to</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#features">Features</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#item">Order</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#download">Contact</a>
                    </li>
                     <!-- <li>
                        <a class="page-scroll" id = "login" href="#" onclick = "loadtest(0)">Login</a>
                        <a class="page-scroll" id = "login" href="#" onclick='javascript:window.open("http://localhost:8080/foodordering/index.jsp", "_blank", "scrollbars=1,resizable=1,height=300,width=450");'>Login</a>
                        
                    </li> --> 
                    <li>
              
                  <a class="glyphicon glyphicon-shopping-cart my-cart-icon">CART<span class="badge badge-notify my-cart-badge"></a>
     
       </li>        
        </ul>
        </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Intro Header -->
    <header class="intro">
        <div class="intro-body">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <h1 class="brand-heading">FoodClock</h1>
                        <p class="intro-text">Delicious and Healthy Food, In No-Time!</p>
                        <a href="#download" class="btn btn-default btn-lg page-scroll">Order Now</a>
                        <p id="demo">Click the button to get your position.</p>

<button onclick="getLocation()"><img src="resources/img/Apps-Google-Maps-icon.png" style="width:50px;height:50px;"></button>

<div id="mapholder"></div>

<script src="https://maps.google.com/maps/api/js?sensor=false"></script>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- About Section -->
    <section id="about" class="container content-section text-center">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <h2>To get the satisfaction out of your work,<br>
                Sometimes you need to think outside the box</h2>
                <hr class="hrOrange">
                <p>We asked Indian Corporate employees, what they miss most in their corporate lifestyle- Food came first and Time second, to Money and Appreciation.</p>
                <p>Research says that “we really are what we eat”. So, eat healthfully today for happiness tomorrow!</p>
                <p>Food-clock serves you healthy and delicious meals, at work, prepared by qualified chefs and brought to you by distinguished global team.</p>
                <p>Select, Order and Walk to your door-step!</p>
            </div>
        </div>
    </section>

    <!-- How it works -->
    <section id="how" class="content-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">How FoodClock works</h2>
                    <hr class="hrPurple">
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-6 text-center">
                    <div class="service-box">
                        <i class="fa flaticon-app orange wow bounceIn"></i>
                        <h3>Step 1</h3>
                        <p class="text-muted">Choose your meals, drinks, and treats from our daily rotating menu.</p>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 text-center">
                    <div class="service-box">
                        <i class="fa flaticon-food wow bounceIn purple" data-wow-delay=".1s"></i>
                        <h3>Step 2</h3>
                        <p class="text-muted">Our expert chefs organize your food for delivery - hot and ready to eat!</p>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 text-center">
                    <div class="service-box">
                        <i class="fa flaticon-food-1 wow bounceIn orange" data-wow-delay=".2s"></i>
                        <h3>Step 3</h3>
                        <p class="text-muted">Your meal arrives in no time - like a home-cooked meal without the effort!</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    

    <!-- test Section -->
    <section id="moreTime" class="content-section" id="portfolio">
        <div class="container-fluid">
        <h2 class="text-center">Spend more time...</h2>
        <hr class="hrOrange">
            <div class="row no-gutter">
                <div class="col-lg-3 col-sm-6">
                    <a class="portfolio-box">
                        <img src="resources/img/portfolio/friend.jpg" class="img-responsive" alt="">
                        <div class="portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded">
                                    Enjoying with that special friend
                                </div>
                                <div class="project-name">
                                    Kyunki Har ek Friend Zaroori Hota hai!
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <a class="portfolio-box">
                        <img src="resources/img/portfolio/family.jpg" class="img-responsive" alt="">
                        <div class="purple-box portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded">
                                    Taking your family out
                                </div>
                                <div class="project-name">
                                    All you can, we can do for your family!
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <a class="portfolio-box">
                        <img src="resources/img/portfolio/more.jpg" class="img-responsive" alt="">
                        <div class="portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded">
                                    Doing more
                                </div>
                                <div class="project-name">
                                    Creating Impression for what matters most!
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <a class="portfolio-box">
                        <img src="resources/img/portfolio/study.jpg" class="img-responsive" alt="">
                        <div class="purple-box portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded">
                                    Studying for that important exam
                                </div>
                                <div class="project-name">
                                    We understand, that one night is all you have got!
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <a class="portfolio-box">
                        <img src="resources/img/portfolio/hobbies.jpg" class="img-responsive" alt="">
                        <div class="purple-box portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded">
                                    Enjoying your favourite hobbies
                                </div>
                                <div class="project-name">
                                    Let’s bring the creativity out of you!
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <a class="portfolio-box">
                        <img src="resources/img/portfolio/workingout.jpg" class="img-responsive" alt="">
                        <div class="portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded">
                                    Working out
                                </div>
                                <div class="project-name">
                                    Yoga, Jog, exercise ‘cause body and brain needs to align!
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <a class="portfolio-box">
                        <img src="resources/img/portfolio/tv.jpg" class="img-responsive" alt="">
                        <div class="purple-box portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded">
                                    Enjoying your favorite TV shows and movies
                                </div>
                                <div class="project-name">
                                    You must not wait for a full day to view repeat telecast!
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <a class="portfolio-box">
                        <img src="resources/img/portfolio/mom.jpg" class="img-responsive" alt="">
                        <div class="portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded">
                                    Thanking your mother
                                </div>
                                <div class="project-name">
                                    She is the idol figure, who has inspired us, all of us!
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </section>
    
    <!-- How it works -->
    <section id="features" class="content-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">At Your Service</h2>
                    <hr class="hrPurple">
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6 text-center">
                    <div class="service-box">
                        <i class="fa flaticon-icon-109973 wow bounceIn orange"></i>
                        <h3>Ordering On-demand</h3>
                        <p class="text-muted">It is your meal, your time.</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 text-center">
                    <div class="service-box">
                        <i class="fa flaticon-road wow bounceIn purple" data-wow-delay=".1s"></i>
                        <h3>Geo-mapped Live tracking</h3>
                        <p class="text-muted">Locate your food even before you order.</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 text-center">
                    <div class="service-box">
                        <i class="fa flaticon-icon-93466 wow bounceIn orange" data-wow-delay=".2s"></i>
                        <h3>DOOR-STEP DELIVERY</h3>
                        <p class="text-muted">Our industry's best delivery team will bring your food right at your doorstep.</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 text-center">
                    <div class="service-box">
                        <i class="fa fa-4x fa-thumbs-up wow bounceIn purple" data-wow-delay=".3s"></i>
                        <h3>PROMISE of "AS-IT-IS"</h3>
                        <p class="text-muted">Hot will be hot and cold will be cold!</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

          
 <section id="item" class="content-section">          
 <body class="container">
 <div class="page-header">
    <h1>NON VEG</h1>
  </div>
<div class="row">
    <div class="col-md-3 text-center">
	
      <img src="resources/img/images/chicken briyani.jpg" class="btn btn-info2" data-toggle="modal" data-target="#myModal1" width="150px" height="150px">
	  <div id="myModal1" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-body">
            <img src="resources/img/images/chicken briyani.jpg" class="img-responsive">
			<b>Ingredients = </b> AAA
        </div>
    </div>
  </div>
</div>
      <br/>
      CHICKEN BRIYANI - <strong>100</strong>
      <br/>
      <button class="btn btn-danger my-cart-btn" data-id="2A" data-name="THAI" data-summary="summary 2" data-price="20" data-quantity="1" data-image="resources/img/images/chicken briyani.jpg">Add to Cart</button>
    </div>

    
    <div class="col-md-3 text-center">
	
      <img src="resources/img/images/thai.jpg" class="btn btn-info2" data-toggle="modal" data-target="#myModal1" width="150px" height="150px">
	  <div id="myModal1" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-body">
            <img src="resources/img/images/thai.jpg" class="img-responsive">
			<b>Ingredients = </b> AAA
        </div>
    </div>
  </div>
</div>
      <br>
	  </a>
      THAI - <strong>$20</strong>
      </br>
      <button class="btn btn-danger my-cart-btn" data-id="2A" data-name="THAI" data-summary="summary 2" data-price="20" data-quantity="1" data-image="resources/img/images/thai.jpg">Add to Cart</button>
    </div>

    <div class="col-md-3 text-center">
      <img src="resources/img/images/salmondish.jpeg" class="btn btn-info2" data-toggle="modal" data-target="#myModal2" width="150px" height="150px">
	  	  <div id="myModal2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-body">
            <img src="resources/img/images/salmondish.jpeg" class="img-responsive">
			<b>Ingredients = </b> BBB
        </div>
    </div>
  </div>
</div>
      <br>
	  
      SALMONDISH - <strong>$30</strong>
      <br>
      <button class="btn btn-danger my-cart-btn" data-id="3A" data-name="SALMONDISH" data-summary="summary 3" data-price="30" data-quantity="1" data-image="resources/img/images/salmondish.jpeg">Add to Cart</button>
    </div>

    
    <div class="col-md-3 text-center">
      <img src="resources/img/images/item4.jpg" class="btn btn-info2" data-toggle="modal" data-target="#myModal2" width="150px" height="150px">
	  	  <div id="myModal2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-body">
            <img src="resources/img/images/item4.jpg" class="img-responsive">
			<b>Ingredients = </b> CCC
        </div>
    </div>
  </div>
</div>
      <br>
      ITEM4 - <strong>$40</strong>
      <br>
      <button class="btn btn-danger my-cart-btn" data-id="4A" data-name="ITEM4" data-summary="summary 4" data-price="40" data-quantity="1" data-image="resources/img/images/item4.jpg">Add to Cart</button>
    </div>

  </div>

  
  <div class="page-header">
    <h1>VEG</h1>
      
  </div>

  <div class="row">
    <div class="col-md-3 text-center">
      <img src="resources/img/images/chapati_med.jpg" class="btn btn-info2" data-toggle="modal" data-target="#myModal11A" width="150px" height="150px">
	  	  <div id="myModal11A" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-body">
            <img src="resources/img/images/chapati_med.jpg" class="img-responsive">
			<b>Ingredients = </b> EEE
        </div>
    </div>
  </div>
</div>
      <br>
      CHAPATI- <strong>$10</strong>
      <br>
      <button class="btn btn-danger my-cart-btn" data-id="11A" data-name="CHAPATI" data-summary="summary 1" data-price="10" data-quantity="1" data-image="resources/img/images/chapati_med.png">Add to Cart</button>
    </div>

    <div class="col-md-3 text-center">
	  <img src="resources/img/images/item_3.JPG" class="btn btn-info2" data-toggle="modal" data-target="#myModal13A" width="150px" height="150px">
	  	  <div id="myModal13A" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-body">
            <img src="resources/img/images/item_3.JPG" class="img-responsive">
			<b>Ingredients = </b> GGG
        </div>
    </div>
  </div>
</div>
      <br>
      ITEM3 - <strong>$40</strong>
      <br>
      <button class="btn btn-danger my-cart-btn" data-id="13A" data-name="ITEM3" data-summary="summary 3" data-price="30" data-quantity="1" data-image="resources/img/images/item_3.JPG">Add to Cart</button>
    </div>

    <div class="col-md-3 text-center">     
	 <img src="resources/img/images/malai_paneer1.jpg"class="btn btn-info2" data-toggle="modal" data-target="#myModal14A" width="150px" height="150px">
	 	  <div id="myModal14A" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-body">
            <img src="resources/img/images/malai_paneer1.jpg" class="img-responsive">
			<b>Ingredients = </b> HHH
        </div>
    </div>
  </div>
</div>
      <br>
      MALAI_PANEER- <strong>$40</strong>
      <br>
      <button class="btn btn-danger my-cart-btn" data-id="14A" data-name="MALAI_PANEER" data-summary="summary 4" data-price="40" data-quantity="1" data-image="images/malai_paneer1.png">Add to Cart</button>
    </div>

    <div class="col-md-3 text-center">
	  <img src="resources/img/images/Aloo_tikki_chaat.jpg" class="btn btn-info2" data-toggle="modal" data-target="#myModal15A" width="150px" height="150px">
	  	  <div id="myModal15A" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-body">
            <img src="resources/img/images/Aloo_tikki_chaat.jpg" class="img-responsive">
			<b>Ingredients = </b> JJJ
        </div>
    </div>
  </div>
</div>
      <br>
      Aloo_tikki_chaat - <strong>$50</strong>
      <br>
      <button class="btn btn-danger my-cart-btn" data-id="15A" data-name="Aloo_tikki_chaat" data-summary="summary 5" data-price="50" data-quantity="1" data-image="resources/img/images/Aloo_tikki_chaat.png">Add to Cart</button>
	  
    </div>
</div>
  
  <div class="page-header">
    <h1>SALAD</h1>
  </div>

  <div class="row">
    <div class="col-md-3 text-center">
      <img src="resources/img/images/salad1.jpg" class="btn btn-info2" data-toggle="modal" data-target="#myModal21A" width="150px" height="150px">
	  	  <div id="myModal21A" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-body">
            <img src="resources/img/images/salad1.jpg" class="img-responsive">
			<b>Ingredients = </b> MMM
        </div>
    </div>
  </div>
</div>
      <br>
      MIX SALAD- <strong>$20</strong>
      <br>
      <button class="btn btn-danger my-cart-btn" data-id="21A" data-name="MIX SALAD" data-summary="summary 1" data-price="10" data-quantity="1" data-image="resources/img/images/salad1.jpg">Add to Cart</button>
    </div>
	

    <div class="col-md-3 text-center">
	  <img src="resources/img/images/salad2.jpg" class="btn btn-info2" data-toggle="modal" data-target="#myModal22A" width="150px" height="150px">
	  	  <div id="myModal22A" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-body">
            <img src="resources/img/images/salad2.jpg" class="img-responsive">
			<b>Ingredients = </b> NNN
        </div>
    </div>
  </div>
</div>
      <br>
      SALAD2- <strong>$30</strong>
      <br>
      <button class="btn btn-danger my-cart-btn" data-id="22A" data-name="SALAD2" data-summary="summary 2" data-price="20" data-quantity="1" data-image="resources/img/images/salad2.jpg">Add to Cart</button>
    </div>

    <div class="col-md-3 text-center">
	  <img src="resources/img/images/salad3.JPG" class="btn btn-info2" data-toggle="modal" data-target="#myModal23A" width="150px" height="150px">
	  	  <div id="myModal23A" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-body">
            <img src="resources/img/images/salad3.JPG" class="img-responsive">
			<b>Ingredients = </b> LLL
        </div>
    </div>
  </div>
</div>
      <br>
	 
      SALAD3 - <strong>$30</strong>
      <br>
      <button class="btn btn-danger my-cart-btn" data-id="23A" data-name="SALAD3" data-summary="summary 3" data-price="30" data-quantity="1" data-image="resources/img/images/salad3.JPG">Add to Cart</button>
    </div>

    
    <div class="col-md-3 text-center">     
	 <img src="resources/img/images/salad4.jpg" class="btn btn-info2" data-toggle="modal" data-target="#myModal24A" width="150px" height="150px">
	 	  <div id="myModal24A" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-body">
            <img src="resources/img/images/salad4.jpg" class="img-responsive">
			<b>Ingredients = </b> ppp
        </div>
    </div>
  </div>
</div>
      <br>
	 </a>
      SALAD4- <strong>$40</strong>
      <br>
      <button class="btn btn-danger my-cart-btn" data-id="24A" data-name="SALAD4" data-summary="summary 4" data-price="40" data-quantity="1" data-image="resources/img/images/salad4.png">Add to Cart</button>
    </div>

    <div class="col-md-3 text-center">
	  <img src="resources/img/images/salad5.jpg" class="btn btn-info2" data-toggle="modal" data-target="#myModal25A" width="150px" height="150px">
	   <div id="myModal25A" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-body">
            <img src="resources/img/images/salad5.jpg" class="img-responsive">
			<b>Ingredients = </b> UUU
        </div>
    </div>
  </div>
</div>
      <br>
	  </a>
      SALAD5- <strong>$50</strong>
      <br>
      <button class="btn btn-danger my-cart-btn" data-id="25A" data-name="SALAD5" data-summary="summary 5" data-price="50" data-quantity="1" data-image="resources/img/images/salad5.png">Add to Cart</button>
	  
    </div>

  </div>
    </section>
      
     <script src="resources/bootstrap/js/bootstrap.min.js"></script>
   

    <!-- Contact Section -->
    
   <section id="download" class="content-section text-center">
   
        <div class="download-section">
         
            <div class="col-lg-8 col-lg-offset-2">
                <h2>Follow Us</h2>
                <p>We're on social media too!  Stay informed about our next moves</p>
                <ul class="list-inline banner-social-buttons">
                    <li>
                        <a href="https://facebook.com/" class="btn btn-default btn-lg"><i class="fa fa-facebook fa-fw"></i> <span class="network-name">Facebook</span></a>
                    </li>
                    <li>
                        <a href="https://twitter.com/" class="btn btn-default btn-lg"><i class="fa fa-twitter fa-fw"></i> <span class="network-name">Twitter</span></a>
                    </li>
                    <li>
                        <a href="https://www.instagram.com/" class="btn btn-default btn-lg"><i class="fa fa-instagram fa-fw"></i> <span class="network-name">Instagram</span></a>
                    </li>
                    <li>
                        <a href="https://www.instagram.com/" class="btn btn-default btn-lg"><span class="network-name">Blog</span></a>
                    </li>
                    <li>
                        <a href="https://www.gmail.com/" class="btn btn-default btn-lg"><i class="fa fa-gmail fa-fw"></i> <span class="network-name">Gmail</span></a>
                       
                    </li>

                </ul>
            </div>
        </div>
        
    </section>
   <!-- <center><a href="secure/address.do">Select your Location</a></center>-->
 <a href="jsp/address.jsp">Select your Location</a>
     
    <!-- Footer -->
    <footer>
      <div class="container text-center">
           <p>Copyright &copy; FoodClock 2016</p>
        </div>
    </footer>

    <!-- jQuery -->
    <script src="<c:url value="/resources/bootstrap/js/jquery-1.12.4.js" />"></script>
    <!-- <script src="resources/bootstrap/js/jquery.js"></script> -->
    
     <!-- Bootstrap Core JavaScript -->
    <script src="resources/bootstrap/js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="resources/bootstrap/js/jquery.easing.min.js"></script>
    <script src="resources/bootstrap/js/wow.min.js"></script>
    
    <!-- Custom Theme JavaScript -->
    <script src="resources/bootstrap/js/grayscale.js"></script>
    <script src="resources/bootstrap/js/jquery.popupoverlay.js"></script>
    

    <script>

      (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){

      (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),

      m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)

      })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

      ga('create', 'UA-75421770-1', 'auto');

      ga('send', 'pageview');

    </script>
     
   
 <script src="resources/bootstrap/js/jquery.mycart.js"/></script>
  <script src="resources/bootstrap/js/jquery.mycart.min.js"/></script>
  
  <script type="text/javascript">
  
  //$.noConflict();
  $(function () {

    var goToCartIcon = function($addTocartBtn){
      var $cartIcon = $(".my-cart-icon");
      var $image = $('<img width="30px" height="30px" src="' + $addTocartBtn.data("image") + '"/>').css({"position": "fixed", "z-index": "999"});
      $addTocartBtn.prepend($image);
      var position = $cartIcon.position();
      $image.animate({
        top: position.top,
        left: position.left
      }, 500 , "linear", function() {
        $image.remove();
      });
    }

    $('.my-cart-btn').myCart({
      currencySymbol: '$',
      classCartIcon: 'my-cart-icon',
      classCartBadge: 'my-cart-badge',
      classProductQuantity: 'my-product-quantity',
      classProductRemove: 'my-product-remove',
      classCheckoutCart: 'my-cart-checkout',
      affixCartIcon: true,
      showCheckoutModal: true,
      cartItems: [],
      clickOnAddToCart: function($addTocart){
        goToCartIcon($addTocart);
      },
      afterAddOnCart: function(products, totalPrice, totalQuantity) {
        console.log("afterAddOnCart", products, totalPrice, totalQuantity);
      },
      clickOnCartIcon: function($cartIcon, products, totalPrice, totalQuantity) {
        console.log("cart icon clicked", $cartIcon, products, totalPrice, totalQuantity);
      },
      checkoutCart: function(products, totalPrice, totalQuantity) {
          var checkoutString = "Total Price: " + totalPrice + "\nTotal Quantity: " + totalQuantity;
          checkoutString += "\n\n id \t name \t summary \t price \t quantity \t image path";
          $.each(products, function(){
            checkoutString += ("\n " + this.id + " \t " + this.name + " \t " + this.summary + " \t " + this.price + " \t " + this.quantity + " \t " + this.image);
          });
          
        },
      
        
     
    });
    
  });
  
  $(function() {
  	  $("#test1").click(function() {
  	  var url = 'jsp/payment.jsp';
  	  $(location).attr('href', url);
  	  })
  	  });

  function loadtest(){debugger;
	  
		 
		 alert("we are here");
		   $('#diwakar').dialog({ modal: true });
		 //$("#diwakar").popup('show');
		 alert("we are heregukugukguk");
		 
		 
  }
		  
		  

		  
		 /* $(function() {

			  $("#diwakar").dialog({
			     autoOpen: false,
			     modal: true
			   });

			  $("#login").on("click", function(e) {
				  alert("we are hwre");
			      e.preventDefault();
			      $("#diwakar").dialog("open");
			  });

			});
		 
		 
		  */
		 
		 
		 
	// }
$(document).ready(function() {
        $(".remove-attr").click(function(){            
            $("a").removeAttr("href");
            $('#diwakar').dialog({ modal: true });
        });
    });


  </script>
  
  
<!--
To use this code on your website, get a free API key from Google.
Read more at: https://www.w3schools.com/graphics/google_maps_basic.asp
-->

<script>
var x = document.getElementById("demo");
function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition, showError);
    } else { 
        x.innerHTML = "Geolocation is not supported by this browser.";
    }
}

function showPosition(position) {
    lat = position.coords.latitude;
    lon = position.coords.longitude;
    latlon = new google.maps.LatLng(lat, lon)
    mapholder = document.getElementById('mapholder')
    mapholder.style.height = '250px';
    mapholder.style.width = '500px';

    var myOptions = {
    center:latlon,zoom:14,
    mapTypeId:google.maps.MapTypeId.ROADMAP,
    mapTypeControl:false,
    navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}
    }
    
    var map = new google.maps.Map(document.getElementById("mapholder"), myOptions);
    var marker = new google.maps.Marker({position:latlon,map:map,title:"You are here!"});
}

function showError(error) {
    switch(error.code) {
        case error.PERMISSION_DENIED:
            x.innerHTML = "User denied the request for Geolocation."
            break;
        case error.POSITION_UNAVAILABLE:
            x.innerHTML = "Location information is unavailable."
            break;
        case error.TIMEOUT:
            x.innerHTML = "The request to get user location timed out."
            break;
        case error.UNKNOWN_ERROR:
            x.innerHTML = "An unknown error occurred."
            break;
    }
}
</script>
 <script src="../resources/bootstrap/js/jquery-2.1.3.min.js"></script>
    <script src="../resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="../resources/bootstrap/js/mycurrentlocation.js"></script>
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDR_8y6SHg6EhFVV7q4cs7t8yeRiQ5RSjo&sensor=true"></script>
<script type="text/javascript">
      //calling the function to detects

        $( document ).ready(function() {
          myCurrentLocation();
        });
    </script>
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-59526751-1', 'auto');
  ga('send', 'pageview');

	


</script>
  

</body>

</html>
