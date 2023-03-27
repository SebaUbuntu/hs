public class MCDmcm {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int valore1, valore2;
        int val_min, val_max;
        int mcd, mcm;
        int n1, n2;

        System.out.print("Inserisci il valore 1: ");
        while (!scanner.hasNextInt()) scanner.next();
        valore1 = scanner.nextInt();

        System.out.print("Inserisci il valore 2: ");
        while (!scanner.hasNextInt()) scanner.next();
        valore2 = scanner.nextInt();

        if (valore1 > valore2) {
            val_max = valore1;
            val_min = valore2;
        } else {
            val_max = valore2;
            val_min = valore1;
        }

        n1 = val_max;
        n2 = val_min;
        while (n1 != n2) {
            if (n1 > n2)
                n1 = n1 - n2;
            else
                n2 = n2 - n1;
        }
        mcd = n1;

        mcm = val_max;
        while (mcm % valore1 != 0 && mcm % valore2 != 0)
            mcm++;

        System.out.println("Il MCD è: " + mcd);
        System.out.println("Il mcm è: " + mcm);

        scanner.close();
    }
}
