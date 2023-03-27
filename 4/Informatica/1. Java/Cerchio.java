public class Cerchio {
    private double raggio;

    public Cerchio(double raggio) {
        this.raggio = raggio;
    }

    public double getRaggio() {
        return raggio;
    }

    public void setRaggio(double raggio) {
        this.raggio = raggio;
    }

    public double calcolaArea() {
        return Math.PI * raggio * raggio;
    }

    public double calcolaPerimetro() {
        return 2 * Math.PI * raggio;
    }

    public String toString() {
        return "Cerchio{" +
                "raggio=" + raggio +
                '}';
    }

    public static void main(String[] args) {
        Cerchio cerchio = new Cerchio(5);
        System.out.println(cerchio);
        System.out.println("Area: " + cerchio.calcolaArea());
        System.out.println("Perimetro: " + cerchio.calcolaPerimetro());
    }
}
