"""
S-BOX impl
"""

SBOX = [4, 2, 6, 1, 7, 0, 5, 3]

MAX_VALUE = len(SBOX)

def sbox_enc(number: int):
    assert 0 <= number < MAX_VALUE, "Numero non valido"
    return SBOX[number]

def sbox_dec(number: int):
    assert 0 <= number < MAX_VALUE, "Numero non valido"
    return SBOX.index(number)

def main():
    # Get a number from the user
    number = int(input("Inserisci un numero: "))
    assert 0 <= number < MAX_VALUE, "Numero non valido"

    result = sbox_enc(number)
    result_dec = sbox_dec(result)

    print(f"Risultato: {result}")
    print(f"Risultato decodificato: {result_dec}")
    return

if __name__ == "__main__":
    main()
