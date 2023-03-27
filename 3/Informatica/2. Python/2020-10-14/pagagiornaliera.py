ora_mattino_entrata = float(input("Inserisci orario entrata mattino: "))
ora_mattino_uscita = float(input("Inserisci orario uscita mattino: "))
ora_pomeriggio_entrata = float(input("Inserisci orario entrata mattino: "))
ora_pomeriggio_uscita = float(input("Inserisci orario uscita mattino: "))
paga_oraria = float(input("Inserisci paga oraria: "))

orario_totale = (ora_mattino_uscita - ora_mattino_entrata) + (ora_pomeriggio_uscita - ora_pomeriggio_entrata)
paga = orario_totale * paga_oraria
print("Ore lavorate: ", orario_totale)
print("Paga giornaliera: ", paga)