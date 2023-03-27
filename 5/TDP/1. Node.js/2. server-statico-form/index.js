const fs = require("fs");
const http = require("http");
const url = require("url");

function requestHandler(request, response) {
    let oggettourl = url.parse(request.url, "false");
    console.log('href: ' + oggettourl.href);
    const path = oggettourl.pathname;
    console.log('pathname: ' + path);

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
            console.log(oggettourl.query);

            response.writeHead(200, { "content-Type": "text/plain" });
            response.write('Nome: ' + oggettourl.query.nome);
            response.write('\nCognome: ' + oggettourl.query.cognome);
            response.write('\nEta: ' + oggettourl.query.eta);
            response.write('\nSesso: ' + oggettourl.query.sesso);
            response.write('\nStato Civile: ' + oggettourl.query.stat_civ);
            response.write('\nHobby: ' + oggettourl.query.hobby);

            const datiErrati = []
            if (isNaN(oggettourl.query.nome) || oggettourl.query.nome.length < 0 || oggettourl.query.nome.length > 30) {
                datiErrati.push("Nome")
            }
            if (isNaN(oggettourl.query.cognome) || oggettourl.query.cognome.length < 0 || oggettourl.query.cognome.length > 30) {
                datiErrati.push("Cognome")
            }
            if (isNaN(oggettourl.query.eta) || oggettourl.query.eta.length < 0) {
                datiErrati.push("Età")
            }
            if (!(oggettourl.query.sesso in ["Maschio", "Femmina"])) {
                datiErrati.push("Sesso")
            }
            if (!(oggettourl.query.stat_civ in ["Celibe/nubile", "Coniugato/a", "Vedovo/a", "Divorziato/a"])) {
                datiErrati.push("Stato civile")
            }
            if (!(oggettourl.query.hobby in ["Calcio", "Nuoto", "Cucina", "Videogiochi"])) {
                datiErrati.push("Hobby")
            }
            if (datiErrati.length > 0) {
                response.end("\n\ndati errati: " + datiErrati.join());
                console.log("Dati errati")
            } else {
                response.end('\n\ndati ricevuti');
            }

            break;
    }
}

const server = http.createServer(requestHandler);
server.listen(3000);
