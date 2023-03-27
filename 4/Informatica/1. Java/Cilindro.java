public class Cilindro extends Cerchio {
    private double altezza;

    public Cilindro(double raggio, double altezza) {
        super(raggio);
        this.altezza = altezza;
    }

    public double getAltezza() {
        return altezza;
    }

    public void setAltezza(double altezza) {
        this.altezza = altezza;
    }

    public double volume() {
        return Math.PI * Math.pow(getRaggio(), 2) * getAltezza();
    }

    public static void main(String[] args) {
        Cilindro c = new Cilindro(2, 3);
        System.out.println(c.volume());
    }
}
