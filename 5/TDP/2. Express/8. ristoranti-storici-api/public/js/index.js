const ristorantiElement = document.getElementById("ristoranti");

async function main() {
	let response = await fetch("api/ristoranti");
	if (!response.ok) {
		console.error("Errore durante il caricamento dei ristoranti");
		return;
	}

	let ristoranti = await response.json();

	if (ristoranti.length === 0) {
		ristorantiElement.innerHTML = "<p>Nessun ristorante trovato</p>";
		return;
	}

	ristorantiElement.innerHTML = "";
	ristoranti.forEach(ristorante => {
		ristorantiElement.innerHTML += `
			<div class="ristorante" onClick="window.location='ristorante.html?ristorante_id=${ristorante.ristorante_id}';">
				<h2 class="ristorante-nome">${ristorante.nome}</h2>
				<p class="ristorante-provincia">Provincia: ${ristorante.provincia}</p>
				<p class="ristorante-indirizzo">Indirizzo: ${ristorante.indirizzo}</p>
				<p class="ristorante-anno-apertura">Anno di apertura: ${ristorante.anno_apertura}</p>
				<p class="ristorante-specialita">Specialità: ${ristorante.specialita}</p>
			</div>
		`
	});
}

main();
