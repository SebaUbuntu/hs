/*
 * Math
 *
 * Author: Barezzi Sebastiano
 * Date: 2021-01-27
 * Version: 1.0
*/

#include <math.h>
#include <stdio.h>

int main() {
	int a;
	int b;

	printf("Inserisci il primo numero: ");
	scanf("%d", &a);
	printf("Inserisci il secondo numero: ");
	scanf("%d", &b);

	printf("%d + %d = %d\n", a, b, a + b);
	printf("%d - %d = %d\n", a, b, a - b);
	printf("%d * %d = %d\n", a, b, a * b);
	printf("%d / %d = %d\n", a, b, a / b);
	printf("%d ^ %d = %f\n", a, b, pow(a, b));
	printf("sqrt(%d) = %f\n", a, sqrt(a));

	return 0;
}
