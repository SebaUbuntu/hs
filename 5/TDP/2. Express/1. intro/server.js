const express = require("express");
const path = require('path');

let app = express();
app.set('port', process.env.PORT || 3000);
app.set('appName', 'Form');
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');

app.use(function (req, res, next) {
    console.log('url: ', req.url);
    console.log('url: ', req.path);
    console.log('Request Type: ', req.method);
    console.log('Ip del client: ', req.ip);
    console.log('Hostname del client: ', req.hostname);
    console.log('Protocollo della richiesta: ', req.protocol);
    console.log('Metodo di chiamata Ajax: ', req.xhr);
    console.log('Protocollo sicuro: : ', req.secure);
    console.log('Header della richiesta: ', req.header);
    console.log('Parametri della richiesta get: ', req.query);
    console.log('Parametri della richiesta post: ', req.body);

    next();
});

app.get("/", function (req, res) {
    console.log('invio al client la pagina di benvenuto');
    res.render('index', { msg: 'Hello World' });
});

app.use("*", function (req, res, next) {
    res.status(404);
    res.send('Url non presente');
});

const server = app.listen(app.get('port'), function () {
    console.log('Server in ascolto');
});
