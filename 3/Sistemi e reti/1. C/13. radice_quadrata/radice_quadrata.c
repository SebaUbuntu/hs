/*
 * Radice quadrata
 *
 * Autore: Sebastiano Barezzi, 3C INF
 */

#include "radice_quadrata.h"

#include <limits.h>
#include <malloc.h>
#include <stdio.h>

/*
 * https://stackoverflow.com/questions/1068849/how-do-i-determine-the-number-of-digits-of-an-integer-in-c
 */
int cifre_in_numero(int n) {
    if (n < 0) return cifre_in_numero ((n == INT_MIN) ? INT_MAX: -n);
    if (n < 10) return 1;
    return 1 + cifre_in_numero (n / 10);
}

int calcola(radice_t *rad) {
    int base = rad->base;
    int cifre_in_base_n = cifre_in_numero(base);
    int *cifre_in_base = (int *) calloc(cifre_in_base_n, sizeof(int));
    int i = 0;
    int somma_cifre = 0;

    while (base) {
        cifre_in_base[i++] = base % 10;
        base /= 10;
    }

    for (i=0; i<cifre_in_base_n; i++)
        somma_cifre += cifre_in_base[i];

    free(cifre_in_base);

    return somma_cifre - rad->esponente;
}

int main() {
    radice_t rad;

    printf("Inserisci la base: ");
    scanf("%d", &rad.base);

    printf("Inserisci l'esponente: ");
    scanf("%d", &rad.esponente);

    printf("Risultato: %d\n", calcola(&rad));
}
