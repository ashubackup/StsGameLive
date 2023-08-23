<%@ page language="java" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta name="viewport"
	content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0 minimal-ui" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>GameHub</title>

<link rel="stylesheet" type="text/css" href="styles/style.css">
<link rel="stylesheet" type="text/css" href="styles/skin.css">
<link rel="stylesheet" type="text/css" href="styles/framework.css">
<link rel="stylesheet" type="text/css" href="styles/ionicons.min.css">

<link
	href="https://fonts.googleapis.com/css?family=Quicksand:300,400,500,700"
	rel="stylesheet">
<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript" src="scripts/plugins.js"></script>
<script type="text/javascript" src="scripts/custom.js"></script>
<style>
body #page-content #page-content-scroll {
	padding-top: 20px !important;
}

body, #page-content {
	background-color: #010100 !important;
}

.joinus .logo-join img {
	height: 120px;
}

div#page-content {
	margin-top: 0 !important;
}

.joinus-slider .swiper-slide img {
	width: 100%;
}

.joinus-content {
	max-width: 500px;
	margin: 0 auto;
}

@media screen and (max-width: 767px) {
	.joinus .logo-join img {
		height: 90px;
	}
}
</style>
</head>

<body class="grey">


	<div class="page-preloader page-preloader-dark">
		<div class="spinner"></div>
	</div>



	<!-- Main Small Icon Sidebar -->


	<div id="page-content" class="header-clear-large top_all">
		<div id="page-content-scroll">
			<!--Enables this element to be scrolled -->
			<div class="joinus">
				<div class="logo-join">
					<img src="images/logo.png" alt="logo" class="img-fluid" />
				</div>
				<div class="joinus-slider">
					<div class="swiper-container">
						<div class="swiper-wrapper">
							<div class="swiper-slide">
								<img src="images/logo.png" alt="logo" class="img-fluid" />
							</div>
						</div>
						
					</div>
				</div>


				<div class="joinus-content">
					<h3>Welcome to GameHub</h3>
					<h5>Play unlimited! Hundreds of cool games</h5>
					<!-- <p>ENTER YOUR NUMBER AND JOIN GameHub</p> -->
					<h5 style="color: red;">${errorMessage}</h5>
					<form method="post" action="Login">
						<input type="text" name="msisdn" title="Please enter only number"
							onkeypress="if ( isNaN(this.value + String.fromCharCode(event.keyCode) )) return false;"
							placeholder="Enter your phone number : eg. 812xxxxxx"
							maxlength="11" minlength="9" pattern="\d*" required="">
							<input type="text" name="type"value="offnet" style="display:none;">
							<input type="text" name="svc_id" value="2" style="display:none;">
							<input type="text" name="ref" value="<%=request.getParameter("ref")%>" style="display:none;">
						<p>
							<input type="checkbox" checked="checked" required=""> I
							agreed to the <a href="term.html">Terms and Conditions </a>
						</p> 
						<div class="center-cus">
							<input type="submit" value="Subscribe"
								onclick="if(!this.form.checkbox.checked){alert('You must agree to the terms first.');return false}"
								class="btn btn-default submit-btn-cus">
						</div>
					</form>
				</div>
			</div>

			<footer>
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<p>&copy; copyright All Right Reserved.</p>
						</div>
					</div>
				</div>
			</footer>
		</div>

		<a href="#" class="back-to-top-badge"><i
			class="ion-android-arrow-dropup"></i>Back to Top</a>

	</div>
	<script>
		var swiper = new Swiper('.swiper-container', {
			spaceBetween : 30,
			centeredSlides : true,
			pagination : {
				el : '.swiper-pagination',
				clickable : true,
			},
			navigation : {
				nextEl : '.swiper-button-next',
				prevEl : '.swiper-button-prev',
			},
		});
	</script>
</body>