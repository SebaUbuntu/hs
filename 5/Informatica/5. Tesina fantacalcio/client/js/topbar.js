import { API_URL } from "./lib.js";

const topbarElement = document.getElementById("topbar");

const ROOT = "/~sBAREZZI/fantacalcio/client"

const PAGES = {
    "Home": `${ROOT}/`,
    "Squadre Serie A TIM": `${ROOT}/visualizza_squadre.html`,
    "Listone": `${ROOT}/listone.html`,
}

export async function showLogin() {
	// Crea un dialogo per poter fare login
	let dialog = document.createElement("dialog");
	dialog.innerHTML = `
		<style>
			form {
				display: flex;
				flex-direction: column;
			}
		</style>
		<form method="dialog">
			<label for="username">Username</label>
			<input id="username" type="text" placeholder="Username" required>
			<br>

			<label for="password">Password</label>
			<input id="password" type="password" placeholder="Password" required>
			<br>

			<button type="submit">Login</button>
		</form>
		<p>Non sei registrato? <a href="register.html">Registrati</a></p>
	`;
	document.body.appendChild(dialog);

	// Mostra il dialogo
	dialog.showModal();

	// Quando viene inviato il form, fai login
	dialog.querySelector("form").addEventListener("submit", async (event) => {
		event.preventDefault();

		let username = dialog.querySelector("input[type='text']").value;
		let password = dialog.querySelector("input[type='password']").value;

		let response = await fetch(`${API_URL}/login.php`, {
			method: "POST",
			body: JSON.stringify({
				username,
				password
			})
		});

		if (response.ok) {
			dialog.close();
			location.reload();
		} else {
			alert("Credenziali errate");
		}
	});

	// Quando viene cliccato il pulsante "Annulla", chiudi il dialogo
	dialog.addEventListener("cancel", () => {
		dialog.close();
	});

	// Focus sul campo username
	dialog.querySelector("input[type='text']").focus();

	// Chiudi il dialogo quando viene premuto ESC
	dialog.querySelector("form").addEventListener("keydown", (event) => {
		if (event.key == "Escape") {
			dialog.close();
		}
	});
}

export async function doLogout() {
	await fetch(`${API_URL}/logout.php`);

	window.location.reload();
}

async function injectTopbar() {
	// Ottieni informazioni sul login
	let loginInfo = null;

	let response = await fetch(`${API_URL}/get_login_info.php`);
	if (response.ok) {
		loginInfo = await response.json();
	}

    topbarElement.innerHTML = `
		<style>
			#topbar ul {
				list-style-type: none;
				margin: 0;
				padding: 0;
				overflow: hidden;
				background-color: #333;
			}

			#topbar li {
				display: inline-block;
				float: left;
			}

			#topbar li a {
				display: inline-block;
				color: white;
				text-align: center;
				padding: 14px 16px;
				text-decoration: none;
			}

			#topbar li a:hover:not(.active) {
				background-color: #111;
			}

			#topbar .active {
				background-color: #04AA6D;
			}
		</style>

		<ul>
			<li><a>Fantacalcio</a></li>

			${Object.keys(PAGES).map(page => `
				<li><a href="${PAGES[page]}" class="${window.location.pathname === PAGES[page] ? "active" : ""}">${page}</a></li>
			`).join("")}

			<li style="float:right;">
				${loginInfo ? `
					<a href="${ROOT}/account_info.html" class="${
						window.location.pathname === `${ROOT}/account_info.html` ? "active" : ""
					}">${loginInfo.nome} ${loginInfo.cognome} (@${loginInfo.username})</a>
					<a id="logoutButton">Logout</a>
				` : `
					<a id="loginButton">Login</a>
				`}
			</li>
		</ul>
	`;

	// Aggiungi un listener al pulsante di login
	let loginButton = document.getElementById("loginButton");
	if (loginButton) {
		loginButton.addEventListener("click", showLogin);
	}

	// Aggiungi un listener al pulsante di logout
	let logoutButton = document.getElementById("logoutButton");
	if (logoutButton) {
		logoutButton.addEventListener("click", doLogout);
	}
}

injectTopbar();
