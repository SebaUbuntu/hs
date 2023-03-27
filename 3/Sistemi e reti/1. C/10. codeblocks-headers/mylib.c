#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "mylib.h"

int countSetBit(unsigned long long n) {

}

void hello(char *nome) {
	printf("Ciao %s\n", nome);
	return;
}

int quadrato(int n) {
	return n**2;
}

int leggiInt(char msg[]) {

}

void stampaRetta(retta_t ret) {

}

/*
 * Verifica se il carattere car è del tipo indicato da opt
 * 'M' maiusc,
 * 'm' minusc,
 * 'a' carattere,
 * 'd' cifra,
 * 's' simbolo,
 * 'p' punteggiatura,
 * 'c' controllo
*/
int isWhat(char car, char opt) {
    int corretto = 0;
    switch (opt) {
        case 'M':
            if ((car >= 65) && (car <= 90))
                corretto = 1;
            break;
        case 'm':
            if ((car >= 97) && (car <= 122))
                corretto = 1;
            break;
        case 'a':
            if ((car >= 65) && (car <= 90))
                corretto = 1;
            if ((car >= 97) && (car <= 122))
                corretto = 1;
            break;
        case 'd':
            if ((car >= 48) && (car <= 57)) {
                corretto = 1;
            }
            break;
        case 's':
            if ((car >= 33) && (car <= 47)) {
                corretto = 1;
            }
            break;
        case 'p':
            switch (opt) {
                case '.':
                case ',':
                case ':':
                case ';':
                    corretto = 1;
                    break;
            }
            break;
        case 'c':
            if ((car >= 65) && (car <= 90)) {
                corretto = 1;
            }
            break;
    }

    return corretto;
}

// Disegna una cornice di dimensione dim (L,H) a partire dalla posizione pos (x,y) fatta di caratteri c
void cornicetta(point_t pos, size_t dim, char c) {

}

// Calcola la radice n-esima di x con il metoto di Newton alla massima precisione possibile con i tipi double (vedi Compito per Gio 4/03)
double nroot(double x, double n) {

}

// lancia un dado con nfaces e ritorna la faccia
int rollADice(int nfaces) {
    int i; /* counter */

    srand(time(NULL));
    i = 1 + (rand() % (nfaces-1));

    return i;
}

// Lancia N dadi e ritorna la somma delle faccie uscite
int rollNDices(int dicesNum) {
    int result;
    int i;
    int j;

    for (i = 1; i <= 20; i++) {
        j = rollADice(6);
        result += j;
    }

    return result;
}

// genera un numero casuale tra Min e Max
int casualeTra(int Min, int Max) {

}

// genera un numero casuale tra 0 e 1
float casuale01() {
    float i;

    srand(time(NULL));
    i = rand() / (double)RAND_MAX;

    return i;
}

// Lancia un dato num volte e salva le frequenza di ciascuna faccia nella relativa variabile f#
void diceFreq(unsigned num, unsigned* f1, unsigned* f2, unsigned* f3, unsigned* f4, unsigned* f5, unsigned* f6) {

}
