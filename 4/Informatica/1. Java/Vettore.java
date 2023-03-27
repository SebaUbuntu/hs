public class Vettore {
    private double x0;
    private double y0;
    private double x1;
    private double y1;

    public Vettore() {
        this.x0 = 0;
        this.y0 = 0;
        this.x1 = 0;
        this.y1 = 0;
    }

    public Vettore(double x0, double y0, double x1, double y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    public Vettore(Vettore vettore) {
        this.x0 = vettore.x0;
        this.y0 = vettore.y0;
        this.x1 = vettore.x1;
        this.y1 = vettore.y1;
    }

    public double getX0() {
        return x0;
    }

    public void setX0(double x0) {
        this.x0 = x0;
    }

    public double getY0() {
        return y0;
    }

    public void setY0(double y0) {
        this.y0 = y0;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public boolean equals(Vettore vettore) {
        return (this.x0 == vettore.x0
                && this.y0 == vettore.y0
                && this.x1 == vettore.x1
                && this.y1 == vettore.y1);
    }

    public double length() {
        double catetoA = (x0 > x1) ? (x0 - x1) : (x1 - x0);
        double catetoB = (y0 > y1) ? (y0 - y1) : (y1 - y0);

        if (x0 == x1)
            return catetoB;

        if (y0 == y1)
            return catetoA;

        return Math.sqrt((catetoA * catetoA) + (catetoB * catetoB));
    }

    @Override
    public String toString() {
        return "X0: " + x0
               + ", Y0: " + y0
               + ", X1: " + x1
               + ", Y1: " + y1;
    }

    public static void main(String[] args) {
        Vettore vettore1 = new Vettore(0, 0, 10, 0);
        Vettore vettore2 = new Vettore(10, 0, 0, 0);
        System.out.println("Vettore 1: " + vettore1);
        System.out.println("Lunghezza vettore 1: " + vettore1.length());
        System.out.println("Lunghezza vettore 2: " + vettore2.length());
        System.out.println("Vettore 1 == vettore 2: " + vettore1.equals(vettore2));
    }
}
