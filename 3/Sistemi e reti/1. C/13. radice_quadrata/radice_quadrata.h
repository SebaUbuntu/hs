/*
 * Radice quadrata
 *
 * Autore: Sebastiano Barezzi, 3C INF
 */

#ifndef RADICE_QUADRATA_H
#define RADICE_QUADRATA_H

typedef struct radice {
    int base;
    int esponente;
} radice_t;

int cifre_in_numero(int n);
int calcola(radice_t *rad);

#endif // RADICE_QUADRATA_H
