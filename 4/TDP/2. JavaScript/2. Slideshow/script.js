var ultimaImmagine = 0;

function setImmagine(n) {
    var img = document.getElementById("img");
    var numbertext = document.getElementById("numbertext");
    var anteprime = document.getElementsByClassName("anteprime");

    ultimaImmagine = n;

    if (ultimaImmagine > anteprime.length - 1) {
        ultimaImmagine = 0;
    }

    if (ultimaImmagine < 0) {
        ultimaImmagine = anteprime.length - 1;
    }

    // Disattiva tutte le anteprime
    for (var i = 0; i < anteprime.length; i++) {
        anteprime[i].className = anteprime[i].className.replace(" active", "");
    }

    // Imposta l'immagine
    img.src = "img_" + (ultimaImmagine + 1) + ".jpg";
    // Imposta il numero dell'immagine
    numbertext.innerHTML = (ultimaImmagine + 1) + " / " + anteprime.length;
    // Imposta l'anteprima dell'immagine come attiva
    anteprime[ultimaImmagine].className += " active";
}

setImmagine(ultimaImmagine);
