numero_pezzi = int(input("Inserisci il numero di pezzi: "))
prezzo_per_pezzo = float(input("Inserisci il prezzo per ogni articolo: "))

prezzo_totale = prezzo_per_pezzo * numero_pezzi

if prezzo_totale > 1000:
    prezzo_totale = (prezzo_totale / 100) * 85

print("Prezzo da pagare:", prezzo_totale, "€")
