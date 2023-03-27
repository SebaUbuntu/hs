package strumentimusicali;

public class Main {
    public static void main(String[] args) {
        MagazzinoMusicale magazzino = new MagazzinoMusicale();

        StrumentoCorda chitarra1 = new StrumentoCorda("Chitarra", 1, 1833, "strumento musicale cordofono a pizzico", 6);
        StrumentoCorda violino1 = new StrumentoCorda("Violino", 2, 1570, "strumento musicale della famiglia degli archi", 4);
        StrumentoAria fagotto1 = new StrumentoAria("Fagotto", 3, 1800, "strumento musicale a fiato ad ancia doppia", true, false, false);
        StrumentoAria tuba1 = new StrumentoAria("Tuba", 4, 1750, "strumento musicale che appartiene alla famiglia degli ottoni", false, true, false);

        magazzino.aggiungiStrumento(chitarra1);
        magazzino.aggiungiStrumento(violino1);
        magazzino.aggiungiStrumento(fagotto1);
        magazzino.aggiungiStrumento(tuba1);

        System.out.println(magazzino.stampaMagazzino());

        System.out.println(magazzino.stampaMagazzino(StrumentoCorda.class));
        System.out.println(magazzino.stampaMagazzino(StrumentoAria.class));

        System.out.println(magazzino.strumentoPiuAntico());

        System.out.println(magazzino.cercaStrumento("Violino"));

        magazzino.eliminaStrumento(violino1);

        System.out.println(magazzino.cercaStrumento("Violino"));
    }
}
