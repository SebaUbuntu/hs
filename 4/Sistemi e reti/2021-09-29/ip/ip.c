/*
 * IP
 *
 * Autore: Sebastiano Barezzi
 */

#include "ip.h"
#include <stdio.h>

int main() {
	ip_t ip = leggi_ip();
	stampa_ip(&ip);
}

typedef struct ip_class_info {
	char name;
	unsigned char leading_bit;
	unsigned char mask;
} ip_class_info_t;

static const ip_class_info_t ip_classes[] = {
	{.name = 'A', .leading_bit = 0x00, .mask = 0x80},
	{.name = 'B', .leading_bit = 0x80, .mask = 0xC0},
	{.name = 'C', .leading_bit = 0xC0, .mask = 0xE0},
	{.name = 'D', .leading_bit = 0xE0, .mask = 0xF0},
	{.name = 'E', .leading_bit = 0xF0, .mask = 0xF0},
};

ip_t leggi_ip() {
	ip_t ip;
	int i;

	printf("Inserisci un indirizzo IPv4 nel formato x.x.x.x: ");
	scanf("%hhu.%hhu.%hhu.%hhu",
	      &ip.oct[0], &ip.oct[1], &ip.oct[2], &ip.oct[3]);

	ip.val = (ip.oct[0] << 24) + (ip.oct[1] << 16) + (ip.oct[2] << 8) + (ip.oct[3]);

	for (i = 0; i < sizeof(ip_classes) / sizeof(ip_classes[0]); i++)
		if ((ip.oct[0] & ip_classes[i].mask) == ip_classes[i].leading_bit)
			ip.ip_class = ip_classes[i].name;

	return ip;
}

void stampa_ip(ip_t *ip) {
	printf("Hai inserito l'IP %hhu.%hhu.%hhu.%hhu\n"
	       "ID: %u\n"
	       "Classe: %c\n",
	       ip->oct[0], ip->oct[1], ip->oct[2], ip->oct[3],
		   ip->val,
		   ip->ip_class);
}
