const http = require('http');
const fs = require('fs');
const path = require('path');

function requestHandler(req, res) {
    let filePath = '.' + req.url;

    if (filePath === './') {
        filePath = './index.html';
    }

    const extname = path.extname(filePath);

    let contentType;
    switch (extname) {
        case '.html':
            contentType = 'text/html';
            break;
        case '.jpg':
            contentType = 'image/jpg';
            break;
        case '.jfif':
            contentType = 'image/jfif';
            break;
        case '.js':
            contentType = 'text/javascript';
            break;
        case '.css':
            contentType = 'text/css';
            break;
        case '.json':
            contentType = 'application/json';
            break;
        case '.png':
            contentType = 'image/png';
            break;
    }

    fs.readFile(filePath, function (error, content) {
        if (error) {
            res.writeHead(404);
        } else {
            res.writeHead(200, { 'Content-Type': contentType });
            res.write(content, 'utf-8');
        }

        res.end();
    });
}

var server = http.createServer(requestHandler);
server.listen(3000);
