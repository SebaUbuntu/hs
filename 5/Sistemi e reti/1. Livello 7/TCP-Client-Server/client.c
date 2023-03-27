#include <stdio.h>

#ifdef _MSC_VER
#pragma comment(lib, "ws2_32. Lib")
#pragma warning(disable : 4996) //_CRT_SECURE_ NO_WARNINGS
#include <winsock2.h>
#endif

#ifdef __GNUC__
#include <windows.h>
#endif

#define RCVBUFSIZE (64) /* Dimensione del buffer di ricezione */
void OnError(char *errorMessage)
{
    fprintf(stderr, "%s: %D\n", errorMessage, WSAGetLastError());
    exit(1);
}

u_short GetPort(int sock) /* recupera il n. di porta locale (effimera) della connessione */
{
    struct sockaddr_in addrs;
    int addrsSize;

    addrsSize = sizeof(addrs);
    getsockname(sock, (struct sockaddr *)&addrs, &addrsSize);
    return ntohs(addrs.sin_port);
}

void main(int argc, char *argv[])
{
    WSADATA wsaData;                             /*Struttura dati per Winsock */
    int sock;                                    /*Socket client */
    struct sockaddr_in addrClient;               /*Echo server address*/
    unsigned short remoteport;                   /* Echo server port */
    char szIPServer[20];                         /*Server IP address (dotted) */
    char szTx[RCVBUFSIZE], recvBuff[RCVBUFSIZE]; /* Stringa di trasmissione */
    int txSize, bytesRcvd, totalBytesRcvd;

    char ch = 0;

    strcpy(szIPServer, "127.0.0.1");
    strcpy(szTx, "ITIS PR");
    remoteport = 6000;

    if (argc > 1)
        strcpy(szTx, argv[1]); /* stringa da trasmettere su linea di comando */
    if (argc > 2)
        strcpy(szIPServer, argv[2]); /* IP del server su linea di comando */
    txSize = strlen(szTx);

    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) /* inizializzazione libreria Winsock */
    {
        fprintf(stderr, "WSAStartup () failed");
        exit(1);
    }
    /* Creazione socket client */
    if ((sock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
        OnError("socket () fallita");
    /* Valori di impostazione socket client */
    memset(&addrClient, 0, sizeof(addrClient));
    addrClient.sin_family = AF_INET;
    addrClient.sin_addr.s_addr = inet_addr(szIPServer); /* IPremoto */
    addrClient.sin_port = htons(remoteport);            /* Porta remota */
    printf("\t------TCP Concorrente----\n");
    printf("\tConnessione a IP remoto %s su Porta remota %d. .. \n", szIPServer, remoteport);

    /* Tentativo di connessione */
    if (connect(sock, (struct sockaddr *)&addrClient, sizeof(addrClient)) < 0)
        OnError("connect () fallita");
    printf("\n\tClient connesso a Server. \n\tIP Remoto=%s; Porta Remota=%d Porta Locale: %hu\n"
           "\t (x: Termina)",
           inet_ntoa(addrClient.sin_addr), remoteport, GetPort(sock));

    ch = 0;

    while (ch != 'x') /* finché non si digita x ... */
    {
        if (ch)
        {
            fflush(stdin);
            scanf("sc", &ch);
            if (ch == 'x')
                strcpy(szTx, "x");
            szTx[txSize] = ch;
            szTx[txSize + 1] = 0;
            txSize = strlen(szTx);
        }
        else
            ch = '1';

        /* Spedizione del messaggio al server */

        if (send(sock, szTx, txSize, 0) != txSize)
            OnError("send () fallita");
        printf(" \n\tTrasmesso: %s", szTx);

        /* ora mi aspetto la risposta (echo) del server...*/

        totalBytesRcvd = 0;
        while (totalBytesRcvd < txSize)
        {
            if ((bytesRcvd = recv(sock, recvBuff, RCVBUFSIZE - 1, 0)) <= 0)
                OnError("recv () fallita");
            totalBytesRcvd += bytesRcvd;
            recvBuff[bytesRcvd] = 0;
            printf("\n\tRicevuto: %s\n", recvBuff);
        }
    }

    closesocket(sock);

    printf("\n\tConnessione terminata");
    WSACleanup(); /* Cleanup Winsock */
    Sleep(2000);

    exit(0);
}
