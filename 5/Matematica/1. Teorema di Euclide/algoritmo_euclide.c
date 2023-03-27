/**
 * @brief Algoritmo di Euclide
 * @author Sebastiano Barezzi <ssebastiano.barezzi@itis.pr.it>
 */

#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char const *argv[]) {
	unsigned long long a, b, r, temp;
	int i = 0;

	printf("Nota: il numero massimo è %llu\n", ULLONG_MAX - 1);

	// Otteniamo i due numeri interi
	printf("Inserisci il primo numero intero: ");
	i = scanf("%llu", &a);
	if (i != 1 || a == 0 || a == ULLONG_MAX)
	{
		printf("Numero non corretto");
		return 1;
	}

	printf("Inserisci il secondo numero intero: ");
	i = scanf("%llu", &b);
	if (i != 1 || b == 0 || b == ULLONG_MAX)
	{
		printf("Numero non corretto");
		return 1;
	}

	// Invertiamo i valori se a < b
	if (a < b)
	{
		temp = a;
		a = b;
		b = temp;
	}

	// Calcola il MCD
	// Continua finche' il resto della divisione (modulo) e' diverso da 0
	while ((r = a % b) > 0)
	{
		printf("a: %llu, b: %llu, r: %llu\n", a, b, r);
		a = b; // a diventa il valore di b
		b = r; // b diventa il valore del resto
	}

	printf("a: %llu, b: %llu, r: %llu\n", a, b, r);

	// Stampa il risultato
	printf("Il MCD e': %llu", b);

	return 0;
}
