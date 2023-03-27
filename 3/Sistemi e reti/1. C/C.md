# C

## Compilazione

### gcc -E file.c -o file.i

Elabora il codice con il preprocessore

### gcc -S file.c -o file.s

Compila il codice ma non lo assembla o linka (Restituisce codice Assembly)

### gcc -c file.c -o file.o

Compila e assembla il codice ma non lo linka (Restituisce un file oggetto)

### gcc file.c -o file

Restituisce un eseguibile (output: file)


## Tipi di dati

| Nome                 | Bits | Specificatore formato               | Range                                                    | Descrizione                                           |
| :------------------: | :--: |-------------------------------------|----------------------------------------------------------|-------------------------------------------------------|
| `char`               | `8`  | `%c`                                | CHAR_BIT                                                 | Rappresenta un carattere                              |
| `signed char`        | `8`  | `%c` (o `%hhi` per output numerico) | [−127, +127]                                             | Rappresenta un carattere signed (con segno)           |
| `unsigned char`      | `8`  | `%c` (o `%hhu` per output numerico) | [0, 255]                                                 | Rappresenta un carattere unsigned (senza segno)       |
| `short`              | `16` | `%hi` o `%hd`                       | [−32,767, +32,767]                                       | Rappresenta un intero a 8 bit signed (con segno)      |
| `unsigned short`     | `16` | `%hu`                               | [0, 65,535]                                              | Rappresenta un intero a 8 bit unsigned (senza segno)  |
| `int`                | `16` | `%i` o `%d`                         | [−32,767, +32,767]                                       | Rappresenta un intero a 16 bit signed (con segno)     |
| `unsigned int`       | `16` | `%u`                                | [0, 65,535]                                              | Rappresenta un intero a 16 bit unsigned (senza segno) |
| `long`               | `32` | `%li` o `%ld`                       | [−2,147,483,647, +2,147,483,647]                         | Rappresenta un intero a 32 bit signed (con segno)     |
| `unsigned long`      | `32` | `%lu`                               | [0, 4,294,967,295]                                       | Rappresenta un intero a 32 bit unsigned (senza segno) |
| `long long`          | `64` | `%lli` o `%lld`                     | [−9,223,372,036,854,775,807, +9,223,372,036,854,775,807] | Rappresenta un intero a 64 bit signed (con segno)     |
| `unsigned long long` | `64` | `%llu`                              | [0, +18,446,744,073,709,551,615]                         | Rappresenta un intero a 64 bit unsigned (senza segno) |
| `float`              |      |                                     |                                                          |                                                       |
| `double`             |      |                                     |                                                          |                                                       |
| `long double`        |      |                                     |                                                          |                                                       |

### Apice

Singolo apice: Carattere

Doppio apice: Stringa

### Stringa

Chiusa da carattere \0
