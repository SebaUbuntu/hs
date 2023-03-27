var NUM_FOTO = 3;

var immagine = 1;

function Avanti() {
    immagine++;
    if (immagine > NUM_FOTO)
        immagine = 1;
    document.getElementById("slider"). src="immagine(" + immagine + ").png";
}

function Indietro() {
    immagine--;
    if (immagine < 1)
        immagine = NUM_FOTO;
    document.getElementById("slider"). src="immagine(" + immagine + ").png";
}
