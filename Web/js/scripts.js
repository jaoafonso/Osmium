function loadHTML(page) {
    fetch(page)
	.then(response => response.text())
	.then(text => document.getElementById('pagina').innerHTML = text);
}

loadHTML("home.html");

current_page = 1;
total_pages = 5;
document.getElementById("paginas").innerHTML = "Página " + current_page + " de " + total_pages;

function irPara(pagina, numpag) {
	document.getElementById("paginas").innerHTML = "Página " + numpag + " de " + total_pages;
	loadHTML(pagina);
	current_page = numpag;
}

function lastPage() {
	if(current_page >= 1){
		if(current_page > 1){
			current_page = current_page - 1;
		}
		console.log(current_page);
		document.getElementById("paginas").innerHTML = "Página " + current_page + " de " + total_pages;
		switch (current_page) {
			case 1:
			loadHTML("home.html")
			break;
			case 2:
			loadHTML("sobre.html")
			break;
			case 3:
			loadHTML("contato.html")
			break;
			case 4:
			loadHTML("atualizacoes.html")
			break;
			case 5:
			loadHTML("equipe.html")
			break;
		}
	}
}


function nextPage() {
	if(current_page < 5) {
		if(current_page < 5){
			current_page = current_page + 1;
		}
		console.log(current_page);
		document.getElementById("paginas").innerHTML = "Página " + current_page + " de " + total_pages;
		switch (current_page) {
			case 1:
			loadHTML("home.html")
			break;
			case 2:
			loadHTML("sobre.html")
			break;
			case 3:
			loadHTML("contato.html")
			break;
			case 4:
			loadHTML("atualizacoes.html")
			break;
			case 5:
			loadHTML("equipe.html")
			break;
		}
	}
}
