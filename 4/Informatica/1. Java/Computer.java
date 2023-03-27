public class Computer {
    private int codice;
    private String marca;
    private String modello;
    private String processore;
    private float velocitaProcessore;
    private int[] ram;
    private int dimensioneDisco;
    private int dimensioneMonitor;
    private String annoAcquisto;
    private MotherBoard motherboard;

    private static int codice_inc = 0;

    /**
     * Costruttore vuoto
     */
    public Computer() {
        setCodice(codice_inc++);
        setMarca("Sconosciuto");
        setModello("Sconosciuto");
        setProcessore("Sconosciuto");
        setVelocitaProcessore(0);
        setRam(new int[0]);
        setDimensioneDisco(0);
        setDimensioneMonitor(0);
        setAnnoAcquisto("2021");
        setMotherboard(new MotherBoard());
    }

    /**
     * Costruttore con tutti gli attributi
     * @param marca
     * @param modello
     * @param processore
     * @param velocitaProcessore
     * @param ram
     * @param dimensioneDisco
     * @param dimensioneMonitor
     * @param annoAcquisto
     * @param motherboard
     */
    public Computer(String marca, String modello, String processore,
            float velocitaProcessore, int[] ram, int dimensioneDisco, int dimensioneMonitor,
            String annoAcquisto, MotherBoard motherboard) {
        setCodice(codice_inc++);
        setMarca(marca);
        setModello(modello);
        setProcessore(processore);
        setVelocitaProcessore(velocitaProcessore);
        setRam(ram);
        setDimensioneDisco(dimensioneDisco);
        setDimensioneMonitor(dimensioneMonitor);
        setAnnoAcquisto(annoAcquisto);
        setMotherboard(motherboard);
    }

    /**
     * Costruttore di copia
     * @param computer
     */
    public Computer(Computer computer) {
        setCodice(codice_inc++);
        setMarca(computer.marca);
        setModello(computer.modello);
        setProcessore(computer.processore);
        setVelocitaProcessore(computer.velocitaProcessore);
        setRam(computer.ram);
        setDimensioneDisco(computer.dimensioneDisco);
        setDimensioneMonitor(computer.dimensioneMonitor);
        setAnnoAcquisto(computer.annoAcquisto);
        setMotherboard(computer.motherboard);
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getProcessore() {
        return processore;
    }

    public void setProcessore(String processore) {
        this.processore = processore;
    }

    public float getVelocitaProcessore() {
        return velocitaProcessore;
    }

    /**
     * Imposta la velocità del processore.
     * @param velocitaProcessore numero compreso tra 1 e 4
     */
    public void setVelocitaProcessore(float velocitaProcessore) {
        if (velocitaProcessore >= 1.0 && velocitaProcessore <= 4.0)
            this.velocitaProcessore = velocitaProcessore;
    }

    public int[] getRam() {
        return ram;
    }

    public void setRam(int[] ram) {
        this.ram = ram;
    }

    public int getDimensioneDisco() {
        return dimensioneDisco;
    }

    public void setDimensioneDisco(int dimensioneDisco) {
        this.dimensioneDisco = dimensioneDisco;
    }

    public int getDimensioneMonitor() {
        return dimensioneMonitor;
    }

    /**
     * Imposta la dimensione del monitor.
     * @param dimensioneMonitor numero compreso tra 13 e 50
     */
    public void setDimensioneMonitor(int dimensioneMonitor) {
        if (dimensioneMonitor >= 13 && dimensioneMonitor <= 50)
            this.dimensioneMonitor = dimensioneMonitor;
    }

    public String getAnnoAcquisto() {
        return annoAcquisto;
    }

    /**
     * Imposta l'anno di acquisto.
     * @param annoAcquisto numero compreso tra 2004 e 2022
     */
    public void setAnnoAcquisto(String annoAcquisto) {
        int annoInt;
        try {
            annoInt = Integer.parseInt(annoAcquisto);
        } catch (NumberFormatException e) {
            annoInt = 0;
        }
        if (annoInt >= 2004 && annoInt <= 2022)
            this.annoAcquisto = annoAcquisto;
    }

    public MotherBoard getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(MotherBoard motherboard) {
        this.motherboard = motherboard;
    }

    /**
     * Ottieni la RAM totale
     * @return RAM totale
     */
    public int totaleRam() {
        int totale = 0;
        for (int rambank : ram)
            totale += rambank;
        return totale;
    }

    /**
     * Confronta 2 computer
     * @param computer
     * @return true se sono uguali, altrimenti false
     */
    public boolean isEqual(Computer computer) {
        boolean ramEquals = true;
        if (ram.length != computer.ram.length) {
            ramEquals = false;
        } else {
            for (int i = 0; i < ram.length; i++)
                if (ram[i] != computer.ram[i])
                    ramEquals = false;
        }

        return (codice == computer.codice
                && marca.equals(computer.marca)
                && modello.equals(computer.modello)
                && processore.equals(computer.processore)
                && velocitaProcessore == computer.velocitaProcessore
                && ramEquals
                && dimensioneDisco == computer.dimensioneDisco
                && dimensioneMonitor == computer.dimensioneMonitor
                && annoAcquisto.equals(computer.annoAcquisto)
                && motherboard.isEqual(computer.motherboard));
    }

    public String toString() {
        String ramString = "";
        for (int ramBank : ram)
            ramString += ramBank + "GB ";
        if (ramString.isEmpty())
            ramString = "Nessuno";

        return "Codice: " + codice
               + ", marca: " + marca
               + ", modello: " + modello
               + ", processore: " + processore
               + ", velocità processore: " + velocitaProcessore + "GHz"
               + ", RAM: " + ramString
               + ", dimensione disco: " + dimensioneDisco + "GB"
               + ", dimensione monitor: " + dimensioneMonitor + " pollici"
               + ", anno acquisto: " + annoAcquisto
               + ", motherboard: (" + motherboard + ")";
    }

    public static void main(String[] args) {
        Computer computer1 = new Computer();
        int[] ram = new int[2];
        ram[0] = 8;
        ram[1] = 8;
        Computer computer2 = new Computer("Dell", "Optiplex 7010", "Intel Pentium G2400",
                4.0F, ram, 128, 24,
                "2020", new MotherBoard("Dell", "OEM", "LGA1155", "ddr4"));
        Computer computer3 = new Computer(computer2);

        System.out.println("computer1: " + computer1);
        System.out.println("computer2: " + computer2);
        System.out.println("computer1 e computer2 sono uguali: " + computer1.isEqual(computer2));
        System.out.println("computer2 e computer3 sono uguali: " + computer2.isEqual(computer3));
    }
}
