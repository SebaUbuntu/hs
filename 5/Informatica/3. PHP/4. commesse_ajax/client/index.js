let API_URL = "../server/controller_commesse.php";

let contenuto = document.getElementById("contenuto");

function emptyContenuto() {
	contenuto.innerHTML = "<p>Caricamento...</p>";
}

async function getCommesse() {
	emptyContenuto();

	let	response = await fetch(`${API_URL}?get=get_commesse`);
	if (!response.ok) {
		contenuto.innerHTML = `Errore ${response.status}: ${response.statusText}`;
		return;
	}

	let commesse = await response.json();

	let output = `
	<h1>Commesse</h1>

	<a href="javascript:insertCommessa();">
		<span class="material-symbols-outlined">add</span>
	</a>
	<br>

	<table>
	<tr>
		<th>Data commessa</th>
		<th>Descrizione</th>
		<th>Richiedente</th>
		<th>Reparto</th>
		<th>Tipo intervento</th>
		<th>Modifica</th>
		<th>Elimina</th>
	</tr>
	`;

	commesse.forEach(function (commessa) {
		output += `
		<tr>
			<td>${commessa.data_commessa}</td>
			<td>${commessa.descrizione}</td>
			<td>${commessa.richiedente_nominativo}</td>
			<td>${commessa.reparto_descrizione}</td>
			<td>${commessa.tipo_intervento_descrizione}</td>
			<td align='center'><a href='javascript:editCommessa(${commessa.id_commessa});'><button style="width:100%">Modifica</button></a></td>
			<td align='center'><a href='javascript:deleteCommessa(${commessa.id_commessa});'><button style="width:100%">Elimina</button></a></td>
		</tr>
		`;
	});

	output += "</table>";

	contenuto.innerHTML = output;
}

async function editCommessa(idCommessa) {
	emptyContenuto();

	let data = {
		"commessa": null,
		"reparti": null,
		"tipi_intervento": null,
		"utenti": null,
	};

	for (const [key, _] of Object.entries(data)) {
		let response = await fetch(`${API_URL}?get=get_${key}&id_commessa=${idCommessa}`);
		if (!response.ok) {
			contenuto.innerHTML = `Errore ${response.status}: ${response.statusText}`;
			return;
		}

		data[key] = await response.json();
	}

	output = `
	<h1>Modifica commessa n. ${data.commessa.id}</h1>

	<form id="formEditCommessa" onsubmit="return sendEditCommessa();">
		<input type="hidden" name="id_commessa" id="id_commessa" value='${data.commessa.id}'/>

		<label for="data_commessa">Data commessa (yyyy-mm-gg)</label>
		<input type="text" name="data_commessa" id="data_commessa" value='${data.commessa.data}'/>
		<br>

		<label for="descrizione">Descrizione</label>
		<input type="text" name="descrizione" id="descrizione" value='${data.commessa.descrizione}'/>
		<br>

		<label for="id_richiedente">Richiedente</label>
		<select id="id_richiedente" name="id_richiedente">
			${
				data.utenti.map(
					(utente) =>
						`<option value='${utente.id}' ${
							data.commessa.id_richiedente == utente.id ? "selected" : ""
						}>${utente.nominativo}</option>`
				)
			}
		</select>
		<br>

		<label for="id_reparto">Reparto</label>
		<select id="id_reparto" name="id_reparto">
			${
				data.reparti.map(
					(reparto) =>
						`<option value='${reparto.id}' ${
							data.commessa.id_reparto == reparto.id ? "selected" : ""
						}>${reparto.descrizione}</option>`
				)
			}
		</select>
		<br>

		<label for="id_tipo_intervento">Tipo intervento</label>
		<select id="id_tipo_intervento" name="id_tipo_intervento">
			${
				data.tipi_intervento.map(
					(tipo_intervento) =>
						`<option value='${tipo_intervento.id}' ${
							data.commessa.id_tipo_intervento == tipo_intervento.id ? "selected" : ""
						}>${tipo_intervento.descrizione}</option>`
				)
			}
		</select>
		<br>

		<input type="submit" name="salva" id="salva" value="Salva" />
	</form>
	`;

	contenuto.innerHTML = output;
}

async function sendEditCommessa() {
	let form = document.getElementById("formEditCommessa");

	let data = new URLSearchParams(new FormData(form));
	data.append("get", "update_commessa");

	emptyContenuto();

	let response = await fetch(`${API_URL}`, {
		method: "POST",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded",
		},
		body: data,
	});
	if (!response.ok) {
		contenuto.innerHTML = `Errore ${response.status}: ${response.statusText}`;
	}

	await getCommesse();

	return false;
}

async function deleteCommessa(idCommessa) {
	emptyContenuto();

	let response = await fetch(`${API_URL}`, {
		method: "POST",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded",
		},
		body: `get=delete_commessa&id_commessa=${idCommessa}`,
	});
	if (!response.ok) {
		contenuto.innerHTML = `Errore ${response.status}: ${response.statusText}`;
	}

	await getCommesse();
}

async function getCommessePerTipoIntervento() {
	emptyContenuto();

	let response = await fetch(`${API_URL}?get=get_commesse_per_tipo_intervento`);
	if (!response.ok) {
		contenuto.innerHTML = `Errore ${response.status}: ${response.statusText}`;
		return;
	}

	let commesse = await response.json();

	let output = `
	<h1>Commesse per tipo intervento</h1>

	<table>
	<tr>
		<th>Tipo intervento</th>
		<th>Numero commesse</th>
	</tr>
	`;

	commesse.forEach(function (commessa) {
		output += `
		<tr>
			<td>${commessa.descrizione}</td>
			<td>${commessa.num_commesse}</td>
		</tr>
		`;
	});

	output += "</table>";

	contenuto.innerHTML = output;
}

async function insertCommessa() {
	emptyContenuto();

	let data = {
		"reparti": null,
		"tipi_intervento": null,
		"utenti": null,
	};

	for (const [key, _] of Object.entries(data)) {
		let response = await fetch(`${API_URL}?get=get_${key}`);
		if (!response.ok) {
			contenuto.innerHTML = `Errore ${response.status}: ${response.statusText}`;
			return;
		}

		data[key] = await response.json();
	}

	let today = new Date();

	output = `
	<h1>Inserisci commessa</h1>

	<form id="formInsertCommessa" onsubmit="return sendInsertCommessa();">
		<label for="data_commessa">Data commessa (yyyy-mm-gg)</label>
		<input type="text" name="data_commessa" id="data_commessa" value="${today.getFullYear()}-${today.getMonth()}-${today.getDate()}"/>
		<br>

		<label for="descrizione">Descrizione</label>
		<input type="text" name="descrizione" id="descrizione"/>
		<br>

		<label for="id_richiedente">Richiedente</label>
		<select id="id_richiedente" name="id_richiedente">
			${
				data.utenti.map(
					(utente) =>
						`<option value='${utente.id}'>${utente.nominativo}</option>`
				)
			}
		</select>
		<br>

		<label for="id_reparto">Reparto</label>
		<select id="id_reparto" name="id_reparto">
			${
				data.reparti.map(
					(reparto) =>
						`<option value='${reparto.id}'>${reparto.descrizione}</option>`
				)
			}
		</select>
		<br>

		<label for="id_tipo_intervento">Tipo intervento</label>
		<select id="id_tipo_intervento" name="id_tipo_intervento">
			${
				data.tipi_intervento.map(
					(tipo_intervento) =>
						`<option value='${tipo_intervento.id}'>${tipo_intervento.descrizione}</option>`
				)
			}
		</select>
		<br>

		<input type="submit" value="Inserisci" />
	</form>
	`;

	contenuto.innerHTML = output;
}

async function sendInsertCommessa() {
	let form = document.getElementById("formInsertCommessa");

	let data = new URLSearchParams(new FormData(form));
	data.append("get", "add_commessa");

	emptyContenuto();

	let response = await fetch(`${API_URL}`, {
		method: "POST",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded",
		},
		body: data,
	});
	if (!response.ok) {
		contenuto.innerHTML = `Errore ${response.status}: ${response.statusText}`;
	}

	await getCommesse();

	return false;
}

// Mostra la pagina delle commesse come pagina iniziale
getCommesse();
