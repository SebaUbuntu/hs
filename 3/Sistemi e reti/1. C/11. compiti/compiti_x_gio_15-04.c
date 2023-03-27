// Converte un numero espresso come stringa in una base qualsiasi in decimale
// Es: base2dec("123",8) -> 83
unsigned long long base2dec(char* str, int base) {
    return (unsigned long long) 0;
}

// Medinate random propone a schermo una tabellina a caso tra quelle dell 1 e di max e ritorna il numero di tentativi fatti dall'alievo prima di indovinare
void tabelline(int max, int* tentativi) {
    return;
}

//data la data in input, dopo la sua chiamata la data è incrementata di un giorno
//Es: incDay(31,12,1999) restituirà 1,1,2000
void incDay(int* day, int* month, int* year ) {
    if (*day >= 31) {
        *day = 1;
        if (*month >= 12) {
            *month = 1;
            *year += 1;
        } else {
            *month += 1;
        }
    } else {
        *day += 1;
    }
    return;
}

// risolve l'eq di secondo grado ax^2+bx+c=0 e ritorna il delta,
// mentre per indirizzo ritorn x1 e x2 (se esistono;)
double solveEq2(int a, int b, int c, double* x1, double* x2) {
    return (double) 0;
}

// Inverte i due interi
void swap(int *a, int *b) {
    int temp;

    temp = *a;
    *a = *b;
    *b = temp;

    return;
}

// Qualsiasi siano i valori in n1,n2 e n3,
// dopo la chiamata n1<n2<n3
// (suggerimento implementare prima la funzione swap(int*, int*))
void ordina(int* n1,int* n2,int* n3) {
    if (*n1 > *n3)
        swap(n1, n3);

    if (*n1 > *n2)
        swap(n1, n2);

    if (*n2 > *n3)
        swap(n2, n3);
}

// Due persone hanno una quantità di denaro x e y;
// richiamando la funzione robinHood,
// equilibrare la quantità di denaro tra i due.
void robinHood(int* x, int* y) {
    int totale;

    totale = (*x + *y) / 2;
    *x = totale;
    *y = totale;

    return;
}
