package appartamenti;

public class Main {
    public static void main(String[] args) {
        Appartamento appartamento1 = new Appartamento(12, 120, "Via Roma 46a", "Parma", 2, true, 1);
        Appartamento appartamento2 = new Appartamento(12, 120, "Via Bari 21", "Parma", 2, true, 1);
        Villa villa1 = new Villa(10, 300, "Via Roma 46a", "Parma", 3, 1000, true);
        Villa villa2 = new Villa(12, 120, "Via Trento 1", "Parma", 3, 500, false);
    
        System.out.println(appartamento1);
        System.out.println(villa1);

        System.out.println("appartamento1 == appartamento2: " + appartamento1.equals(appartamento2));
        System.out.println("appartamento1 == villa2: " + appartamento1.equals(villa2));
    }
}
