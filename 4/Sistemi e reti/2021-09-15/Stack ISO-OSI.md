# Stack ISO-OSI

Modello teorico modulare costituito da protocolli

- Facile da mantenere
- Facile da debuggare

Composto da 7 layer:
- L1 (fisico): Ambito elettronico
- L2 (di collegamento dei dati): Presente nei firmware della scheda di rete, responsabile della trasmissione di gruppi di bit, viene usato il MAC address per l'identificazione
- ARP/RARP ((Reverse) Address Resolution Protocol): Viene usato per l'identificazione
- L3 (di rete): Implementato dal driver della scheda di rete, gestisce l'instradamento, viene usato l'indirizzo IP per l'identificazione
- L4 (di trasporto): Gestisce trasferimenti end-to-end (protocollo che lavora su due terminali estremi)
- L5 (di sessione): 
- L6 (di presentazione): 
- L7 (di applicazione): 
