// Dati da inviare al server
const options = {
    method: 'POST',
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'authorization': 'auth123'
    },
    body: JSON.stringify({
        id: 2,
        name: "Mario",
        surname: "Rossi",
        email: "mariorossi@gmail.com"
    })
}

async function main() {
    try {
        let resp = await fetch('http://localhost:3000/users', options)

        let res = await resp.json();

        console.log(res);
    } catch (error) {
        console.log('error:', error);
    }
}

main().then(() => console.log('done'));
