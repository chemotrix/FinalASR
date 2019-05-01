<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zxx" class="no-js">
<head>
<!-- Mobile Specific Meta -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Favicon-->
<link rel="shortcut icon" href="img/fav.png">
<!-- Author Meta -->
<meta name="author" content="colorlib">
<!-- Meta Description -->
<meta name="description" content="">
<!-- Meta Keyword -->
<meta name="keywords" content="">
<!-- meta character set -->
<meta charset="UTF-8">
<!-- Site Title -->
<title>PRACTICA FINAL - TweetsAnalyzer</title>

<link
	href="https://fonts.googleapis.com/css?family=Poppins:100,200,400,300,500,600,700"
	rel="stylesheet">
<!--
			CSS
			============================================= -->
<link rel="stylesheet" href="css/linearicons.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/magnific-popup.css">
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/nice-select.css">
<link rel="stylesheet" href="css/animate.min.css">
<link rel="stylesheet" href="css/owl.carousel.css">
<link rel="stylesheet" href="css/main.css">
</head>
<body style="display: block;">


	<header id="header" class="">
	<div class="container main-menu">
		<div class="row">

			<div class="col-md-4" id="logo">
				<a href="/"><img src="img/logo.png" alt="" title=""></a>
			</div>
			<div class="col-md-4">
				<h1 class="" style="font-size: 6vw; text-align: center;">TweetsAnalyzer</h1>
			</div>
			<!-- #nav-menu-container -->
		</div>
	</div>
	</header>
	<!-- #header -->

	<!-- #header -->

	<!-- start banner Area -->
	<section class="home-about-area" style="padding-top: 180px;">

	<div class="container">
		<div class="row align-items-center justify-content-between">
			<div class="col-lg-12 col-md-12 home-about-left">


				<hr>
				<script>
					function loadingani() {
						document.getElementById("mom").style.display = "block";
						document.getElementById("elformo").submit();
						return false;
					}
				</script>
				<form class="form-inline" method="post" id="elformo"
					action="/getTweets">

					<div class="container" style="">

						<div class="row" style="text-align: center;">
							<div class="col-md-3" style="padding-bottom: 10px;">
								<label class="h3" style="margin-top: 13px;">Usuario de
									Twitter</label>
							</div>
							<div class="col-md-7" style="padding-bottom: 10px;">
								<input type="text" name="username"
									class="form-control form-control-lg" id="colFormLabelLg"
									placeholder="Insertar aqui" style="width: inherit;">
							</div>
							<div class="col-md-2"
								style="text-align: center; width: 100%; padding-bottom: 10px;">
								<input class="btn btn-primary btn-lg active" type="submit" onclick="return loadingani();"
									style="width: 100%;" value="Enviar">
							</div>
						</div>

					</div>

				</form>

				<hr>
				<div class="container" style="margin-top: 10px;">

					<div class="row" style="text-align: center;">
						<div class="col-md-3" style="padding-bottom: 10px;"></div>
						<div class="col-md-3"
							style="text-align: center; width: 100%; padding-bottom: 10px;">
							<form class="form-inline" method="post" action="/listar" id="elformo2">
								<input class="btn btn-secondary active" type="submit" 
									style="width: 100%;" value="Listar">
							</form>
						</div>
						<div class="col-md-4"
							style="text-align: center; width: 100%; padding-bottom: 10px;">
							<form class="form-inline" method="post" action="/deletedb" id="elformo3">
								<input class="btn btn-danger active" type="submit" "
									style="width: 100%;" value="Reset DataBase">
							</form>
						</div>
						<div class="col-md-2" style="padding-bottom: 10px;"></div>
					</div>
					<div class="row">
						<div class="loadermom" id="mom">

							<div class="loader"></div>
						</div>
					</div>

				</div>


				<br> <br>

			</div>
		</div>

	</div>
	</section>
	<!-- End brands Area -->




	<!-- start footer Area -->
	<footer
		style="
    position: fixed;
    height: 100px;
    background-color: black;
    bottom: 0px;
    left: 0px;
    right: 0px;
    margin-bottom: 0px;
">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="single-footer-widget">
					<br> <br>
					<h3 style="color: #fff;">ARQUITECTURA DE SERVICIOS EN RED</h3>
					<h4>&copy; Chema Rodriguez &amp; Carlos Arranz</h4>
				</div>
				<br> <br>
			</div>

		</div>
	</div>
	</footer>
	<!-- End footer Area -->

	<style>
.loadermom {
	width: 100%;
	margin-left: 45%;
	margin-top: 100px;
	display: none;
}

.loader {
	border: 16px solid #ffffff; /* Light grey */
	border-top: 16px solid #0062cc;
	border-bottom: 16px solid #0062cc;
	border-radius: 50%;
	width: 120px;
	height: 120px;
	animation: spin 2s linear infinite;
}

@
keyframes spin { 0% {
	transform: rotate(0deg);
}
100%
{
transform








:




 




rotate








(360
deg






);
}
}
</style>



	<script src="js/vendor/jquery-2.2.4.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/vendor/bootstrap.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
	<script src="js/easing.min.js"></script>
	<script src="js/hoverIntent.js"></script>
	<script src="js/superfish.min.js"></script>
	<script src="js/jquery.ajaxchimp.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/jquery.tabs.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/isotope.pkgd.min.js"></script>
	<script src="js/waypoints.min.js"></script>
	<script src="js/jquery.counterup.min.js"></script>
	<script src="js/simple-skillbar.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/mail-script.js"></script>
	<script src="js/main.js"></script>

	<nav id="mobile-nav"> </nav>
	<div id="mobile-body-overly"></div>


	<nav id="mobile-nav"> </nav>
	<div id="mobile-body-overly"></div>
</body>



</html>