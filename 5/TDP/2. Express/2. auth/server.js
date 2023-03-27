const express = require("express");

// Array di utenti
let users = [
    {
        id: 0,
        name: "Sebastiano",
        surname: "Barezzi",
        email: "barezzisebastiano@gmail.com",
    },
    {
        id: 1,
        name: "John",
        surname: "Doe",
        email: "johndoe@gmail.com",
    },
]
// Token di autenticazione
const TOKEN = "auth123";

// Middleware di autenticazione
const auth = (req, res, next) => {
    const authorization = req.headers.authorization;

    if (authorization === TOKEN) {
        next();
    } else {
        res.status(401).json({ error: "Unauthorized" });
    }
}

// Crea l'applicazione Express
const app = express();
app.use(express.json());

// Crea una GET route per /users
app.get("/users", (req, res) => {
    res.json(users);
});

// Crea una GET route per /users/:id
app.get("/users/:id", (req, res) => {
    // Ottieni l'id dall'URL
    const id = req.params.id;

    // Cerca l'utente con l'id corrispondente
    const user = users.find((user) => user.id === parseInt(id));

    // Se non esiste, restituisci un errore
    if (!user) {
        return res.status(404).json({ error: "User not found" });
    }

    // Rispondi con l'utente
    res.json(user);
});

// Crea una POST route per /users
app.post("/users", auth, (req, res) => {
    // Ottieni l'utente dal body della richiesta
    const user = req.body;

    // Verifica che l'utente non sia vuoto
    if (!user) {
        return res.status(400).json({ error: "Invalid user" });
    }

    // Verifica che l'utente abbia tutti i campi necessari
    if (!user.name || !user.surname || !user.email) {
        return res.status(400).json({ error: "Invalid user" });
    }

    // Aggiungi l'utente all'array
    users.push(user);

    // Rispondi con l'utente
    res.json(user);
});

// Avvia il server
app.listen(3000, () => {
    console.log("Server started on port 3000");
});
