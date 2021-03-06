<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, asr.proyectoFinal.dominio.Tweet"%>
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
								<label class="sr-only" for="inlineFormInputGroup">Username</label>
								<div class="input-group mb-2">
									<div class="input-group-prepend">
										<div class="input-group-text">@</div>
									</div>


									<input type="text" name="username"
										class="form-control form-control-lg" id="colFormLabelLg"
										placeholder="Vox_es" style="width: inherit;">
								</div>
							</div>
							<div class="col-md-2"
								style="text-align: center; width: 100%; padding-bottom: 10px;">
								<input class="btn btn-primary btn-lg active" type="submit"
									onclick="return loadingani();" style="width: 100%;"
									value="Enviar">
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
							<form class="form-inline" method="post" action="/listar"
								id="elformo2">
								<input class="btn btn-secondary active" type="submit"
									style="width: 100%;" value="Listar">
							</form>
						</div>
						<div class="col-md-4"
							style="text-align: center; width: 100%; padding-bottom: 10px;">
							<form class="form-inline" method="post" action="/deletedb"
								id="elformo3">
								<input class="btn btn-danger active" type="submit"
									"
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
	<%
		if (request.getAttribute("reset") == "true") {
	%>

	<div class="row" style="text-align: center;">
		<div class="col-md-12">
			<p class="text-center h2" style="font-size: 48px;">
			<div class="alert alert-success" role="alert"
				style="font-size: 25px;">La base de datos ha sido reseteada
				satisfactoriamente.</div>
			</p>

		</div>
	</div>
	<%
		}
		String tone = (String) request.getAttribute("tone");
		String tweet = (String) request.getAttribute("tweet");
		String username = (String) request.getAttribute("username");
		String id0 = (String) request.getAttribute("id0");

		if (request.getAttribute("listar") == "True") {
	%>
	<div class="container" style="margin-top: 50px; max-width: 1400px;">
		<div
			style="text-align: left; font-size: xx-large; font-weight: bold; color: #0062cc;">
			BASE DE DATOS</div>
		<br />
		<div class="row" style="text-align: center;">
			<div class="col-md-6" style="text-align: left;">
				<span style="font-weight: normal; font-size: large;"> Se
					almacena cada Tweet con los siguientes atributos: </span>
				<ul class="list-group list-group-flush">
					<li class="list-group-item"
						style="font-size: 20px; font-weight: 500;">- IdTweet</li>
					<li class="list-group-item"
						style="font-size: 20px; font-weight: 500;">- Username</li>
					<li class="list-group-item"
						style="font-size: 20px; font-weight: 500;">- Tweet (texto)</li>
					<li class="list-group-item"
						style="font-size: 20px; font-weight: 500;">- Pic (Foto/Video)</li>
					<li class="list-group-item"
						style="font-size: 20px; font-weight: 500;">- Tone Analysis</li>
				</ul>
			</div>
			<div class="col-md-6">
				<img src="https://image.flaticon.com/icons/svg/23/23670.svg"
					style="text-align: center; height: 200px;">
			</div>
		</div>
	</div>
	<br>
	<br>
	<div class="container" style="margin-top: 100px; max-width: 1400px;">
		<div class="row" style="text-align: left;">

			<div class="row" style="width: 100%;">
				<%
					Collection<Tweet> all_tweets = (Collection<Tweet>) request.getAttribute("cloudant");
						String tmpTone = "";
						Iterator<Tweet> iterator = all_tweets.iterator();
						while (iterator.hasNext()) {
							Tweet t = (Tweet) iterator.next();

							if (!t.getTone().contains("-")) {
								t.setTone("Neutral - 50%");
							}
							if (t.getUsername() != null && t.getUsername() != "" && t.getUsername() != " ") {

								System.out.println("-" + tmpTone + "-");
				%>


				<div class="col-md-3" style="margin-bottom: 40px;">
					<div class="card" style="width: 100%; height: 100%;">
						<div class="card-header"
							style="text-align: center; font-size: 2vh; font-weight: 600;">
							<img src="https://avatars.io/twitter/<%=t.getUsername()%>"
								alt="https://avatars.io/twitter/<%=t.getUsername()%>"
								class="rounded-circle border border-dark" height="50" width="50"
								style="margin-right: 10px; border: 2px solid #222222 !important;">
							<a href="http://twitter.com/<%=t.getUsername()%>">@<%=t.getUsername()%></a>
							<p style="font-weight: 100; padding-top: 17px; text-align: left;">
								<span style="font-weight: 600;">idTweet: </span><%=t.getidTweet()%></p>
						</div>
						<div class="card-body">
							<blockquote class="blockquote mb-0">
								<p style="padding-bottom: 30px;"><%=t.getTweet() + " "%>
									<a href="http://<%=t.getPic()%>"><%=t.getPic()%></a>
								</p>

								<footer
									style="color: #008000a6;font-weight: bold; font-size: 25px;position: absolute;left: 10px; text-align: center;bottom: 20px;width: 100%;">
								<%=t.getTone()%></footer>
							</blockquote>

						</div>
					</div>
				</div>
				<%
					}
						}
				%>


			</div>
		</div>
	</div>

	<%
		} else {
			if (tweet != null) {
	%>



	<div class="container" style="padding-bottom: 150px; margin-top: 0px;">
		<br> <br>

		<%
			if (request.getAttribute("null") != "true") {
		%>
		<div class="row" style="text-align: center;">
			<div class="col-md-10">
				<p class="text-center h2"
					style="margin-top: 80px; font-size: 5.3vw; margin-bottom: 100px;">
					<a href="http://twitter.com/<%=username%>">@<%=username%></a>
				</p>
			</div>
			<div class="col-md-2">
				<img src="https://avatars.io/twitter/<%=username%>"
					alt="https://avatars.io/twitter/<%=username%>"
					class="rounded-circle border border-dark" height="200" width="200"
					style="border: 3px solid #222222 !important;">
			</div>
		</div>
		<%
			} else {
		%>

		<div class="row" style="text-align: center;">
			<div class="col-md-12">
				<p class="text-center h2" style="font-size: 48px;">
				<div class="alert alert-danger" role="alert"
					style="font-size: 25px;">
					Oops! Ese usuario no existe, quiz� estes buscando analizar a <a
						href="/getTweets?username=vox_es">Vox_es</a>
				</div>
				</p>

			</div>
		</div>
		<%
			}

					if (request.getAttribute("null") != "true") {
		%>
		<div class="row" style="margin-top: 30px">

			<div class="col-md-12">
				<div class="container">
					<br>
					<hr>
					<br>
					<div class="page-header">
						<h1 id="timeline" style="text-align: center;">Personality
							Insight</h1>
					</div>

					<br>


					<p id="Openness" class="bigtrait"></p>
					<p id="Conscientiousness" class="bigtrait"></p>
					<p id="Extraversion" class="bigtrait"></p>
					<p id="Agreeableness" class="bigtrait"></p>
					<p id="Neuroticism" class="bigtrait"></p>

					<script type="text/javascript">
						var ins =
					<%=request.getAttribute("insi")%>
						;
						var i;
						for (i = 0; i < ins.length; i++) {

							var name = ins[i]["name"]

							if (name == "Emotional range") {
								name = "Neuroticism"
							}
							var per = ins[i]["percentile"]
							console.log(name + ',' + per);

							document.getElementById(name).innerHTML = name
									+ ': ' + Math.round(per * 100) + '%';
						}
					</script>
				</div>

			</div>

			<div class="col-md-12">
				<div class="container">
					<br>
					<hr>
					<br>
					<div class="page-header">
						<h1 id="timeline" style="text-align: center;">Sentiment
							Timeline Analysis</h1>
					</div>



					<ul class="timeline">
						<%
							for (int i = 0; i < 10; i++) {
											String tweet_tmp = (String) request.getAttribute("tweet" + i);
											String pic_tmp = (String) request.getAttribute("pic" + i);
											String tone_tmp = (String) request.getAttribute("tone" + i);
											if (tone_tmp == "")
												tone_tmp = "Neutral - 50%";

											if (i % 2 == 0) {
						%>
						<li>
							<div class="timeline-badge">
								<i class="glyphicon glyphicon-check"></i>
							</div>
							<div class="timeline-panel"
								style="font-size: 2vh; text-align: left;">
								<div class="timeline-heading">
									<h4 class="timeline-title">
										<img src="https://avatars.io/twitter/<%=username%>"
											alt="https://avatars.io/twitter/<%=username%>"
											class="rounded-circle border border-dark" height="50"
											width="50" style="border: 2px solid #222222 !important;">@<%=username%></h4>
									<p>
										<small class="text-muted"><i
											class="glyphicon glyphicon-time"></i></small>
									</p>
								</div>
								<div class="timeline-body">
									<p><%=tweet_tmp%></p>
									<%
										if (pic_tmp != " ") {
									%>
									<p>
										<a href="http://<%=pic_tmp%>"><%=pic_tmp%></a>
									</p>
									<%
										}
									%>

									<br>
									<p
										style="text-align: center; color: #11b537d6; font-size: 26px;">
										Top sentiment:<br> <br> <span
											style="font-weight: bold;"> <%=tone_tmp%></span>
									</p>
								</div>
							</div>
						</li>
						<%
							} else {
						%>
						<li class="timeline-inverted">
							<div class="timeline-badge warning">
								<i class="glyphicon glyphicon-credit-card"></i>
							</div>
							<div class="timeline-panel"
								style="font-size: 2vh; text-align: left;">
								<div class="timeline-heading">
									<h4 class="timeline-title">
										<img src="https://avatars.io/twitter/<%=username%>"
											alt="https://avatars.io/twitter/<%=username%>"
											class="rounded-circle border border-dark" height="50"
											width="50" style="border: 2px solid #222222 !important;">@<%=username%></h4>
								</div>
								<div class="timeline-body">
									<br>
									<p><%=tweet_tmp%></p>
									<%
										if (pic_tmp != " ") {
									%>
									<p>
										<a href="http://<%=pic_tmp%>"><%=pic_tmp%></a>
									</p>
									<%
										}
									%>

									<br>
									<p
										style="text-align: center; color: #11b537d6; font-size: 26px;">
										Top sentiment:<br> <br> <span
											style="font-weight: bold;"><%=tone_tmp%><span>
									</p>
								</div>
							</div>
						</li>
						<%
							}
										}
						%>
					</ul>
				</div>
				<hr>

			</div>


		</div>

	</div>

	<%
		}
			}

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
    height: 100px;
    background-color: black;
    bottom: 0px;
    left: 0px;
    right: 0px;
    margin-bottom: 0px;
">
	<div class="" style="background-color: black;">
		<div class="container">
			<div class="col-lg-12 col-md-12 col-sm-12"
				style="background-color: black;">
				<div class="single-footer-widget">
					<br> <br>
					<h3 style="color: #fff;">ARQUITECTURA DE SERVICIOS EN RED</h3>
					<h4>� Chema Rodriguez &amp; Carlos Arranz</h4>
				</div>
				<br> <br>
			</div>

		</div>
	</div>
	</footer>
	<!-- End footer Area -->


	<style>
.timeline {
	list-style: none;
	padding: 20px 0 20px;
	position: relative;
}

.timeline:before {
	top: 0;
	bottom: 0;
	position: absolute;
	content: " ";
	width: 3px;
	background-color: #eeeeee;
	left: 50%;
	margin-left: -1.5px;
}

.timeline>li {
	margin-bottom: 20px;
	position: relative;
}

.timeline>li:before, .timeline>li:after {
	content: " ";
	display: table;
}

.timeline>li:after {
	clear: both;
}

.timeline>li:before, .timeline>li:after {
	content: " ";
	display: table;
}

.timeline>li:after {
	clear: both;
}

.timeline>li>.timeline-panel {
	width: 46%;
	float: left;
	border: 1px solid #d4d4d4;
	border-radius: 2px;
	padding: 20px;
	position: relative;
	-webkit-box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
	box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
}

.timeline>li>.timeline-panel:before {
	position: absolute;
	top: 26px;
	right: -15px;
	display: inline-block;
	border-top: 15px solid transparent;
	border-left: 15px solid #ccc;
	border-right: 0 solid #ccc;
	border-bottom: 15px solid transparent;
	content: " ";
}

.bigtrait {
	text-align: center;
	margin-left: 0;
	font-size: 25px;
	color: #121c8f;
	font-weight: bold;
}

}
.timeline>li>.timeline-panel:after {
	position: absolute;
	top: 27px;
	right: -14px;
	display: inline-block;
	border-top: 14px solid transparent;
	border-left: 14px solid #fff;
	border-right: 0 solid #fff;
	border-bottom: 14px solid transparent;
	content: " ";
}

.timeline>li>.timeline-badge {
	color: #fff;
	width: 50px;
	height: 50px;
	line-height: 50px;
	font-size: 1.4em;
	text-align: center;
	position: absolute;
	top: 16px;
	left: 50%;
	margin-left: -25px;
	background-color: #999999;
	z-index: 100;
	border-top-right-radius: 50%;
	border-top-left-radius: 50%;
	border-bottom-right-radius: 50%;
	border-bottom-left-radius: 50%;
}

.timeline>li.timeline-inverted>.timeline-panel {
	float: right;
}

.timeline>li.timeline-inverted>.timeline-panel:before {
	border-left-width: 0;
	border-right-width: 15px;
	left: -15px;
	right: auto;
}

.timeline>li.timeline-inverted>.timeline-panel:after {
	border-left-width: 0;
	border-right-width: 14px;
	left: -14px;
	right: auto;
}

.timeline-badge.primary {
	background-color: #2e6da4 !important;
}

.timeline-badge.success {
	background-color: #3f903f !important;
}

.timeline-badge.warning {
	background-color: #f0ad4e !important;
}

.timeline-badge.danger {
	background-color: #d9534f !important;
}

.timeline-badge.info {
	background-color: #5bc0de !important;
}

.timeline-title {
	margin-top: 0;
	color: inherit;
}

.timeline-body>p, .timeline-body>ul {
	margin-bottom: 0;
}

.timeline-body>p+p {
	margin-top: 5px;
}

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

