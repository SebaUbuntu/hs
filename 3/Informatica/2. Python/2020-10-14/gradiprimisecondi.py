angolo = input("Inserisci i gradi dell'angolo nel seguente formato: gradi primi secondi\nInput: ")
angoloseconditotale = int(angolo.split()[0]) * 3600 + (int(angolo.split()[1]) * 60) + (int(angolo.split()[2]))
print("Secondi totali dell'angolo:", angoloseconditotale)