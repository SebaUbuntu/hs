# Comunicazione

Classificazioni:

| Occupazione del canale | Inoltro                            | Connessione                                                                 | Commutazione         |
| ---------------------- | ---------------------------------- | --------------------------------------------------------------------------- | ------------         |
| Simplex                | Broadcast(tutti ricevono)          | Orientata alla connessione (stabilire/ connessione)                         | di circuito          |
| Half-duplex            | Point to point (messaggio diretto) | Connectionless (mandi un pacchetto senza accertarsi che venga ricevuto)     | di pacchetto         |
| Full-duplex            | Point to point (messaggio diretto) | Connectionless (mandi un pacchetto senza accertarsi che venga ricevuto)     | di circuito virtuale |

simplex: due cavi, un canale alla volta (telefono)
half-duplex: un cavo, un canale alla volta (walkie-talkie)
full-duplex: un cavo, due canali alla volta (telefono)

Commutazioni:
- di circuito: Instradamento diretto
- di pacchetto: L'indirizzo del pacchetto è nel pacchetto stesso, aggiunge overhead
- di circuito virtuale: Il router sa già dove un pacchetto è destinato

Packet switch network: usato per il full-duplex, pacchetti alternati
