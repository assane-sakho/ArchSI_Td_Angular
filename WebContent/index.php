<!DOCTYPE html>
<?php
session_start();
?>
<html lang="fr">

<head>
  <meta charset="utf-8">
  <meta charset="utf8_general_ci">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>TP ANGULAR</title>

  <!-- Bootstrap Core CSS -->
  <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">

  <!-- Fonts -->
  <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <link href="css/animate.css" rel="stylesheet" />
  <!-- Squad theme CSS -->
  <link href="css/style.css" rel="stylesheet">
  <link href="color/default.css" rel="stylesheet">

</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">
  <!-- Preloader -->
  <div id="preloader">
    <div id="load"></div>
  </div>
  <nav class=" navbar-custom top-nav-collapse" role="navigation">
    <div class="container">
		<div class="navbar-header">
      <!-- Collect the nav links, forms, and other content for toggling -->
        <ul class="nav navbar-nav">
          <li><a href="index.php">Accueil</a></li>
			   <li class="dropdown">
					<a href="\ArchSI_Td_Angular\shop.html?category=Ordinateur" class="dropdown-toggle"  role="button" aria-haspopup="true" aria-expanded="true"> <span class="nav-label">Ordinateurs</span> <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="\ArchSI_Td_Angular\shop.html?category=PC-Portable">PC Portable</a></li>
						<li><a href="\ArchSI_Td_Angular\shop.html?category=PC-de-Bureau">PC de Bureau</a></li>
						<li><a href="\ArchSI_Td_Angular\shop.html?category=Accessoires-ordinateur">Accessoires</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="\ArchSI_Td_Angular\shop.html?category=T�l�phonie" class="dropdown-toggle"  role="button" aria-haspopup="true" aria-expanded="true"> <span class="nav-label">Téléphonies</span> <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="\ArchSI_Td_Angular\shop.html?category=SmartPhone">Smart Phone</a></li>
						<li><a href="\ArchSI_Td_Angular\shop.html?category=Tel-fixe">Tel Fixe</a></li>
						<li><a href="\ArchSI_Td_Angular\shop.html?category=Accessoires-t�l�phonie">Accessoires</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="\ArchSI_Td_Angular\shop.html?category=Stockage" role="button" aria-haspopup="true" aria-expanded="true"> <span class="nav-label">Stockage</span> <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="\ArchSI_Td_Angular\shop.html?category=Disque-dur">Disque dur</a></li>
						<li><a href="\ArchSI_Td_Angular\shop.html?category=Cl�-USB">Clé USB</a></li>
						<li><a href="\ArchSI_Td_Angular\shop.html?category=Accessoires-stockage">Accessoires</a></li>
					</ul>
				</li>	

			</div>	
        </ul>
		<div class="collapse navbar-collapse navbar-right navbar-main-collapse">
        <ul class="nav navbar-nav">

	    <!-- <li><a href="index.php?url=deconnexion">Déconnexion</a></li> -->
	  
	  <li><a href="\ArchSI_Td_Angular\connection.html">Connexion</a></li>
	  
	
        </ul>
      <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
  </nav>

<div class="container">

	<OBJECT DATA="/ArchSI_Td_Angular/rest/shop/infos" STYLE="width:100%;"></OBJECT>

</div>

  <footer>
    <div class="container">
      <div class="row">
        <div class="col-md-12 col-lg-12">
          <div class="col-lg-4">
            <h4>Siège Social NBHT</h4>
            <address>
				  92-98 Boulevard Victor Hugo<br>
				  92110 Clichy<br>
			</address>
			</div>
			<div class="col-lg-4">
            <address>
				<abbr title="Phone">Tel :</abbr> (+33) 6 05 40 86 90<br><br>
				<abbr title="Email">Email : notreboutiquehightech@gmail.com</abbr> <a href="mailto:villagesvacancesalpes@gmail.com">notreboutiquehightech@gmail.com</a><br>	  
			</address>
			</div>
			<div align="center" class="col-lg-4">
            <address>
			    <strong>Nous sommes sur les réseaux</strong><br>
					<ul class="company-social">
						<li class="social-facebook"><a href="#" target="_blank"><i class="fa fa-facebook"></i></a></li>
						<li class="social-twitter"><a href="#" target="_blank"><i class="fa fa-twitter"></i></a></li>
					</ul>
			</address>
			</div>
        </div>
      </div>
    </div>
	
	
  </footer>

  <!-- Core JavaScript Files -->
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/wow.min.js"></script>
  <!-- Custom Theme JavaScript -->
  <script src="js/custom.js"></script>

</body>

</html>