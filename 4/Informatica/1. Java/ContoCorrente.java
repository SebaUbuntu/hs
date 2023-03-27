import java.util.Random;

public class ContoCorrente {
    private int numero;
    private String proprietario;
    private double saldo;

    private Random random = new Random();

    public static void main(String[] args) {
        ContoCorrente contocorrente1 = new ContoCorrente("Utente 1");
        ContoCorrente contocorrente2 = new ContoCorrente("Utente 2");

        contocorrente1.deposita(5);
        contocorrente1.preleva(3);
        System.out.println("Saldo conto corrente 1: " + contocorrente1.getSaldo());

        contocorrente1.trasferisci(contocorrente1.getSaldo(), contocorrente2);
        System.out.println("Saldo conto corrente 2: " + contocorrente2.getSaldo());
    }

    public ContoCorrente() {
        this.numero = random.nextInt();
        this.proprietario = "Sconosciuto";
        this.saldo = 0;
    }

    public ContoCorrente(String proprietario) {
        this.numero = random.nextInt();
        this.proprietario = proprietario;
        this.saldo = 0;
    }

    public ContoCorrente(int numero, String proprietario, double saldo) {
        this.numero = numero;
        this.proprietario = proprietario;
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Deposita soldi sul conto
     * @param importo
     */
    public void deposita(double importo) {
        this.saldo += importo;
    }

    /**
     * Preleva soldi dal conto
     * @param importo
     * @return importo se andato a buon fine, altrimenti 0
     */
    public double preleva(double importo) {
        if (saldo < importo)
            // Saldo insufficiente
            return 0;

        saldo -= importo;
        return importo;
    }

    /**
     * Trasferisci soldi da questo conto ad un altro
     * @param importo
     * @param contocorrente
     * @return true se avvenuto con successo, false altrimenti
     */
    public boolean trasferisci(double importo, ContoCorrente contocorrente) {
        if (preleva(importo) == 0)
            return false;

        contocorrente.deposita(importo);
        return true;
    }
}
