//
// Blackjack (client)
// Autori: Sebastiano Barezzi e Marco Zurlini
//

import { Blackjack, Player } from './libblackjack.js';

const dealerCards = document.getElementById("dealerCards");
const dealerScoreTextbox = document.getElementById("dealerScoreTextbox");
const hitUserButton = document.getElementById("hitUserButton");
const messageTextbox = document.getElementById("messageTextbox");
const newGameButton = document.getElementById("newGameButton");
const playerCards = document.getElementById("playerCards");
const playerScoreTextbox = document.getElementById("playerScoreTextbox");
const standUserButton = document.getElementById("standUserButton");

function toggleButtons(enabled) {
	hitUserButton.disabled = !enabled;
	standUserButton.disabled = !enabled;
}

let socket = io();
socket.on('update', function (data) {
	// Deserializza i dati ricevuti dal server
	let game = Blackjack.fromJson(data);

	// Disattiva i pulsanti se il turno dell'utente è finito
	toggleButtons(!game.userEnded);

	// Aggiorna le carte del daler
	dealerCards.innerHTML = game.dealer.cards.map((card) => card.getHtml()).join("");
	let dealerScore = game.dealer.getScore();
	if (dealerScore == -1) {
		dealerScore = "?";
	}
	dealerScoreTextbox.innerText = "Punti: " + dealerScore;

	// Aggiorna le carte del giocatore
	playerCards.innerHTML = game.user.cards.map((card) => card.getHtml()).join("");
	playerScoreTextbox.innerText = "Punti: " + game.user.getScore();

	// Aggiorna il messaggio
	if (game.gameEnded) {
		let winner = "Sconosciuto";
		if (game.winner == Player.USER) {
			winner = "Giocatore";
		} else if (game.winner == Player.DEALER) {
			winner = "Dealer";
		}
		messageTextbox.innerText = "Vincitore: " + winner + " (" + game.winReason + ")";
	} else if (game.userEnded) {
		messageTextbox.innerText = "Turno del dealer";
	} else {
		messageTextbox.innerText = "Turno del giocatore";
	}
});

// Imposta i callback dei button
hitUserButton.onclick = () => socket.emit('hit');
newGameButton.onclick = () => socket.emit('newGame');
standUserButton.onclick = () => socket.emit('stand');
