numero_1 = int(input("Inserisci il primo numero: "))
numero_2 = int(input("Inserisci il secondo numero: "))
numero_3 = int(input("Inserisci il terzo numero: "))
numero_4 = int(input("Inserisci il quarto numero: "))

primonumero = numero_1

if primonumero > numero_2:
    secondonumero = primonumero
    primonumero = numero_2
else:
    secondonumero = numero_2

if secondonumero > numero_3:
    if primonumero > numero_3:
        terzonumero = secondonumero
        secondonumero = primonumero
        primonumero = numero_3
    else:
        terzonumero = secondonumero
        secondonumero = numero_3
else:
    terzonumero = numero_3

if terzonumero > numero_4:
    if secondonumero > numero_4:
        if primonumero > numero_4:
            quartonumero = terzonumero
            terzonumero = secondonumero
            secondonumero = primonumero
            primonumero = numero_4
        else:
            quartonumero = terzonumero
            terzonumero = secondonumero
            secondonumero = numero_4
    else:
        quartonumero = terzonumero
        terzonumero = numero_4
else:
    quartonumero = numero_4

print("Numeri ordinati", primonumero, secondonumero, terzonumero, quartonumero)
