/*
 * Data types
 *
 * Author: Barezzi Sebastiano
 * Date: 2021-01-27
 * Version: 1.0
*/

#include <limits.h>
#include <stdio.h>

#define SEPARATOR '+'
#define FILLER '-'

// Variabili che possono essere usate per ridimensionare
// il numero delle colonne e l'ampiezza della tabella
#define COLUMNS 4
#define COLUMN_SIZE 25

// Titolo che viene mostrato
// all'esecuzione del programma
#define TITLE "Description of every variable:"
#define TITLE_LEN 31

// Non cambiare
#define COLUMN_DATA_TYPE COLUMN_SIZE - 2
#define TAB_WIDTH COLUMNS * COLUMN_SIZE + COLUMNS + 1
#define TITLE_SPACES_LENGTH (TAB_WIDTH - TITLE_LEN) / 2

// Macro che formatta e printa la descrizione dei data type
// Viene fatto un casting a min e max per evitare warning GCC
#define DATA_TYPE(name, type, min, max)                     \
	printf("| %-*s | %-*ld | %-*lld | %-*llu |\n",          \
		   COLUMN_DATA_TYPE, name,                          \
		   COLUMN_DATA_TYPE, sizeof(type),                  \
		   COLUMN_DATA_TYPE, (long long int)min,            \
		   COLUMN_DATA_TYPE, (long long unsigned int)max);  \
	print_separator();

// Printa un separatore
int print_separator() {
    int i;
	int j;

	for(i = 0; i < COLUMNS; i++) {
		putchar(SEPARATOR);
		for(j = 0; j < COLUMN_SIZE; j++) {
			putchar(FILLER);
		}
	}
	printf("%c\n", SEPARATOR);

    return 0;
}

// Printa una descrizione del programma
int print_summary() {
	int i;

	for(i = 0; i < TAB_WIDTH; i++) {
		putchar('*');
	}
	putchar('\n');

	printf("%-*s%s%*s\n",
		   TITLE_SPACES_LENGTH, " ",
		   TITLE,
		   TITLE_SPACES_LENGTH, " ");

	for(i = 0; i < TAB_WIDTH; i++) {
		putchar('*');
	}
	printf("\n\n");

	return 0;
}

// Printa la prima riga della tabella
int print_tab_header() {
	print_separator();
	printf("| %-*s | %-*s | %-*s | %-*s |\n",
		COLUMN_DATA_TYPE, "Tipo",
		COLUMN_DATA_TYPE, "Dim [bytes]",
		COLUMN_DATA_TYPE, "Min",
		COLUMN_DATA_TYPE, "Max");
	print_separator();

	return 0;
}

int main() {
	print_summary();
	print_tab_header();

	// Ogni DATA_TYPE rappresenta una riga della tabella
	DATA_TYPE("char", char, CHAR_MIN, CHAR_MAX)
	DATA_TYPE("signed char", signed char, SCHAR_MIN, SCHAR_MAX)
	DATA_TYPE("unsigned char", unsigned char, 0, UCHAR_MAX)
	DATA_TYPE("short", short, SHRT_MIN, SHRT_MAX)
	DATA_TYPE("unsigned short", unsigned short, 0, USHRT_MAX)
	DATA_TYPE("int", int, INT_MIN, INT_MAX)
	DATA_TYPE("unsigned int", unsigned int, 0, UINT_MAX)
	DATA_TYPE("long", long, LONG_MIN, LONG_MAX)
	DATA_TYPE("unsigned long", unsigned long, 0, ULONG_MAX)
	DATA_TYPE("long long", long long, LLONG_MIN, LLONG_MAX)
	DATA_TYPE("unsigned long long", unsigned long long, 0, ULLONG_MAX)

	return 0;
}
