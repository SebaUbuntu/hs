import { readFile } from "fs";
import { createServer } from "http";
import { Blackjack } from "./js/libblackjack.js";
import { extname as _extname } from "path";
import { Server } from "socket.io";

function requestHandler(req, res) {
	let filePath = '.' + req.url;
	if (filePath === './') {
		filePath = './index.html';
	}

	const extname = _extname(filePath);

	let contentType
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

	readFile(filePath, function (error, content) {
		if (error) {
			res.writeHead(404);
		}

		else {
			res.writeHead(200, { 'Content-Type': contentType });
			res.write(content, 'utf-8');
		}

		res.end();
	});
}

let httpServer = createServer(requestHandler);
httpServer.listen(3000);

// Socket.io
let gameSessions = new Map();

let socketServer = new Server(httpServer);
socketServer.on("connection", function (socket) {
	socket.on("disconnect", function () {
		if (gameSessions.get(socket.id) != null) {
			delete gameSessions[socket.id];
		}
	});

	socket.on("newGame", function () {
		if (gameSessions.get(socket.id) != null) {
			delete gameSessions[socket.id];
		}

		let gameSession = new Blackjack();
		gameSessions.set(socket.id, gameSession);

		socket.emit("update", gameSession.toJson());
	});
	socket.on("hit", function () {
		let gameSession = gameSessions.get(socket.id);

		gameSession.hitUser();

		socket.emit("update", gameSession.toJson());
	});
	socket.on("stand", function () {
		let gameSession = gameSessions.get(socket.id);

		gameSession.standUser();

		socket.emit("update", gameSession.toJson());
	});

	let gameSession = new Blackjack();
	gameSessions.set(socket.id, gameSession);
	socket.emit("update", gameSession.toJson());
});
