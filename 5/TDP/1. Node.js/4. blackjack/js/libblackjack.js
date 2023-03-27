//
// libblackjack (shared between client and server)
// Autori: Sebastiano Barezzi e Marco Zurlini
//

// Il punteggio massimo che si può raggiungere con le carte.
const MAX_VALORE_CARTE = 21;

/**
 * Una classe che rappresenta una carta da gioco.
 */
export class Card {
	static _counter = 0;
	static ALL_CARDS = [];
	static NUM_CARTE = 13;

	static ASSO = new Card("Asso", "A", 11);
	static DUE = new Card("Due", "2", 2);
	static TRE = new Card("Tre", "3", 3);
	static QUATTRO = new Card("Quattro", "4", 4);
	static CINQUE = new Card("Cinque", "5", 5);
	static SEI = new Card("Sei", "6", 6);
	static SETTE = new Card("Sette", "7", 7);
	static OTTO = new Card("Otto", "8", 8);
	static NOVE = new Card("Nove", "9", 9);
	static DIECI = new Card("Dieci", "10", 10);
	static JACK = new Card("Jack", "J", 10);
	static REGINA = new Card("Regina", "Q", 10);
	static RE = new Card("Re", "K", 10);
	static HIDDEN = new Card("Hidden", "?", 0);

	/**
	 * Ottieni una carta random.
	 */
	static getRandom() {
		let cardNumber = Math.floor(Math.random() * this.NUM_CARTE);
		return this.fromEnum(cardNumber);
	}

	/**
	 * Costruttore.
	 */
	constructor(nome, simbolo, valore) {
		this.nome = nome;
		this.simbolo = simbolo;
		this.valore = valore;

		this.enum = this.constructor._counter++;
		this.constructor.ALL_CARDS.push(this);
	}

	/**
	 * Ottieni una stringa contenente il codice HTML per visualizzare la carta.
	 */
	getHtml() {
		if (this == this.constructor.HIDDEN) {
			return '<div class="card hidden"></div>';
		} else {
			return '' +
				'<div class="card">' +
				'	<p class="card-top">' + this.simbolo + '</p>' +
				'	<p class="card-bottom">' + this.simbolo + '</p>' +
				'</div>';
		}
	}

	/**
	 * Ottieni un'oggetto carta dato il valore enum.
	 * Usato per deserializzare.
	 */
	static fromEnum(enumValue) {
		for (let card of this.ALL_CARDS) {
			if (card.enum == enumValue) {
				return card;
			}
		}

		throw "Invalid card enum " + enumValue;
	}
}

/**
 * Una classe che rappresenta un giocatore.
 */
export class Player {
	static USER = 0;
	static DEALER = 1;

	/**
	 * Costruttore.
	 */
	constructor(name, type) {
		this.name = name;
		this.type = type;
		this.cards = [];
	}

	/**
	 * Deserializza un giocatore da un dizionario in un'istanza.
	 * Viene usato dal client per ottenere le informazioni sul giocatore.
	 */
	static fromJson(json) {
		let player = new Player(json.name, json.type);
		player.cards = json.cards.map(c => Card.fromEnum(c));
		return player;
	}

	/**
	 * Serializza un giocatore dall'istanza in un dizionario.
	 * Viene usato dal server per inviare le informazioni sul giocatore.
	 * Si occupa di nascondere la seconda carta del dealer se il turno
	 * dell'utente non è finito.
	 */
	toJson(userEnded) {
		let cards = this.cards;
		if (this.type == Player.DEALER && !userEnded) {
			cards = [cards[0], Card.HIDDEN];
		}

		return {
			name: this.name,
			type: this.type,
			cards: cards.map(c => c.enum)
		};
	}

	/**
	 * Pesca una carta random.
	 */
	hit() {
		this.cards.push(Card.getRandom());
	}

	/**
	 * Calcola il valore delle carte.
	 */
	getScore() {
		let value = 0;
		let numAssi = 0;

		for (let i = 0; i < this.cards.length; i++) {
			value += this.cards[i].valore;
			if (this.cards[i] == Card.ASSO) {
				numAssi += 1;
			}
		}

		// Considera gli assi come 1 se sforiamo
		if (value > MAX_VALORE_CARTE) {
			while (numAssi > 0) {
				value -= 10;
				if (value <= MAX_VALORE_CARTE) {
					break;
				}
				numAssi--;
			}
		}

		return value;
	}

	/**
	 * Controlla se il giocatore ha sforato.
	 */
	hasBusted() {
		return this.getScore(this.cards) > MAX_VALORE_CARTE;
	}
}

/**
 * Una classe che rappresenta una sessione di gioco.
 */
export class Blackjack {
	/**
	 * Costruttore.
	 */
	constructor() {
		this.user = new Player("Utente", Player.USER);
		this.dealer = new Player("Dealer", Player.DEALER);

		this.userEnded = false;
		this.gameEnded = false;
		this.winner = null;
		this.winReason = null;

		// Il giocatore deve avere almeno 1 carta
		this.hitUser();

		// Il dealer inizia con una carta coperta e una scoperta
		this.hitDealer();
		this.hitDealer();
	}

	/**
	 * Deserializza una sessione di gioco da un dizionario in un'istanza.
	 * Viene usato dal client per ottenere le informazioni sulla partita.
	 */
	static fromJson(json) {
		let game = new Blackjack();
		game.user = Player.fromJson(json.user);
		game.dealer = Player.fromJson(json.dealer);
		game.userEnded = json.userEnded;
		game.gameEnded = json.gameEnded;
		game.winner = json.winner;
		game.winReason = json.winReason;
		return game;
	}

	/**
	 * Serializza una sessione di gioco dall'istanza in un dizionario.
	 * Viene usato dal server per inviare le informazioni sulla partita.
	 */
	toJson() {
		return {
			user: this.user.toJson(),
			dealer: this.dealer.toJson(this.userEnded),
			userEnded: this.userEnded,
			gameEnded: this.gameEnded,
			winner: this.winner,
			winReason: this.winReason
		};
	}

	/**
	 * Fai pescare all'utente una carta.
	 */
	hitUser() {
		this.user.hit();

		// Controlla che il giocatore non abbia perso
		if (this.user.hasBusted()) {
			this.winner = Player.DEALER;
			this.winReason = "il giocatore ha sforato";
			this.endGame();
			return;
		}

		// Se il giocatore ha fatto 21 punti, fermalo
		if (this.user.getScore() == 21) {
			this.standUser();
			return;
		}
	}

	/**
	 * Fai fermare l'utente.
	 */
	standUser() {
		// Indica che il giocatore ha finito
		this.userEnded = true;

		// Fai giocare il dealer
		this.playDealer();
	}

	/**
	 * Fai pescare al dealer una carta.
	 */
	hitDealer() {
		this.dealer.hit();
	}

	/**
	 * Fai giocare il dealer.
	 */
	playDealer() {
		while (true) {
			// Controlla che il dealer non abbia perso
			if (this.dealer.hasBusted()) {
				this.winner = Player.USER;
				this.winReason = "il dealer ha sforato";
				break;
			}

			// Facciamo fermare il dealer se ha 17 o più
			var value = this.dealer.getScore();
			if (value >= 17) {
				if (value >= this.user.getScore()) {
					this.winner = Player.DEALER;
				} else {
					this.winner = Player.USER;
				}
				this.winReason = "per punti";
				break;
			}

			// Pesca una carta
			this.hitDealer();
		}

		// Anche il turno del dealer è finito, termina la partita
		this.endGame();
	}

	/**
	 * Termina la partita.
	 */
	endGame() {
		// Indica che il giocatore ha finito
		this.userEnded = true;

		// Indica che la partita è finita
		this.gameEnded = true;
	}
}
