/*
 * Analisi voti
 *
 * Author: Barezzi Sebastiano
 * Date: 2021-02-03
 * Version: 1.0
*/

#include <stdio.h>

#define NUMERO_VOTI 10
#define MIN_VOTO 0
#define MAX_VOTO 10
#define SUFFICIENZA 6

int main() {
	float media;
	int val_min = MAX_VOTO;
	int val_max = MIN_VOTO;
	int voti_suf = 0;
	int voti_insuf = 0;
	int somma_voti = 0;
	int i;
	for (i=0; i < NUMERO_VOTI; i++) {
		int voto_corrente;
		printf("Inserisci il %d° voto: ", i+1);
		scanf("%d", &voto_corrente);
		if (voto_corrente > val_max) {
			val_max = voto_corrente;
		}
		if (voto_corrente < val_min) {
			val_min = voto_corrente;
		}
		if (voto_corrente >= SUFFICIENZA) {
			voti_suf += 1;
		} else {
			voti_insuf += 1;
		}
		somma_voti += voto_corrente;
	}
	media = somma_voti / NUMERO_VOTI;

	printf("Media: %.2f\n"
		   "Valore minimo: %d\n"
		   "Valore massimo: %d\n"
		   "N. voti suficienti: %d\n"
		   "N. voti insufficienti: %d\n",
		   media, val_min, val_max, voti_suf, voti_insuf);

	return 0;
}
