#include <stdio.h>
#include <string.h>

#define MAX_LEN 1000

int main() {
    char nums[MAX_LEN];
    int int_input;
    int i = 0;
    int min_i = 0, max_i = 0;

    do {
        scanf("%d", &int_input);
        nums[i] = int_input;
        if (int_input != 0) {
            if (nums[min_i] > int_input)
                min_i = i;
            if (nums[max_i] < int_input)
                max_i = i;
        }
        i++;
    } while (int_input != 0);

    printf("%d %d", min_i, max_i);
    return 0;
}
