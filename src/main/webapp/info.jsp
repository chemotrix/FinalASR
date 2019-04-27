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
	<button type="button" id="mobile-nav-toggle">
		<i class="lnr lnr-menu"></i>
	</button>
	<button type="button" id="mobile-nav-toggle">
		<i class="lnr lnr-menu"></i>
	</button>
	<header id="header" class="">
	<div class="container main-menu">
		<div class="row align-items-center justify-content-between d-flex">
			<div id="logo">
				<a href="/FinalASR"><img src="img/logo.png" alt="" title=""></a>
			</div>
			<nav id="nav-menu-container">
			<h1 class="display-1">TweetsAnalyzer</h1>
			</nav>
			<!-- #nav-menu-container -->
		</div>
	</div>
	</header>
	<!-- #header -->



	<!-- start banner Area -->
	<section class="home-about-area" style="padding-top: 180px;">

	<div class="container">
		<div class="row align-items-center justify-content-between">
			<div class="col-lg-12 col-md-12 home-about-left">


				<hr>

				<form class="form-inline" method="post" action="/getTweets">

					<div class="container" style="">

						<div class="row">
							<div class="col col-lg-3">
								<label class="h3" style="margin-top: 13px;">Usuario de
									Twitter</label>
							</div>
							<div class="col col-lg-6">
								<input type="text" name="username"
									class="form-control form-control-lg" id="colFormLabelLg"
									placeholder="Insertar aqui" style="width: inherit;">
							</div>
							<div class="col col-lg-3">
								<input class="btn btn-primary btn-lg active" type="submit"
									value="Enviar">
							</div>
						</div>

					</div>

				</form>
				<br> <br>

			</div>
		</div>

	</div>

	<%
		String tweet = (String) request.getAttribute("tweet");
		String username = (String) request.getAttribute("username");
		String id0 = (String) request.getAttribute("id0");
		String tweet0 = (String) request.getAttribute("tweet0");
		if (tweet != null) {
	%>



	<div class="container" style="padding-bottom: 150px;">
		<div class="row" style="text-align: center;">
			<div class="col-6">
				<p class="text-center h2"
					style="margin-top: 80px; margin-left: 100px;">
					@<%=username%></p>
			</div>
			<div class="col-6">
				<img src="<%="https://avatars.io/twitter/" + username%>"
					alt="Smiley face" height="200" width="200">
			</div>
		</div>

		<div class="row vertical-divider" style="margin-top: 30px">

			<div class="col-4">
				<p class="h2">Personality Insight</p>
				<br>
				<p>Lorem Ipsum is simply dummy text of the printing and
					typesetting industry. Lorem Ipsum has been the industry's standard
					dummy text ever since the 1500s, when an unknown printer took a
					galley of type and scrambled it to make a type specimen book. It
					has survived not only five centuries, but also the leap into
					electronic typesetting, remaining essentially unchanged. It was
					popularised in the 1960s with the release of Letraset sheets
					containing Lorem Ipsum passages, and more recently with desktop
					publishing software like Aldus PageMaker including versions of
					Lorem Ipsum.</p>
			</div>
			<div class="col-4">
				<p class="h2">Tone Analyzer</p>
				<br>
				<p>Lorem Ipsum is simply dummy text of the printing and
					typesetting industry. Lorem Ipsum has been the industry's standard
					dummy text ever since the 1500s, when an unknown printer took a
					galley of type and scrambled it to make a type specimen book. It
					has survived not only five centuries, but also the leap into
					electronic typesetting, remaining essentially unchanged. It was
					popularised in the 1960s with the release of Letraset sheets
					containing Lorem Ipsum passages, and more recently with desktop
					publishing software like Aldus PageMaker including versions of
					Lorem Ipsum.</p>
			</div>
			<div class="col-4">
				<p class="h2">Últimos Tweets</p>
				<br>
				<%
					for (int i = 0; i < 10; i++) {
				%>
				<!-- p><=request.getAttribute("id"+i)%></p-->
				<p><%=request.getAttribute("tweet" + i)%></p>
				<hr>
				<%
					}
				%>
			</div>
		</div>
	</div>

	< <%
 	} else {
 %>
	<p>
		NOT WORKING<%=tweet%><%=username%></p>
	<%
		}
	%> </section>
	<!-- End brands Area -->



	<script type="text/javascript">
		function GetImage() {

			var txtBox = document.getElementById("twit");
			var imgTwitter = document.getElementById("imgTwitter");
			imgTwitter.src = "http://api.twitter.com/1/users/profile_image/"
					+ txtBox.value;

		}
	</script>
	<!-- start footer Area -->
	<footer class=""
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
.row.vertical-divider {
	overflow: hidden;
}

.row.vertical-divider>div[class^="col-"] {
	text-align: center;
	padding-bottom: 100px;
	margin-bottom: -100px;
	border-left: 3px solid #397992a6;
	border-right: 3px solid #397992a6;
}

.row.vertical-divider div[class^="col-"]:first-child {
	border-left: none;
}

.row.vertical-divider div[class^="col-"]:last-child {
	border-right: none;
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

