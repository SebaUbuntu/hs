import { API_URL, getInfoUtente, goToRegister } from "./lib.js";

let registerLinkElement = document.getElementById("register-link");
let loginFormElement = document.getElementById("login-form");

async function main() {
	let params = new URLSearchParams(window.location.search);

	let redirect = params.has("redirect") ? params.get("redirect") : "index.html";

	// Se l'utente è già loggato, reindirizzalo alla pagina di destinazione
	let utente = await getInfoUtente();
	if (utente) {
		location.href = redirect;
		return;
	}

	registerLinkElement.addEventListener("click", (event) => {
		event.preventDefault();

		goToRegister(redirect);
	});

	loginFormElement.addEventListener("submit", async (event) => {
		event.preventDefault();

		let username = document.getElementById("username").value;
		let password = document.getElementById("password").value;

		let response = await fetch(`${API_URL}/login.php`, {
			method: "POST",
			body: JSON.stringify({
				username,
				password,
			})
		});

		if (response.ok) {
			location.href = redirect;
		} else {
			alert("Errore durante la registrazione");
		}
	});
}

main();
