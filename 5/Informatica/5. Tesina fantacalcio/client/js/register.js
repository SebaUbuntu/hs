import { API_URL, getInfoUtente, goToLogin } from "./lib.js";

let loginLinkElement = document.getElementById("login-link");
let registerFormElement = document.getElementById("register-form");

async function main() {
	let params = new URLSearchParams(window.location.search);

	let redirect = params.has("redirect") ? params.get("redirect") : "index.html";

	// Se l'utente è già loggato, reindirizzalo alla pagina di destinazione
	let utente = await getInfoUtente();
	if (utente) {
		location.href = redirect;
		return;
	}

	loginLinkElement.addEventListener("click", (event) => {
		event.preventDefault();

		goToLogin(redirect);
	});

	registerFormElement.addEventListener("submit", async (event) => {
		event.preventDefault();

		let username = document.getElementById("username").value;
		let password = document.getElementById("password").value;
		let nome = document.getElementById("nome").value;
		let cognome = document.getElementById("cognome").value;
		let email = document.getElementById("email").value;

		let response = await fetch(`${API_URL}/register.php`, {
			method: "POST",
			body: JSON.stringify({
				username,
				password,
				nome,
				cognome,
				email,
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
