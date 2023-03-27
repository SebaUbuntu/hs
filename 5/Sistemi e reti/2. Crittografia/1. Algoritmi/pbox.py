"""
P-BOX impl
"""

PBOX = [4, 6, 1, 11, 8, 5, 3, 2, 10, 7, 0, 9]

NUM_BITS = len(PBOX)
MAX_VALUE = 2 ** NUM_BITS

def pbox_enc(number: int):
	assert 0 <= number < MAX_VALUE, "Numero non valido"

	result = 0
	for i in range(NUM_BITS):
		bit = (number >> i) & 1
		result |= bit << PBOX[i]

	return result

def pbox_dec(number: int):
	assert 0 <= number < MAX_VALUE, "Numero non valido"

	result = 0
	for i in range(NUM_BITS):
		bit = (number >> PBOX[i]) & 1
		result |= bit << i

	return result

pbox_enc_lut = [
	pbox_enc(value)
	for value in range(MAX_VALUE)
]
pbox_dec_lut = [
	pbox_dec(value)
	for value in range(MAX_VALUE)
]

def main():
	# Get a number from the user
	number = int(input("Inserisci un numero: "))
	assert 0 <= number < MAX_VALUE, "Numero non valido"

	result = pbox_enc_lut[number]
	result_dec = pbox_dec_lut[result]

	print(f"Risultato: {result}")
	print(f"Risultato decodificato: {result_dec}")
	return

if __name__ == "__main__":
	main()
