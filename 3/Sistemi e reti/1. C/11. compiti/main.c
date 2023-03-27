/*
 * Author: Sebastiano Barezzi
 * Version: V1.0
 * 
*/

#include <stdio.h>

#include "compiti_x_gio_15-04.h"

int main() {
    int day=31, month=10, year=2021;
    int ricco=100, povero=20;
    int minore=5, medio=6, maggiore=2; 

    incDay(&day, &month, &year);
    printf("%d/%d/%d\n", day, month, year);

    robinHood(&ricco, &povero);
    printf("Ricco: %d, Povero: %d\n", ricco, povero);

    ordina(&minore, &medio, &maggiore);
    printf("Minore: %d, medio: %d, maggiore: %d\n", minore, medio, maggiore);

    return 0;
}
