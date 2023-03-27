#ifndef MYLIB_H_INCLUDED
#define MYLIB_H_INCLUDED

typedef struct point {
	int x;
	int y;
} point_t;

typedef struct {
	int L;
	int H;
} dim_t;

typedef struct retta {
	double m;
	double q;
	int min_x;
	int max_x;
	unsigned int nPunti;
} retta_t;

//Ritorna ilnumero di bit a 1 del numero n
int countSetBit(unsigned long long n);

//stampa il saluto alla persona nome
void hello(char* nome);
//Restituisce il quadrato di n
int quadrato(int n);
//Stampa il messaggio msg[] di richiesta di immissione e restituisce l'intero inserito
int leggiInt(char msg[]);
//La funzione dovr� stampa: eq. retta passata, a capo, la tabella con le nPunti coppie (x,y) da min_x a max_x, di punti cha stanno sul grafico della retta passata. Vincoli: nPunti>1, min_x<max_x.
void stampaRetta(retta_t ret);
//Verifica se il carattere car � del tipo indicato da opt
int isWhat(char car, char opt); /*opt:  'M' maiusc,
                                        'm' minusc,
                                        'a' carattere,
                                        'd' cifra,
                                        's' simbolo,
                                        'p' punteggiatura,
                                        'c' controllo
								*/
//Disegna una cornice di dimensione dim (L,H) a partire dalla posizione pos (x,y) fatta di caratteri c
void cornicetta(pont_t pos, size_t dim, char c);
//Calcola la radice n-esima di x con il metoto di Newton alla massima precisione possibile con i tipi double (vedi Compito per Gio 4/03)
doble nroot(double x, double n);
//lancia un dado con nfaces e ritorna la faccia
int rollADice(int nfaces);
//Lancia N dadi e ritorna la somma delle faccie uscite
int rollNDices(int dicesNum);
//genera un numero casuale tra Min e Max
int casualeTra(int Min, int Max);
//genera un numero casuale tra 0 e 1
float casuale01();
//Genera e ritorna un indirizzo IP casuale
unsigned int randIP();
//Funzione che consente all'utente di immetere un indirizzo IP nel formato dei 4 ottetti decimali (es. 192.168.0.1)
//e lo ritorna come dato a 32bit(DWORD)
unsigned int getIp();
//dato un codice a 32bit (DWORD) stampa l'indirizzo IP corrispondente (espresso in ottetti decimali)
void printIP(unsigned int decIP);
//Lancia un dato num volte e salva le frequenza di ciascuna faccia nella relativa variabile f#
void diceFreq(unsigned num, unsigned* f1, unsigned* f2, unsigned* f3, unsigned* f4, unsigned* f5, unsigned* f6);

#endif // MYLIB_H_INCLUDED
