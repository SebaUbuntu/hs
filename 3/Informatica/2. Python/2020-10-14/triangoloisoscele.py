import math

lato_obliquo = int(input("Inserisci il lato obliquo: "))
altezza = int(input("Inserisci l'altezza: "))

base = math.sqrt(lato_obliquo ** 2 - altezza ** 2) * 2
perimetro = base + lato_obliquo * 2
area = (base * altezza) / 2

print("Perimetro:", perimetro, "Area:", area)