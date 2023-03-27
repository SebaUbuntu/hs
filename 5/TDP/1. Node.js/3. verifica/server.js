const fs = require("fs");
const http = require("http");
const url = require("url");

const DEBUG = true;

const PORT = 3000;

function requestHandler(request, response) {
    let oggettourl = url.parse(request.url, "false");
    if (DEBUG) console.log('href: ' + oggettourl.href);
    const path = oggettourl.pathname;
    if (DEBUG) console.log('pathname: ' + path);

    switch (path) {
        case '/':
            fs.readFile('index.html', function (error, data) {
                if (error) {
                    response.writeHead(404);
                }
                else {
                    response.writeHead(200, { "content-Type": "text/html" });
                    response.write(data, "utf8");
                }

                response.end();
            });
            break;

        case '/recuperadati':
            let query = oggettourl.query;

            response.writeHead(200, { "content-Type": "text/plain" });

            if (DEBUG) {
                console.log(query);

                response.write('First name: ' + query.first_name);
                response.write('Last name: ' + query.last_name);
                response.write('\nEmail: ' + query.email);
                response.write('\nPrefisso: ' + query.prefisso);
                response.write('\nNumero di telefono: ' + query.numero_telefono);
                response.write('\nTipo di donazione: ' + query.tipo_donazione);
                response.write('\nImporto: ' + query.importo);
                response.write('\nCommenti: ' + query.commenti);
                response.write('\n\n')
            }

            const datiErrati = []
            if (query.first_name == null || query.first_name.length <= 0) {
                datiErrati.push("First name")
            }
            if (query.last_name == null || query.last_name.length <= 0) {
                datiErrati.push("Last name")
            }
            let split_email = query.email.split("@")
            if (query.email == null || query.email.length <= 0 || split_email.length != 2 || split_email[0].length <= 0 || split_email[1] <= 0) {
                datiErrati.push("Email")
            }
            if (query.prefisso == null || query.prefisso.length <= 0 || isNaN(parseInt(query.prefisso)) || parseInt(query.prefisso) < 0) {
                datiErrati.push("Prefisso")
            }
            if (query.numero_telefono == null || query.numero_telefono.length <= 0 || isNaN(parseInt(query.numero_telefono)) || parseInt(query.numero_telefono) < 0) {
                datiErrati.push("Numero di Telefono")
            }
            if (["Donazione amorevole", "Ampliamento dell'edificio", "Donazione una tantum"].indexOf(query.tipo_donazione) == -1) {
                datiErrati.push("Tipo di Donazione")
            }
            if (query.importo == null || isNaN(parseInt(query.importo)) || parseFloat(query.importo) <= 0.0) {
                datiErrati.push("Importo")
            }
            if (datiErrati.length > 0) {
                response.end("Dati errati: " + datiErrati.join(", "));
            } else {
                response.end('Dati ricevuti');
            }

            break;
    }
}

const server = http.createServer(requestHandler);
server.listen(PORT);
console.log("http://localhost:" + PORT)
