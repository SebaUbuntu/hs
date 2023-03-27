import java.lang.Math;

public class Punto {
    private double x;
    private double y;

    public Punto() {
        this.x = 0;
        this.y = 0;
    }

    public Punto(Punto punto) {
        this.x = punto.x;
        this.y = punto.y;
    }

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean equals(Punto punto) {
        return ((this.x == punto.x) && (this.y == punto.y));
    }

    public double getRetta(Punto punto) {
        if (equals(punto))
            return 0;

        double catetoA = (this.x > punto.x) ? (this.x - punto.x) : (punto.x - this.x);
        double catetoB = (this.y > punto.y) ? (this.y - punto.y) : (punto.y - this.y);

        if (this.x == punto.x)
            return catetoB;

        if (this.y == punto.y)
            return catetoA;

        return Math.sqrt((catetoA * catetoA) + (catetoB * catetoB));
    }
}
