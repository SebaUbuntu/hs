importo_per_pezzo = int(input("Inserisci il valore di ogni pezzo: "))
numero_prezzi = int(input("Inserisci il numero dei pezzi: "))

importo_totale = importo_per_pezzo * numero_prezzi

def sconto(importo_totale):
    if importo_totale < 50:
        valore_sconto = (importo_totale / 100 * 5)
    elif importo_totale < 65:
        valore_sconto = (importo_totale / 100 * 6)
    elif importo_totale < 80:
        valore_sconto = (importo_totale / 100 * 7)
    elif importo_totale <= 100:
        valore_sconto = (importo_totale / 100 * 8)
    else:
        valore_sconto = (importo_totale / 100 * 10)

    return valore_sconto

def applicasconto(importo_totale):
    return importo_totale - sconto(importo_totale)

print("Lo sconto da applicare è", sconto(importo_totale), "€")
print("L'importo scontato è", applicasconto(importo_totale), "€")
