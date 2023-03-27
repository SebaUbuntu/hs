lato_1 = int(input("Inserisci il primo lato: "))
lato_2 = int(input("Inserisci il secondo lato: "))
lato_3 = int(input("Inserisci il terzo lato: "))

if (lato_1 == lato_2 == lato_3):
    print("Questo è un triangolo equilatero")
elif (lato_1 == lato_2) or (lato_1 == lato_3) or (lato_2 == lato_3):
    print("Questo è un triangolo isoscele")
else:
    print("Questo è un triangolo scaleno")
