package praticheautomobilistiche;

public class Main {
    public static void main(String[] args) {
        AutoVeicoloElettrico autoveicoloelettrico = new AutoVeicoloElettrico("AA-123-BB", "Fiat", "Punto", 4, 2010);
        AutoVeicoloGas autoveicologas = new AutoVeicoloGas("AA-123-BB", "Fiat", "Punto", 4, 2010, 
                AutoVeicoloGas.Tipo.GPL, 200);
        AutoVeicoloIdrogeno autoveicoloidrogeno = new AutoVeicoloIdrogeno("AA-123-BB", "Fiat", "Punto", 4, 2010);
        AutoVeicoloTradizionale autoveicotradizionale = new AutoVeicoloTradizionale("AA-123-BB", "Fiat", "Punto", 4,
                2010, AutoVeicoloTradizionale.Tipo.BENZINA, 200);
        MotoVeicolo motoveicolo = new MotoVeicolo("AA-123-BB", "Honda", "CBR", 2, 2010, 150);

        System.out.println(autoveicoloelettrico);
        System.out.println(autoveicologas);
        System.out.println(autoveicoloidrogeno);
        System.out.println(autoveicotradizionale);
        System.out.println(motoveicolo);
    }
}
