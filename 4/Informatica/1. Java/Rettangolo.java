public class Rettangolo {
    private Punto a;
    private Punto b;
    private Punto c;
    private Punto d;

    public static void main(String[] args) {
        Rettangolo rettangolo = new Rettangolo(new Punto(0, 0), new Punto(10, 0), new Punto(10, 4), new Punto(0, 4));

        System.out.println("Perimetro: " + rettangolo.calcolaPerimetro());
        System.out.println("Area: " + rettangolo.calcolaArea());
        System.out.println("Valido: " + rettangolo.isValid());
    }

    public Rettangolo(Punto a, Punto b, Punto c, Punto d) {
        this.a = new Punto(a);
        this.b = new Punto(b);
        this.c = new Punto(c);
        this.d = new Punto(d);
    }

    public Punto getA() {
        return a;
    }

    public void setA(Punto a) {
        this.a = a;
    }

    public Punto getB() {
        return b;
    }

    public void setB(Punto b) {
        this.b = b;
    }

    public Punto getC() {
        return c;
    }

    public void setC(Punto c) {
        this.c = c;
    }

    public Punto getD() {
        return d;
    }

    public void setD(Punto d) {
        this.d = d;
    }

    public double calcolaPerimetro() {
        return a.getRetta(b) + b.getRetta(c) + c.getRetta(d) + d.getRetta(a);
    }

    public double calcolaArea() {
        double base = a.getRetta(b);
        double altezza = b.getRetta(c);

        return base * altezza;
    }

    /**
     * Controlla se il rettangolo è valido
     * @return true se valido, altrimenti false
     */
    public boolean isValid() {
        boolean valid = true;

        // Le rette parallele devono essere della stessa lunghezza
        if (a.getRetta(b) != c.getRetta(d))
            valid = false;

        if (a.getRetta(d) != b.getRetta(c))
            valid = false;

        // Controlla se A e D sono sullo stesso asse X
        if (a.getX() != d.getX())
            valid = false;

        // Controlla se B e C sono sullo stesso asse X
        if (b.getX() != c.getX())
            valid = false;

        // Controlla se A e B sono sullo stesso asse Y
        if (a.getY() != b.getY())
            valid = false;

        // Controlla se C e D sono sullo stesso asse Y
        if (c.getY() != d.getY())
            valid = false;

        return valid;
    }
}
