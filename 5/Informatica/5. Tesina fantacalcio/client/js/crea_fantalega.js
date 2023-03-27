import { API_URL, getInfoUtente } from "./lib.js";

let creaFantalegaForm = document.getElementById("crea-fantalega-form");

async function main() {
    let utente = await getInfoUtente();
    if (utente == null) {
        window.location.href = "login.html";
        return;
    }

    creaFantalegaForm.addEventListener("submit", async (event) => {
        event.preventDefault();

        let nome_fantalega = document.getElementById("nome_fantalega").value;
        let nome_fantasquadra = document.getElementById("nome_fantasquadra").value;
        let crediti_iniziali = document.getElementById("crediti_iniziali").value;
        let numero_portieri = document.getElementById("numero_portieri").value;
        let numero_difensori = document.getElementById("numero_difensori").value;
        let numero_centrocampisti = document.getElementById("numero_centrocampisti").value;
        let numero_attaccanti = document.getElementById("numero_attaccanti").value;

        let response = await fetch(`${API_URL}/crea_fantalega.php`, {
            method: "POST",
            body: JSON.stringify({
                nome_fantalega,
                nome_fantasquadra,
                crediti_iniziali,
                numero_portieri,
                numero_difensori,
                numero_centrocampisti,
                numero_attaccanti
            })
        });

        if (response.ok) {
            let fantalega = await response.json();
            location.href = `visualizza_fantalega.html?fantalega_id=${fantalega.fantalega_id}`;
        } else {
            alert("Errore durante la creazione della fantalega");
        }
    });
}

main();
