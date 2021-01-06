<!DOCTYPE html>
<!--  <html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:p="http://java.sun.com/jsf/passthrough">-->
    <head>
	      <title>S'inscrire</title>
	      <meta charset="utf-8" name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
	      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	      <link rel="stylesheet" href="/E-learning-project/Etudiant/app.v2.css" type="text/css"/>
	      <link rel="stylesheet" href="/E-learning-project/Etudiant/font.css" type="text/css"/>
    </head>
    <body>
        <section id="content" class="m-t-lg wrapper-md animated fadeInUp">
         <a class="nav-brand">Ajouter un compte Admin/Prof</a> 
         <div class="row m-n">
            <div class="col-md-4 col-md-offset-4 m-t-lg">
               <section class="panel">
                  <header class="panel-heading text-center">  </header>
				        <form action="/E-learning-project/SignupAP" style="width:250px;margin: 0 auto;" method="post" >
								<div class="form-group"> <label class="control-label">Prenom :</label>
								<input type="text" name="prenom" id="nom" required />
								</div>
								<div class="form-group"> <label class="control-label">Nom :</label>
								<input type="text" name="nom" id="nom" required />
								</div>
								<div class="form-group"> <label class="control-label">Email :</label>
								<input type="email" name="email" id="nom" required />
								</div>
								<div class="form-group"> <label class="control-label">Telephone :</label>
								<input type="tel" name="tel" id="nom" required />
								</div>

								<div class="form-group"> <label class="control-label">Date de naissance :</label>
								<input type="date" name="dateNaissance" id="nom" required />
								</div>
								<div class="form-group"> <label class="control-label">Sexe :</label>
									<input type="radio" id="male" name="sexe" value="M" /><label for="male">Homme</label>
									<input type="radio" id="female" name="sexe" value="F" /><label for="female">Femme</label>
								</div>
								<div class="form-group"> <label class="control-label">Password</label> 
								<input type="password" name="password" id="nom" required />
	                            </div>
	                            <div class="form-group"> <label class="control-label">Statut :</label>
									<input type="radio" id="admin" name="user_type" value="A" /><label for="admin">Admin</label>
									<input type="radio" id="prof" name="user_type" value="P" /><label for="prof">Prof</label>
								</div>
	                        <input type="submit" value="Ajouter Compte" class="btn btn-info" style="float:left"/>
	                        <a href="profil" class="btn btn-info" style="float:right">Retour</a>
					    </form>
               </section>
            </div>
         </div>
      </section>
    </body>
</html>