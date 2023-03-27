/*
 * Dec to bin
 *
 * Author: Barezzi Sebastiano
 * Date: 2021-01-29
 * Version: 1.0
*/

#include <stdio.h>

int main() {
	int a;
	int i = 0;
	unsigned int cifra, pow;
	unsigned int result = 0;

	printf("Inserisci il numero: ");
	scanf("%d", &a);

	while(a > 0) {
		cifra = a % 2;
		pow = 10;

		while(cifra >= pow) {
        	pow = pow * 10;
		}
		result = result * pow + cifra;

		a = a / 2;
	}

	printf("%d\n", result);

	return 0;
}
