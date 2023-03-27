/* Aiutandosi con il manuale online
			http://www.cplusplus.com/reference/cstdio/printf/

	...aggiungere accanto ad ogni riga il risultato atteso.

	Sull'altra facciata stilare uno schematico glossario
	che riassuma la funzione di ciascuna opzione via via incontrata.
*/
#include <stdio.h>
int main () {

	int a = 100;
	float b = 2.717;
	char *c = "beej!";
	char d = 'X';
	int e = 5;

	printf("%d\n", a); // 100
	printf("%f\n", b); // 2.717000
	printf("%s\n", c); // beej!
	printf("%c\n", d); // X
	printf("110%%\n"); // 110%
	printf("%10d\n", a); // 7 spazi + 100
	printf("%-10d\n", a); // 100 (??)
	printf("%+d\n", e, a); // +5
	printf("%.2f\n", b); // 2.72
	printf("%hhd\n", d); // 88
	printf("%5d %5.2f %c\n", a, b, d); // 100  2.72 X
	printf("|%-5d|%-5d|\n", 1, 2); // |1    |2    |
	printf("%#0110x\n", 12); // 
	printf("%.3f\n%.3g\n%.3f\n%.3g\n", 100.2, 100.2, 3.1415926, 3.1415926); // \
	0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000c \
	100.200 \
	100 \
	3.142 \
	3.14
	printf("The color: %s\n", "blue"); // The color: blue
	printf("First number: %d\n", 12345); // First number: 12345
	printf("Second number: %04d\n", 25); // Second number: 0025
	printf("Third number: %i\n", 1234); // Third number: 1234
	printf("Float number: %3.2f\n", 3.14159); // Float number: 3.14
	printf("Decimal %d to Hexadecimal: %x\n", 255, 255); // Decimal 255 to Hexadecimal: ff
	printf("Decimal %d to Capital Letter Hexadecimal: %X\n", 255, 25); // Decimal 255 to Capital Letter Hexadecimal: 19
	printf("Octal: %o\n", 255); // Octal: 377
	printf("Unsigned value: %u\n", 150); // Unsigned value: 150
	printf("Just print the percentage sign %%\n", 10); // Just print the percentage sign %
	printf(":%s:\n", "Hello, world!"); // :Hello, world!:
	printf(":%15s:\n", "Hello, world!"); // :  Hello, world!:
	printf(": %.lOs:\n", "Hello, world!"); // : %.0Os:
	printf(":%-10s:\n", "Hello, world!"); // :Hello, world!:
	printf(":%-15s:\n", "Hello, world!"); // :Hello, world!  :
	printf(": %.15s:\n", "Hello, world!"); // : Hello, world!:
	printf(":%15.lOs:\n", "Hello, world!"); // :%15.0Os:
	printf(":%-15.10s:\n", "Hello, world!"); // :Hello, wor     :
	return 0;
}
