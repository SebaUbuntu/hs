#include <stdio.h>

#ifdef _MSC_VER
#pragma comment(lib, "ws2_32.lib")
#pragma warning(disable : 4996)  // _CRI_SECURE_NO_WARNINGS
#include <winsock2.h>
#endif

#ifdef __GNUC__
#include <windows.h>
#endif

#define TCP_PORT (6000)
#define MAXPENDING (5)
#define RCVBUFSIZE (64)

typedef struct
{
    int intsock; /* socket del client */
    int remoteport; /* porta remota del client (effimera) */
} t_threadargs;

int AcceptTCPConnection(int socks, int *remoteport); /* Accetta una connessione TCP */
void *threadClient(void *arg); /* Thread del client */

void OnError(char *errorMessage)
{
    fprintf(stderr, "%s: %d\n", errorMessage, WSAGetLastError());
    exit(1);
}

void main(void)
{
    WSADATA wsaData; /* Struttura dati per Winsock */
    int sockServer; /* Socket server*/
    int sockClient; /* Socket client*/
    int remoteport;
    struct sockaddr_in addrServer; /* Local address server */
    DWORD threadID; /* Thread ID del client*/
    t_threadargs *threadargs; /* puntatore alla struttura dati passata ai thread */

    /* Inizializzazione Winsock */
    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0)
    {
        fprintf(stderr, "WSAStartup() failed");
        exit(1);
    }

    /* Creazione socket server */
    if ((sockServer = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0) OnError("socket() failed");
    /* Valori di impostazione socket server */
    memset(&addrServer, 0, sizeof(addrServer));
    addrServer.sin_family = AF_INET;
    addrServer.sin_addr.s_addr = htonl(INADDR_ANY); /* Indirizzo IP locale */
    addrServer.sin_port = htons(TCP_PORT); /* Porta locale */
    /* Impostazione socket server */
    if (bind(sockServer, (struct sockaddr *)&addrServer, sizeof(addrServer)) < 0)
        OnError("bind() failed");

    /* Connessione passiva del server */
    if (listen(sockServer, MAXPENDING) < 0) OnError("listen() failed");
    printf("Server in ascolto sulla porta %d\n", TCP_PORT);

    for (;;)
    {
        /* Accetta una connessione TCP */
        sockClient = AcceptTCPConnection(sockServer, &remoteport);
        /* Un client si è connesso */
        threadargs = (t_threadargs *)malloc(sizeof(t_threadargs));
        threadargs->intsock = sockClient;
        threadargs->remoteport = remoteport;
        if ((CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE) threadClient, threadargs, 0, &threadID)) == NULL)
            OnError("CreateThread() failed");
        printf("\n\t(sul thread %ld)\n", threadID);
    }
}

void *threadClient(void *threadArgs) {
    int sockC; /* Socket del client */
    char recvBuff[RCVBUFSIZE]; /* Buffer di ricezione */
    int recvBuffSize; /* Dimensione del buffer di ricezione */
    int remoteport; /* Porta remota del client (effimera) */

    sockC = ((t_threadargs *)threadArgs)->intsock; /* Socket del client */
    remoteport = ((t_threadargs *)threadArgs)->remoteport; /* Porta remota del client (effimera) */
    free(threadArgs); /* Libera la memoria allocata per la struttura dati passata al thread */

    recvBuff[0] = 0;
    while (recvBuff[0] != 'x') {
        recvBuffSize = recv(sockC, recvBuff, RCVBUFSIZE, 0); /* Bloccante */
        if (recvBuffSize>0) {
            recvBuff[recvBuffSize] = 0;
            send(sockC, recvBuff, recvBuffSize, 0); /* echo sul client */
            printf("\n\tClient (porta remota %d): Ricevuto e risposto: %s ", remoteport, recvBuff);
        }
    }

    printf("\n\tClient (porta remota %d): Terminazione connessione", remoteport);
    closesocket(sockC); /* Chiude la connessione con il client */
    return 0;
}

int AcceptTCPConnection(int sockS, int *remoteport) {
    int sockC; /* Socket del client */
    struct sockaddr_in addrClient; /* Indirizzo del client */
    unsigned int addrSize;

    addrSize = sizeof(addrClient);
    /* Chiamata bloccante */
    if ((sockC = accept(sockS, (struct sockaddr *)&addrClient, (int *)&addrSize)) < 0)
        OnError("accept() failed");
    
    *remoteport = ntohs(addrClient.sin_port); /* Porta remota del client (effimera) */
    printf("\n\tConnesso client. IP remoto=%s (porta remota %d)", inet_ntoa(addrClient.sin_addr), *remoteport);
    return sockC;
}
