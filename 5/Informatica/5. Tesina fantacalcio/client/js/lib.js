//
// Libreria fantacalcio
//

/**
 * URL della API
 */
export const API_URL = "https://webuser.itis.pr.it/~sBAREZZI/fantacalcio/server"

/**
 * Ottiene le informazioni dell'utente loggato
 * @returns {Promise<Object>} Le informazioni dell'utente loggato
 */
export async function getInfoUtente() {
	let response = await fetch(`${API_URL}/get_login_info.php`);
	if (!response.ok) {
		return null;
	}

	let utente = await response.json();
	return utente;
}

/**
 * Imposta il titolo della pagina
 * @param {string} title Il titolo della pagina
 */
export function setTitoloPagina(title) {
	document.title = `${title} - Fantacalcio`;
}

const IMG_ROOT = "https://sport.virgilio.it/img/loghi"
const FIX_SQUADRA_IMG = {
	"Hellas Verona": "verona"
}

/**
 * Ottieni l'URL del logo della squadra
 * @param {string} nomeSquadra nome della squadra
 * @returns {string} URL del logo della squadra
 */
export function getLogoSquadra(nomeSquadra) {
	return `${IMG_ROOT}/${FIX_SQUADRA_IMG[nomeSquadra] || nomeSquadra.toLowerCase()}.svg`
}

/**
 * Ottieni informazioni su un utente
 * @param {number} utenteId ID dell'utente
 * @returns {Promise<Object>} Le informazioni dell'utente
 */
export async function getUtente(utenteId) {
	let response = await fetch(`${API_URL}/get_utente.php?utente_id=${utenteId}`);
	if (!response.ok) {
		return null;
	}

	let utente = await response.json();
	return utente;
}

/**
 * Ottieni una mappa che mappa l'ID di una squadra al suo nome
 * @returns {Promise<Object>} La mappa che mappa l'ID di una squadra al suo nome
 */
export async function getSquadraIdToNome() {
	let response = await fetch(`${API_URL}/get_squadre.php`);
	if (!response.ok) {
		return null;
	}

	let squadre = await response.json();

	let squadra_id_to_nome = {};
	for (let squadra of squadre) {
		squadra_id_to_nome[squadra.squadra_id] = squadra.nome;
	}

	return squadra_id_to_nome;
}

/**
 * Lista di ruoli in ordine
 */
export const ORDINE_POSIZIONI_GIOCATORI = ["P", "D", "C", "A"];

/**
 * Comparatore per ordinare una lista di giocatori
 * @param {object} a il primo giocatore
 * @param {object} b il secondo giocatore
 * @returns {number} -1 se a viene prima di b, 1 se b viene prima di a, 0 se sono uguali
 */
export function defaultGiocatoriComparator(a, b) {
	return false ? 0 :
		// Posizione
		(a.posizione != b.posizione) ? ORDINE_POSIZIONI_GIOCATORI.indexOf(a.posizione) - ORDINE_POSIZIONI_GIOCATORI.indexOf(b.posizione) :
		// Crediti finali
		(a.crediti_finali != b.crediti_finali) ? b.crediti_finali - a.crediti_finali :
		// Nome
		(a.cognome_nome != b.cognome_nome) ? a.cognome_nome.localeCompare(b.cognome_nome) :
		0;
}

/**
 * Reindirizza l'utente alla pagina di login, con un parametro
 * che indica la pagina a cui reindirizzare l'utente dopo il login.
 */
export function goToLogin() {
	let params = new URLSearchParams(window.location.search);

	let redirect = params.has("redirect") ? params.get("redirect") : window.location.href;

	window.location.href = `login.html?redirect=${encodeURIComponent(redirect)}`;
}

/**
 * Reindirizza l'utente alla pagina di registrazione, con un parametro
 * che indica la pagina a cui reindirizzare l'utente dopo il login.
 */
export function goToRegister() {
	let params = new URLSearchParams(window.location.search);

	let redirect = params.has("redirect") ? params.get("redirect") : window.location.href;

	window.location.href = `register.html?redirect=${encodeURIComponent(redirect)}`;
}
