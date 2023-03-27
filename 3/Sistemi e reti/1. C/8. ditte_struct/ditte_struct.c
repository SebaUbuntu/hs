/*
 * Filename: ditte.c
 * Autore originale: Singh Shubhvir
 * Modificato da Ramon Ugolotti e Sebastiano Barezzi
 * Classe: 3^C INF
 * Data: 17/03/2021

 * Consegna:
	Dovendo fare un trasloco, vi siete rivolti a tre ditte di trasporti che vi
	hanno fornito altrttanti preventivi:
	Ditta A:
	- fino a 200kg di matariale trasportato tariffa fissa  € 500
	- oltre i 200kg, tariffa fissa di € 500 + €1 per ogni kg oltre i 200kg
	Ditta B:
	- tariffa fissa € 425 + € 0.75 per ogni kg
	Ditta C:
	- fino a 300kg di materiale trasportato, tariffa fissa di € 400 + € 1 per ogni kg.
	- oltre i 300 kg, tariffa fissa di € 700 + € 0.5 per ogni kg oltre i 300.
	Implementare un programma che dato il materiale in kg da trasportare stampa in ordine
	dalla più economica alla più costosa le ditte e il costo del trasloco per ognuna di loro.
*/

#include <stdio.h>

// Ditta A
#define CST_FIX_A 500
#define SOGLIA_A 200
#define CST_KG_A 1
#define SOGLIA2_A 999999999
#define CST_FIX2_A 0
#define CST_KG2_A 0

// Ditta B
#define CST_FIX_B 425
#define SOGLIA_B 0
#define CST_KG_B 0.75
#define SOGLIA2_B 999999999
#define CST_FIX2_B 0
#define CST_KG2_B 0

// Ditta C
#define CST_FIX_C 400
#define SOGLIA_C 0
#define CST_KG_C 1
#define SOGLIA2_C 300
#define CST_FIX2_C 700
#define CST_KG2_C 0.5

// Dichiarazione della Struttura dati (record):
// ovvero, insieme di dati non omogenei raccolti per significato
// Totale 36B
struct ditta
{
	char *nome;           //8B
	double cst_fix;       //8B
	unsigned int soglia;  //4B
	unsigned int soglia2; //4B
	double cst_kg;        //8B
	double costo;         //8B
};

// dichiarazione e inizializzazione di variabili di tipo struttura (GLOBALE)
struct ditta dA = {"Ditta A", CST_FIX_A, SOGLIA_A, CST_KG2_A, 0};
struct ditta dB = {"Ditta B", CST_FIX_B, SOGLIA_B, CST_KG2_B, 0};
struct ditta dC = {"Ditta C", CST_FIX_C, SOGLIA_C, CST_KG2_C, 0};

#define IMPOSTA_COSTO(peso, ditta) \
	if (peso < ditta.soglia) \
		ditta.costo = ditta.cst_fix; \
	else \
		ditta.costo = ditta.cst_fix + (ditta.cst_kg * (peso - ditta.soglia));

int main() {
	unsigned int peso;

	printf("Inserire il peso in Kg: ");
	scanf("%u", &peso);

	if (peso == 0) {
		printf("Ditta A: %0.2lf\nDitta B: %0.2lf\nDitta C: %0.2lf", dA.costo, dB.costo, dC.costo);
		goto exit;
	}

	IMPOSTA_COSTO(peso, dA)
	IMPOSTA_COSTO(peso, dB)
	IMPOSTA_COSTO(peso, dC)

	if (dA.costo > dB.costo && dA.costo > dC.costo && dB.costo > dC.costo){
		printf("Ditta C: %0.2lf\nDitta B: %0.2lf\nDitta A: %0.2lf\n", dC.costo, dB.costo, dA.costo);
	}
	else if(dA.costo > dC.costo && dA.costo > dB.costo && dC.costo > dB.costo){
		printf("Ditta B: %0.2lf\nDitta C: %0.2lf\nDitta A: %0.2lf\n", dB.costo, dC.costo, dA.costo);
	}
	else if(dB.costo > dA.costo && dB.costo > dC.costo && dA.costo > dC.costo){
		printf("Ditta C: %0.2lf\nDitta A: %0.2lf\nDitta B: %0.2lf\n", dC.costo, dA.costo, dB.costo);
	}
	else if (dB.costo > dC.costo && dB.costo > dA.costo && dC.costo > dA.costo){
		printf("Ditta A: %0.2lf\nDitta C: %0.2lf\nDitta B: %0.2lf\n", dA.costo, dC.costo, dB.costo);
	}
	else if (dC.costo > dA.costo && dC.costo > dB.costo && dA.costo > dB.costo){
		printf("Ditta B: %0.2lf\nDitta A: %0.2lf\nDitta C: %0.2lf\n", dB.costo, dA.costo, dC.costo);
	}
	else {
		printf("Ditta A: %0.2lf\nDitta B: %0.2lf\nDitta C: %0.2lf\n", dA.costo, dB.costo, dC.costo);
	}

exit:
	return 0;
}
