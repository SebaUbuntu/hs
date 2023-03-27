function random_number(min_number, max_number) {
	var number = min_number - 1;
	while (number < min_number) {
		number = Math.floor(Math.random() * max_number);
	}
	return number;
}

function gen() {
	const n = random_number(4, 12);
	var password = "";
	var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	for (var i = 0; i < n; i++) {
		var posizione_random = random_number(0, chars.length);
		password += chars[posizione_random];
	}
	document.getElementById("password").value = password;
}
