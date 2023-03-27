public class Angolo {
    private int gradi;
    private int primi;
    private int secondi;

    public Angolo() {
        this.gradi = 0;
        this.primi = 0;
        this.secondi = 0;
    }

    public Angolo(int gradi, int primi, int secondi) {
        this.gradi = gradi;
        this.primi = primi;
        this.secondi = secondi;
    }

    public int getGradi() {
        return gradi;
    }

    public void setGradi(int gradi) {
        this.gradi = gradi;
    }

    public int getPrimi() {
        return primi;
    }

    public void setPrimi(int primi) {
        this.primi = primi;
    }

    public int getSecondi() {
        return secondi;
    }

    public void setSecondi(int secondi) {
        this.secondi = secondi;
    }

    public String visualizzaAngolo() {
        return gradi + "°" + primi + "`" + secondi;
    }
}
