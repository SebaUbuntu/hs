import java.lang.Math;

public class Triangolo {
    // Assumendo:
    //   base: AB
    //   altezza: (punto di mezzo di AB)C
    private Punto a;
    private Punto b;
    private Punto c;

    public static void main(String[] args) {
        Triangolo triangolo = new Triangolo(new Punto(0, 0), new Punto(0, 4), new Punto(6, 2));

        System.out.println("Perimetro: " + triangolo.calcolaPerimetro());
        System.out.println("Area: " + triangolo.calcolaArea());
    }

    Triangolo() {
        this.a = new Punto();
        this.b = new Punto();
        this.c = new Punto();
    }

    Triangolo(Punto a, Punto b, Punto c) {
        this.a = new Punto(a);
        this.b = new Punto(b);
        this.c = new Punto(c);
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

    public double calcolaPerimetro() {
        return a.getRetta(b) + b.getRetta(c) + c.getRetta(a);
    }

    public double calcolaArea() {
        double semipermimetro = calcolaPerimetro() / 2;
        double ab = a.getRetta(b);
        double bc = b.getRetta(c);
        double ca = c.getRetta(a);

        return Math.sqrt(semipermimetro * (semipermimetro - ab) * (semipermimetro - bc) * (semipermimetro - ca));
    }
}
