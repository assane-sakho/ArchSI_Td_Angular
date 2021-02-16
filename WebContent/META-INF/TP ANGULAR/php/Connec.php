<?php 
	session_start();
	$bdd = new PDO('mysql:host=127.0.0.1;dbname=gacti2','root','');

    //Une fois que le boutton connexion est enclenché
		$mailCo = $_POST['txt_username'];
		$mdpCo = $_POST['txt_password'];        
        //On vérifie que les deux champs sont complété
			$reqUser = $bdd->prepare("SELECT *
										FROM compte 
										WHERE ADRMAILCOMPTE=?
										AND MDP=?");
			$reqUser->execute(array($mailCo,$mdpCo));
			$userExist = $reqUser->rowCount();

			//Création des variable de session et connexion de l'utilisateur
			if($userExist == 1 )
			{
				$userInfo = $reqUser->fetch();
				$_SESSION['USER'] = $userInfo['USER'];
				$_SESSION['NOMCPTE'] = $userInfo['NOMCOMPTE'];
				$_SESSION['PRENOMCPTE'] = $userInfo['PRENOMCOMPTE'];
				$_SESSION['ADRMAILCPTE'] = $userInfo['ADRMAILCOMPTE'];
				$_SESSION['TYPECOMPTE'] = $userInfo['TYPEPROFIL'];
     
				header("location:../index.php");

				
			}
			else
			{
				$message = "Adresse mail ou mot de passe incorrect !";
				header("location:../index.php?url=connexion&message=$message");
				
			}
		

	
?>