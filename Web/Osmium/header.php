<!doctype html>
<html lang="pt-BR">
	<head>
		<script src="https://kit.fontawesome.com/aa667bfc10.js" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
		
		<link rel="stylesheet" href="css/scripts.css">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title id="title">
			<?php echo $title ?>
		</title>
		<meta name="description" content="TCC - Osmium">
		
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<ul class="navbar-nav mr-auto dropdown">
				<li class="nav-item dropdown">
					<a class="nav-link active" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<i class="fa-solid fa-bars fa-2x"></i>
					</a>
					<div class="dropdown-menu bg-dark" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item text-white" href="javascript:void(0);" onclick='irPara("home.php", 1)'>Início</a>
						<a class="dropdown-item text-white" href="javascript:void(0);" onclick='irPara("sobre.php", 2);'>Sobre</a>
						<a class="dropdown-item text-white" href="javascript:void(0);" onclick='irPara("contato.php", 3);'>Contato</a>
						<a class="dropdown-item text-white" href="javascript:void(0);" onclick='irPara("atualizacoes.php", 4);'>Atualizações</a>
						<a class="dropdown-item text-white" href="javascript:void(0);" onclick='irPara("equipe.php", 5);'>Equipe</a>
					</div>
				</li>
			</ul>
			<a class="navbar-brand" href="/tcc-osmium/Web/Osmium/" style="margin-left: 20px;"><img src="img/logo3b.png" style="width:40px"></a>	
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto" rel="stylesheet" href="styles.css">
					<li class="navdiv nav-item active">
						<a class="nav-link" href="/tcc-osmium/Web/Osmium/">Início</a>
					</li>
					<li class="navdiv nav-item active">
						<a class="nav-link" href="javascript:void(0);" onclick='irPara("sobre.php", 2)'>Sobre</a>
					</li>
					<li class="navdiv nav-item active">
						<a class="nav-link" href="javascript:void(0);" onclick='irPara("contato.php", 3)'>Contato</a>
					</li>
					<li class="navdiv nav-item active">
						<a class="nav-link" href="javascript:void(0);" onclick='irPara("atualizacoes.php", 4)'>Atualizações</a>
					</li>
					<li class="navdiv nav-item active">
						<a class="nav-link" href="javascript:void(0);" onclick='irPara("equipe.php", 5)'>Equipe</a>
					</li>
				</ul>
			</div>
			<div class="float-right" style="margin-right:10px">
				<button type="button" class="btn btn-danger" onclick="lastPage()"><i class="fa-solid fa-arrow-left"></i></button>
			</div>
			<div class="float-right">
				<button type="button" class="btn btn-success" onclick="nextPage()"><i class="fa-solid fa-arrow-right"></i></button>
			</div>
			
		</nav>
	</head>