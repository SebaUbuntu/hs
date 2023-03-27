numeri = input("Inserisci una lista di numeri: ")

numeri_lista = [int(i) for i in numeri.split()]
numeri_lista.sort()

print("Numeri ordinati:", ", ".join([str(i) for i in numeri_lista]))
