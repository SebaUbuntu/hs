const cors = require('cors'); 
const cookieParser = require("cookie-parser");
const express = require("express");
const expressSessions = require('express-session');
const path = require('path');

const DEBUG = true;
const PORT = process.env.PORT || 3000;

/**
 * Array di utenti
 * @type {Array<{username: string, password: string, punti: number}>}
 */
let users = [];
function getUser(username) {
	return users.find(u => u.username === username);
}

// Creazione dell'applicazione
let app = express();

// Configura CORS
app.use(cors({
	origin: '*'
}));

// Configura JSON e body parser
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// Configura la cartella statica
app.use(express.static(path.join(__dirname, 'public')));

// Configura Pug
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');

// Configura le sessioni
app.use(expressSessions({
	name: "sessionId", 
	secret: "segreto",
	saveUninitialized: false,
	cookie: {
		secure: false,
		// 30 minuti
		maxAge: 1000 * 60 * 30,
	},
	resave: false,
}));

// Configura cookie parser
app.use(cookieParser());

// Middleware di autenticazione
const auth = function (req, res, next) {
	if (DEBUG) {
		console.log("Autenticazione richiesta, session: " + req.session.username);
	}

	// Verifica se l'utente è autenticato
	if (req.session.username) {
		next();
	} else {
		res.redirect("/login");
	}
}

app.get("/", auth, function (req, res) {
	res.render("index", { username: req.session.username });
});

app.get("/login", function (req, res) {
	if (req.session.username) {
		res.redirect("/");
	} else {
		res.render("login");
	}
});

app.post("/login", function (req, res) {
	users.forEach(user => {
		if (user.username === req.body.username && user.password === req.body.password) {
			if (DEBUG) {
				console.log("Utente autenticato: " + req.body.username);
			}

			req.session.username = req.body.username;
			res.redirect("/");
			return;
		}
	});

	if (!req.session.username) {
		res.status(401);
		res.send("Invalid username or password");
	}
});

app.get("/register", function (req, res) {
	if (req.session.username) {
		res.redirect("/");
	} else {
		res.render("register");
	}
});

app.post("/register", function (req, res) {
	const { username, password } = req.body;

	// Validazione
	if (!username || !password) {
		res.status(400);
		res.send("Invalid username or password");
		return;
	}

	// Verifica se l'utente esiste già
	const user = users.find(u => u.username === username);
	if (user) {
		res.status(400);
		res.send("User already exists");
		return;
	}

	if (DEBUG) {
		console.log("Nuovo utente: " + username);
	}

	// Aggiungi l'utente
	users.push({
		username: username,
		password: password,
		punti: 0,
	});
	req.session.username = username;

	res.redirect("/");
});

app.delete("/logout", function (req, res) {
	if (DEBUG) {
		console.log("Logout utente: " + req.session.username);
	}

	req.session.destroy();
	res.redirect("/");
});

app.get("/giochi", auth, function (req, res) {
	res.render("giochi", {username:req.session.username});
});

app.get("/snake", auth, function (req, res) {
	let punti = Math.floor(Math.random() * 101);
	getUser(req.session.username).punti += punti;

	res.render("snake", { punti: punti });
});

app.get("/classifica", auth, function (req, res) {
	let userWithMaxPoints = users[0];
	users.forEach(user => {
		if (user.punti > userWithMaxPoints.punti) {
			userWithMaxPoints = user;
		}
	});

	res.render("classifica", { userWithMaxPoints: userWithMaxPoints, utenti: users });
});

app.get("/home", auth, function (req, res) {
	users.forEach(user => {
		if (user.username == req.session.username) {
			user.punti += parseInt(req.query.punti);
			if (DEBUG) {
				console.log(user.punti);
			}
		}
	});

	res.render("giochi", { username: req.query.username });
});

// Avvio del server
const server = app.listen(PORT, function () {
	console.log("Server in ascolto su http://localhost:" + PORT);
});
