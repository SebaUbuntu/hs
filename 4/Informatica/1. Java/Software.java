public class Software {
    private String denominazione;
    private String produttore;
    private int versione;
    private String sistemaOperativo;
    private int anno;

    public static void main(String[] args) {
        Software software = new Software("Visual Studio Code", "Microsoft", 1, "Windows/Linux/Mac OS", 2021);
        System.out.println(software);
        int anno = 2022;
        int differenza = software.compareAnno(anno);
        String risultato = differenza == 0 ? "uguale a" : (differenza > 0 ? "minore" : "maggiore") + " di";
        System.out.println("L'anno del software è " + risultato + " " + anno);
    }

    Software(Software software) {
        this.denominazione = software.denominazione;
        this.produttore = software.produttore;
        this.versione = software.versione;
        this.sistemaOperativo = software.sistemaOperativo;
        this.anno = software.anno;
    }

    Software(String denominazione, String produttore, int versione, String sistemaOperativo, int anno) {
        this.denominazione = denominazione;
        this.produttore = produttore;
        this.versione = versione;
        this.sistemaOperativo = sistemaOperativo;
        this.anno = anno;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public String getProduttore() {
        return produttore;
    }

    public void setProduttore(String produttore) {
        this.produttore = produttore;
    }

    public int getVersione() {
        return versione;
    }

    public void setVersione(int versione) {
        this.versione = versione;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public int getAnno() {
        return anno;
    }

    public void setProfessione(int anno) {
        this.anno = anno;
    }

    public String toString() {
        return "Denominazione: " + denominazione
             + ", produttore: " + produttore
             + ", versione: " + versione
             + ", sistema operativo: " + sistemaOperativo
             + ", anno: " + anno;
    }

    public int compareAnno(int anno) {
        return anno - this.anno;
    }
}
