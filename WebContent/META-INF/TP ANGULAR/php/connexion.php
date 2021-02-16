<div style="background-image: url('img/bg4.jpg');background-repeat: no-repeat; background-size: 100%;">
<section class="home-section text-center">
	<div style="padding-top:8%;" class="container">
	<div class="col-lg-4">
	</div>
        <div class="col-lg-4 col-sm-4 well">
            <form action="php/Connec.php"class="form-horizontal" id="loginform" name="loginform" method=POST accept-charset="utf-8">
            <fieldset>
               <div class="section-heading">
                <h4>Connectez-vous</h4>
                <i class="fa fa-2x fa-angle-down"></i>

              </div>
               <div class="form-group">
               <div class="row colbox">
               <div class="col-lg-4 col-sm-4">
                    <label for="txt_username" class="control-label">Adresse Mail</label>
               </div>
               <div class="col-lg-8 col-sm-8">
                    <input class="form-control" id="txt_username" name="txt_username" placeholder="" type="text" value="" required/>
                    <span class="text-danger"></span>
               </div>
               </div>
               </div>
               
               <div class="form-group">
               <div class="row colbox">
               <div class="col-lg-4 col-sm-4">
               <label for="txt_password" class="control-label">Mot de passe</label>
               </div>
               <div class="col-lg-8 col-sm-8">
                    <input class="form-control" id="txt_password" name="txt_password" placeholder="" type="password" value="" required/>
                    <span class="text-danger"></span>
               </div>
               </div>
               </div>
                              
               <div class="form-group">
               <div class="col-lg-12 col-sm-12 text-center">
                     <button type="submit" class="btn btn-skin pull-right" id="btnContactUs">
                            Connexion</button>
               </div>
               </div>
            </fieldset>
            </form>	
			<?php if (isset($_GET["message"])){
				echo '<font color="red">'.$_GET["message"].'</font>';
			}?>
		</div>
		<div class="col-lg-4">
	</div>
</div>
</section>
<div style="padding-top:3.5%;">
</div>
</div>