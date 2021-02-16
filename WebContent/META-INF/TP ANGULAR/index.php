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

  <nav class=" navbar-custom navbar-fixed-top top-nav-collapse" role="navigation">
    <div class="container">
		<div class="navbar-header">
      <!-- Collect the nav links, forms, and other content for toggling -->
        <ul class="nav navbar-nav">
          <li><a href="index.php">Accueil</a></li>
			   <li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true"> <span class="nav-label">Ordinateurs</span> <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">PC Portable</a></li>
						<li><a href="#">PC de Bureau</a></li>
						<li><a href="#">Accessoires</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true"> <span class="nav-label">Téléphonies</span> <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Smart Phone</a></li>
						<li><a href="#">Tel Fixe</a></li>
						<li><a href="#">Accessoires</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true"> <span class="nav-label">Stockage</span> <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Disque dur</a></li>
						<li><a href="#">Clé USB</a></li>
						<li><a href="#">Accessoires</a></li>
					</ul>
				</li>	

			</div>	
        </ul>
		<div class="collapse navbar-collapse navbar-right navbar-main-collapse">
        <ul class="nav navbar-nav">
         
	  <?php 
	  if (isset($_SESSION['USER'])){
	   ?>
		  <li><a href="index.php?url=deconnexion">Déconnexion</a></li>
		  
	  <?php } else { ?>
	  
	  <li><a href="index.php?url=connexion">Connexion</a></li>
	  
	  <?php } ?>
        </ul>
      <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
  </nav>

<?php
if(isset($_GET['url']))
{
	switch ($_GET['url'])
	{
		case "connexion": include('php/connexion.php');
		break;
		case "deconnexion": include('php/deconnexion.php');
		break;
		case "pcportable": include('php/activites.php');
		break;
		case "pcbureau": include('php/gestionAct.php');
		break;
		case "accessordi": include('php/activites.php');
		break;
		case "accesstel": include('php/gestionAct.php');
		break;
		case "accessstock": include('php/activites.php');
		break;
		case "smartphone": include('php/gestionAct.php');
		break;
		case "telfixe": include('php/activites.php');
		break;
		case "disquedur": include('php/gestionAct.php');
		break;
		case "cleusb": include('php/gestionAct.php');
		break;
	}
}
else
{
	include('php/home.php');
}

?>

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
