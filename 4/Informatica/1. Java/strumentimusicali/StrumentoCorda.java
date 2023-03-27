package strumentimusicali;

public class StrumentoCorda extends StrumentoMusicale {
    protected int numeroCorde;

    public StrumentoCorda(String nome, int codice, int annoInvenzione, String descrizione,
            int numeroCorde) {
        super(nome, codice, annoInvenzione, descrizione);

        setNumeroCorde(numeroCorde);
    }

    public int getNumeroCorde() {
        return numeroCorde;
    }

    public void setNumeroCorde(int numeroCorde) {
        this.numeroCorde = numeroCorde;
    }

    @Override
    public String toString() {
        return super.toString()
               + ", numero corde: " + numeroCorde;
    }
}
