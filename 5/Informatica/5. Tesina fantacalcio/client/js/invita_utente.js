import { API_URL, getInfoUtente } from "./lib.js";

let invitaUtenteForm = document.getElementById("invita-utente-form");

async function main() {
	let utente = await getInfoUtente();
	if (utente == null) {
		window.location.href = "login.html";
		return;
	}

	let params = new URLSearchParams(window.location.search);

	if (!params.has('fantalega_id')) {
		invitaUtenteForm.innerHTML = "ID fantalega non specificato";
		return;
	}

	let fantalega_id = params.get('fantalega_id');

	invitaUtenteForm.addEventListener("submit", async (event) => {
		event.preventDefault();

		let username_or_email = document.getElementById("username_or_email").value;

		let response = await fetch(`${API_URL}/invita_utente.php`, {
			method: "POST",
			body: JSON.stringify({
				fantalega_id,
				username_or_email,
			})
		});

		if (response.ok) {
			location.href = `visualizza_fantalega.html?fantalega_id=${fantalega_id}`;
		} else {
			alert("Errore durante l'invito dell'utente");
		}
	});
}

main();
