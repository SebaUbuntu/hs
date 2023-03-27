numero1 = int(input("Inserisci il primo numero: "))
numero2 = int(input("Inserisci il secondo numero: "))

numero = numero1 * numero2

numero_centinaia = numero // 100
numero_decine = (numero - (numero_centinaia * 100)) // 10
numero_unita = numero - (numero_centinaia * 100) - (numero_decine * 10)

print("Numero finale:", numero)
print("Centinaia:", numero_centinaia, "Decine:", numero_decine, "Unità:", numero_unita)