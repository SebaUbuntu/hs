import { API_URL, getInfoUtente } from "./lib.js";

let accettaInvitoFormElement = document.getElementById("accetta-invito-form");

async function main() {
	let utente = await getInfoUtente();
	if (!utente) {
		goToLogin();
		return;
	}

	let params = new URLSearchParams(window.location.search);

	if (!params.has('fantalega_id')) {
		accettaInvitoFormElement.innerHTML = "ID fantalega non specificato";
		return;
	}

	let fantalegaId = params.get('fantalega_id');

	accettaInvitoFormElement.addEventListener("submit", async (event) => {
		event.preventDefault();

		let nome_fantasquadra = document.getElementById("nome_fantasquadra").value;

		let response = await fetch(`${API_URL}/accetta_invito.php`, {
			method: "POST",
			body: JSON.stringify({
				fantalega_id: fantalegaId,
				nome_fantasquadra,
			})
		});

		if (response.ok) {
			location.href = `visualizza_fantalega.html?fantalega_id=${fantalegaId}`;
		} else if (response.status == 403) {
			alert("Non sei stato invitato a questa fantalega");
		} else {
			alert("Errore durante l'accettazione dell'invito");
		}
	});
}

main();
