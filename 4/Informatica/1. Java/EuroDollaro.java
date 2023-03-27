public class EuroDollaro {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        java.text.DecimalFormat decimalformat = new java.text.DecimalFormat("0.##");

        System.out.print("Inserisci il valore in Euro: ");
        while (!scanner.hasNextDouble()) scanner.next();
        double euro = scanner.nextDouble();

        double dollaro = euro * 1.17;
        System.out.println("Il valore in Dollari è: " + decimalformat.format(dollaro));

        scanner.close();
    }
}
