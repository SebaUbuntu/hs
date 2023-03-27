/*
 * Primi numeri interi
 *
 * Author: Barezzi Sebastiano
 * Date: 2021-01-28
 * Version: 1.0
*/

#include <stdio.h>

int main() {
	long long a;
	long long i = 1;
	long long result = 0;

	printf("Inserisci il numero: ");
	scanf("%lld", &a);

	while(i <= a) {
		result = result + i;
		i++;
	}

	printf("%lld\n", result);

	return 0;
}
