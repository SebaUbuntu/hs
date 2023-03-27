/**
 * @brief Calcolo dell'integrale definito di una funzione tramite il metodo dei rettangoli, dei trapezi e di Cavalieri-Simpson
 * @author Sebastiano Barezzi <ssebastiano.barezzi@itis.pr.it>
 */

#define _USE_MATH_DEFINES
#include <math.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

#if 1
double funzione(double x) {
	return 1/sqrt(2*M_PI) * exp(-(pow(x, 2)/2));
}

double derivata_funzione(double x) {
	return -((sqrt(2 * M_PI) * x) / pow((2 * M_PI * M_E), (pow(x, 2) / 2)));
}
#else
double funzione(double x) {
	return pow(x, 3);
}

double derivata_funzione(double x) {
	return pow(3 * x, 2);
}
#endif

/**
 * ((b-a) / n) * Σ(i=0, n-1, f(Xi))
 */
double integrale_rettangoli(double a, double b, int n) {
	double h = (b - a) / n;
	double sum = 0;

	// Σ(i=0, n-1, f(Xi))
	for (int i = 0; i < n; i++) {
		sum += funzione(h * i);
	}

	return h * sum;
}

/**
 * ((b-a) / n) * [((f(x0) + f(xn)) / 2) + Σ(i=1, n-1, f(Xi))]
 */
double integrale_trapezi(double a, double b, int n) {
	double h = (b - a) / n;
	double sum = 0;

	// Σ(i=1, n-1, f(Xi))
	for (int i = 1; i < n; i++) {
		sum += funzione(h * i);
	}

	return h * ((funzione(a) + funzione(b) / 2) + sum);
}

/**
 * 
 */
double integrale_cavalieri_simpson(double a, double b, int n) {
	double h = (b - a) / n;
	double x = a;
	double sum = 0;

	for (int i = 0; i < n; i++)
	{
		sum += (funzione(x) + 4 * funzione(x + h / 2) + funzione(x + h)) / 6;
		x += h;
	}

	return sum * h;
}

int main(int argc, char const *argv[]) {
	unsigned long long a = 0, b, n, temp;
	int i = 0;

	printf("Nota: il numero massimo è %llu\n", ULLONG_MAX - 1);

	// Otteniamo b
	printf("Inserisci il valore di b: ");
	i = scanf("%llu", &b);
	if (i != 1 || b == 0 || b == ULLONG_MAX)
	{
		printf("Numero non corretto");
		return 1;
	}

	printf("Inserisci la precisione: ");
	i = scanf("%llu", &n);
	if (i != 1 || n == 0 || n == ULLONG_MAX)
	{
		printf("Numero non corretto");
		return 1;
	}

	printf("a: %llu, b: %llu, n: %llu\n", a, b, n);

	// Calcola l'integrale
	printf("Integrale dei rettangoli: %f\n", integrale_rettangoli(a, b, n));
	printf("Integrale dei trapezi: %f\n", integrale_trapezi(a, b, n));
	if (n % 2 == 0) {
		printf("Integrale di Cavalieri-Simpson: %f\n", integrale_cavalieri_simpson(a, b, n));
	} else {
		printf("Il numero di intervalli deve essere pari per poter usare il metodo di Cavalieri-Simpson\n");
	}

	return 0;
}
