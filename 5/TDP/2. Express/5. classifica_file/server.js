const bodyParser = require('body-parser');
const express = require('express');
const fs = require('fs');

const app = express();
app.use(bodyParser.urlencoded({ extended: true }));

app.set('view engine', 'pug');
app.set('views', './views');

let classifica = [];
if (fs.existsSync('classifica.json')) {
	classifica = JSON.parse(fs.readFileSync('classifica.json'));
} else {
	classifica = [
		{ squadra: 'Atalanta', punti: 0, partiteGiocate: 0 },
		{ squadra: 'Bologna', punti: 0, partiteGiocate: 0 },
		{ squadra: 'Cremonese', punti: 0, partiteGiocate: 0 },
		{ squadra: 'Empoli', punti: 0, partiteGiocate: 0 },
		{ squadra: 'Fiorentina', punti: 0, partiteGiocate: 0 },
		{ squadra: 'Hellas Verona', punti: 0, partiteGiocate: 0 },
		{ squadra: 'Inter', punti: 0, partiteGiocate: 0 },
		{ squadra: 'Juventus', punti: 0, partiteGiocate: 0 },
		{ squadra: 'Lazio', punti: 0, partiteGiocate: 0 },
		{ squadra: 'Lecce', punti: 0, partiteGiocate: 0 },
		{ squadra: 'Milan', punti: 0, partiteGiocate: 0 },
		{ squadra: 'Monza', punti: 0, partiteGiocate: 0 },
		{ squadra: 'Napoli', punti: 0, partiteGiocate: 0 },
		{ squadra: 'Roma', punti: 0, partiteGiocate: 0 },
		{ squadra: 'Salernitana', punti: 0, partiteGiocate: 0 },
		{ squadra: 'Sampdoria', punti: 0, partiteGiocate: 0 },
		{ squadra: 'Sassuolo', punti: 0, partiteGiocate: 0 },
		{ squadra: 'Spezia', punti: 0, partiteGiocate: 0 },
		{ squadra: 'Torino', punti: 0, partiteGiocate: 0 },
		{ squadra: 'Udinese', punti: 0, partiteGiocate: 0 },
	];
}

app.get('/', (req, res) => {
	res.render('index', { title: 'Home page' });
});

app.get('/form', (req, res) => {
	res.render('form', { title: 'Inserisci risultati', squadre: classifica.map((squadra) => squadra.squadra) });
});

app.post('/form', (req, res) => {
	const { squadraCasa, squadraOspite, goalCasa, goalOspite } = req.body;

	// Validazione dei dati
	if (squadraCasa === squadraOspite) {
		res.render('error', { title: 'Errore', error: 'Le squadre non possono essere uguali' });
		return;
	}

	if (goalCasa < 0 || goalOspite < 0) {
		res.render('form', { title: 'Errore', error: 'I goal non possono essere negativi' });
		return;
	}

	// Aggiorna la classifica
	const casa = classifica.find((squadra) => squadra.squadra === squadraCasa);
	const ospite = classifica.find((squadra) => squadra.squadra === squadraOspite);

	if (goalCasa > goalOspite) {
		casa.punti += 3;
	} else if (goalCasa < goalOspite) {
		ospite.punti += 3;
	} else {
		casa.punti += 1;
		ospite.punti += 1;
	}

	casa.partiteGiocate += 1;
	ospite.partiteGiocate += 1;

	// Ordina la classifica
	classifica.sort((a, b) => b.punti - a.punti);

	// Salva i dati su file come JSON
	fs.writeFileSync('classifica.json', JSON.stringify(classifica));

	res.render('riepilogo', { title: 'Riepilogo', squadraCasa: squadraCasa, squadraOspite: squadraOspite, goalCasa: goalCasa, goalOspite: goalOspite });
});

app.get('/classifica', (req, res) => {
	res.render('classifica', { title: 'Classifica', classifica });
});

app.listen(3000, () => {
	console.log('http://localhost:3000');
});
