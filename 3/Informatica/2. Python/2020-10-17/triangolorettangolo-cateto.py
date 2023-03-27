import math

ipotenusa = float(input("Inserisci l'ipotenusa: "))
cateto1 = float(input("Inserisci il cateto: "))

if cateto1 > ipotenusa:
    print("Errore: Il cateto è maggiore")
    exit()

cateto2 = math.sqrt(ipotenusa ** 2 - cateto1 ** 2)
print("Secondo cateto:", cateto2)
