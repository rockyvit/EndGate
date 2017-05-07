<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<head>
<link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
      
  <title>My Cart</title>
  
  <style>
  .badge-notify{
    background:black;
    position:relative;
    top: -20px;
    right: 10px;
  }
  .my-cart-icon-affix {
    position: fixed;
    z-index: 999;
  }
  </style>
  
</head>

<body class="container">

  <div class="page-header">
    <h1>NON VEG
      <div style="float: right; cursor: pointer;">
        <span class="glyphicon glyphicon-shopping-cart my-cart-icon"><span class="badge badge-notify my-cart-badge"></span></span>
      </div>
    </h1>
  </div>

  <div class="row">
  <div class="col-md-3 text-center">
	
      <img src="resources/img/images/thai.jpg" class="btn btn-info2" data-toggle="modal" data-target="#myModal1" width="150px" height="150px">
	  <div id="myModal1" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-body">
            <img src="resources/img/images/chicken briyani.jpg" class="img-responsive">
			<b>Ingredients = </b> xyz
        </div>
    </div>
  </div>
</div>
      <br/>
	  
      CHICKEN BRIYANI - <strong>$10</strong>
      <br>
      <button class="btn btn-danger my-cart-btn" data-id="1A" data-name="CHICKEN BRIYANI" data-summary="summary 1" data-price="10" data-quantity="1" data-image="images/chicken briyani.jpg">ADD TO CART</button>


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
      <br>
      <button class="btn btn-danger my-cart-btn" data-id="2A" data-name="THAI" data-summary="summary 2" data-price="20" data-quantity="1" data-image="images/thai.jpg">Add to Cart</button>
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
      <button class="btn btn-danger my-cart-btn" data-id="3A" data-name="SALMONDISH" data-summary="summary 3" data-price="30" data-quantity="1" data-image="images/salmondish.jpeg">Add to Cart</button>
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
      <button class="btn btn-danger my-cart-btn" data-id="4A" data-name="ITEM4" data-summary="summary 4" data-price="40" data-quantity="1" data-image="images/item4.jpg">Add to Cart</button>
    </div>

  </div>

  
  <div class="page-header">
    <h1>VEG
      <div style="float: right; cursor: pointer;">
        <span class="glyphicon glyphicon-shopping-cart my-cart-icon"><span class="badge badge-notify my-cart-badge"></span></span>
      </div>
    </h1>
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
      <button class="btn btn-danger my-cart-btn" data-id="11A" data-name="CHAPATI" data-summary="summary 1" data-price="10" data-quantity="1" data-image="images/chapati_med.png">Add to Cart</button>
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
      <button class="btn btn-danger my-cart-btn" data-id="15A" data-name="Aloo_tikki_chaat" data-summary="summary 5" data-price="50" data-quantity="1" data-image="images/Aloo_tikki_chaat.png">Add to Cart</button>
	  
    </div>

  </div>
  
  <div class="page-header">
    <h1>SALAD
      <div style="float: right; cursor: pointer;">
        <span class="glyphicon glyphicon-shopping-cart my-cart-icon"><span class="badge badge-notify my-cart-badge"></span></span>
      </div>
    </h1>
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
      <button class="btn btn-danger my-cart-btn" data-id="21A" data-name="MIX SALAD" data-summary="summary 1" data-price="10" data-quantity="1" data-image="images/salad1.jpg">Add to Cart</button>
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
      <button class="btn btn-danger my-cart-btn" data-id="22A" data-name="SALAD2" data-summary="summary 2" data-price="20" data-quantity="1" data-image="images/salad2.jpg">Add to Cart</button>
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
      <button class="btn btn-danger my-cart-btn" data-id="24A" data-name="SALAD4" data-summary="summary 4" data-price="40" data-quantity="1" data-image="images/salad4.png">Add to Cart</button>
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
      <button class="btn btn-danger my-cart-btn" data-id="25A" data-name="SALAD5" data-summary="summary 5" data-price="50" data-quantity="1" data-image="images/salad5.png">Add to Cart</button>
	  
    </div>

  </div>
  
 
  

  <script src="<c:url value="resources/bootstrap/js/jquery-2.2.3.min.js"/>"></script>
  <script src="<c:url value="resources/bootstrap/js/bootstrap.min.js"/>"></script>>
  <script src="<c:url value="resources/bootstrap/js/jquery.mycart.js"/>"></script>
  <script src="<c:url value="resources/bootstrap/js/jquery.mycart.min.js"/>"></script>
  
  <script type="text/javascript">
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
          alert(checkoutString)
          console.log("checking out", products, totalPrice, totalQuantity);
        },
      
        
     
    });

  });
 
$(document).ready(function() {
        $(".remove-attr").click(function(){            
            $("a").removeAttr("href");
        });
    });


  </script>
  
  <%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>




