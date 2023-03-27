/*
 * IP
 *
 * Autore: Sebastiano Barezzi
 */

typedef struct {
	unsigned int val;
	unsigned char oct[4];
	char ip_class;
} ip_t;

ip_t leggi_ip();
void stampa_ip(ip_t *ip);
