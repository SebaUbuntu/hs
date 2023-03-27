#include <stdio.h>
#include <unistd.h>

int main(int argc, char const *argv[]) {
    pid_t pid0;

    pid0 = getpid();

    printf("Il processo ha pid %d\n", pid0);

    return 0;
}
