#
# Copyright (c) 2023 itis.pr.it
#
# SPDX-License-Identifier: MIT
#
"""Script per importare i dati dei giocatori dal CSV scaricato dal sito del Fantacalcio."""

from csv import QUOTE_NONE, reader
from datetime import datetime
from enum import Enum
from json import dump
from pathlib import Path
from typing import List

FILE_ENCODING = "utf-8"

# Percorso del file CSV scaricato dal sito del Fantacalcio
# Basta scaricare il file e metterlo nella stessa cartella di questo script
# https://www.fantacalcio.it/app-fantaasta
CSV_FILE = Path(__file__).parent / "Lista-FantaAsta-Fantacalcio.csv"

JSON_FILE = Path(__file__).parent / "giocatori.json"

class Posizione(Enum):
    """Enum per le posizioni dei giocatori."""
    P = "P"
    D = "D"
    C = "C"
    A = "A"

class Giocatore:
    """Classe per rappresentare un giocatore."""
    def __init__(
        self,
        cognome_nome: str,
        data_nascita: datetime,
        posizione: Posizione,
        crediti_iniziali: int,
        crediti_finali: int,
        squadra: str,
        nazionalita: List[str],
    ):
        self.cognome_nome = cognome_nome
        self.data_nascita = data_nascita
        self.posizione = posizione
        self.crediti_iniziali = crediti_iniziali
        self.crediti_finali = crediti_finali
        self.squadra = squadra
        self.nazionalita = nazionalita

    def to_dict(self):
        return {
            "cognome_nome": self.cognome_nome,
            "data_nascita": self.data_nascita.strftime("%Y-%m-%d"),
            "posizione": self.posizione.value,
            "crediti_iniziali": self.crediti_iniziali,
            "crediti_finali": self.crediti_finali,
            "squadra": self.squadra,
            "nazionalita": self.nazionalita,
        }

# Alcuni nomi di nazioni sono sbagliati, questo
# dizionario corregge gli errori conosciuti
TYPO_NAZIONI = {
    "Coresa Del Sud": "Corea Del Sud",
    "Costa D''Avorio": "Costa D'Avorio",
    "Kenia": "Kenya",
    "Maroccco": "Marocco",
    "Slovenis": "Slovenia",
}

NOME_SQUADRA = {
    "Verona": "Hellas Verona",
}

def main():
    giocatori = []
    nazioni = set()

    with open(CSV_FILE, "r", newline='', encoding=FILE_ENCODING) as csvfile:
        # Leggi il CSV
        csv_reader = reader(csvfile, delimiter=',', quoting=QUOTE_NONE)

        for row in csv_reader:
            (
                _, # ID giocatore
                _, # Nome da visualizzare, noi mostriamo quello completo
                cognome_nome,
                ruolo,
                _, # Ruolo specifico
                _, # Qualcosa riguardo ai crediti
                _, # Qualcosa riguardo ai crediti
                _, # Qualcosa riguardo ai crediti
                _, # Qualcosa riguardo ai crediti
                squadra,
                crediti_iniziali,
                crediti_finali,
                _, # Piede preferito
                nazionalita_raw,
                data_nascita,
                _, # Link immagine
                is_venduto,
                _, # Media
                _, # Fantamedia
            ) = row

            if is_venduto == "1":
                # Il giocatore non è più in rosa, ignoralo
                continue

            # Ottieni tutte le nazionalità del giocatore, correggendo anche gli errori di battitura
            nazionalita = [
                TYPO_NAZIONI.get(i.title(), i.title()) for i in nazionalita_raw.split(";")
            ]

            # Correggi il nome della squadra
            squadra = NOME_SQUADRA.get(squadra, squadra)

            # Aggiungi il giocatore alla lista
            giocatori.append(
                Giocatore(
                    cognome_nome,
                    datetime.strptime(data_nascita, "%d/%m/%Y %H:%M:%S"),
                    Posizione(ruolo),
                    int(crediti_iniziali),
                    int(crediti_finali),
                    squadra,
                    nazionalita,
                )
            )

            # Aggiungi le nazionalità del giocatore alla lista
            nazioni.update(nazionalita)

    data = {
        "giocatori": [i.to_dict() for i in giocatori],
        "nazioni": sorted(nazioni),
    }

    with JSON_FILE.open("w", encoding=FILE_ENCODING) as f:
        dump(data, f, ensure_ascii=False, indent=4)

main()
