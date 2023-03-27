"""
SPN impl
"""

from pbox import (
    MAX_VALUE as PBOX_MAX_VALUE,
    pbox_enc_lut,
    pbox_dec_lut,
)
from sbox import (
    MAX_VALUE as SBOX_MAX_VALUE,
    sbox_enc,
    sbox_dec,
)

KEY = 0b011111000010
KEY_ROUND_2 = KEY ^ 0b111111111111

assert 0 <= KEY < PBOX_MAX_VALUE, "Chiave non valida"

def format_number(number: int):
    result = ""
    for i in reversed(range(4)):
        result += f"{(number >> (i * 3)) & 0b111:03b} "
    return result

def spn_enc(number: int):
    result = number
    print(f"Testo: {format_number(result)}")
    print(f"Chiave: {format_number(KEY)}")

    # Round 1
    # Fai lo XOR con la chiave
    result ^= KEY
    print(f"Risultato round 1 (XOR): {format_number(result)}")
    # Applica la P-BOX
    result = pbox_enc_lut[result]
    print(f"Risultato round 1 (PBOX): {format_number(result)}")
    # Applica la S-BOX a 3 bit
    temp_num = 0
    for i in reversed(range(4)):
        three_bits = (result >> (i * 3)) & 0b111
        temp_num |= sbox_enc(three_bits) << (i * 3)
    result = temp_num
    print(f"Risultato round 1 (SBOX): {format_number(result)}")

    # Round 2
    # Fai lo XOR con la chiave
    result ^= KEY_ROUND_2
    print(f"Risultato round 2 (XOR): {format_number(result)}")
    # Applica la P-BOX
    result = pbox_enc_lut[result]
    print(f"Risultato round 2 (PBOX): {format_number(result)}")
    # Applica la S-BOX a 3 bit
    temp_num = 0
    for i in reversed(range(4)):
        three_bits = (result >> (i * 3)) & 0b111
        temp_num |= sbox_enc(three_bits) << (i * 3)
    result = temp_num
    print(f"Risultato round 2 (SBOX): {format_number(result)}")

    return result

def spn_dec(number: int):
    result = number

    # Round 2
    # Applica la S-BOX
    temp_num = 0
    for i in range(4):
        three_bits = (result >> (i * 3)) & 0b111
        temp_num |= sbox_dec(three_bits) << (i * 3)
    result = temp_num
    # Applica la P-BOX
    result = pbox_dec_lut[result]
    # Fai lo XOR con la chiave
    result ^= KEY_ROUND_2

    #print(f"Risultato round 2: {format_number(result)}")

    # Round 1
    # Applica la S-BOX
    temp_num = 0
    for i in range(4):
        three_bits = (result >> (i * 3)) & 0b111
        temp_num |= sbox_dec(three_bits) << (i * 3)
    result = temp_num
    # Applica la P-BOX
    result = pbox_dec_lut[result]
    # Fai lo XOR con la chiave
    result ^= KEY

    #print(f"Risultato round 1: {format_number(result)}")

    return result

def main():
    # Prendi un numero di 12 bit dall'utente
    number = int(input("Inserisci un numero: "))
    assert 0 <= number < PBOX_MAX_VALUE, "Numero non valido"

    result = spn_enc(number)
    result_dec = spn_dec(result)

    print(f"Risultato: {number}")
    print(f"Risultato decodificato: {result_dec}")

main()
