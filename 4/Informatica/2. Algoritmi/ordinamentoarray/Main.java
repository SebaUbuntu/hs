package ordinamentoarray;

import java.util.Random;

public class Main {
    static private Random random = new Random();
    static private final int ARRAY_LEN = 10;

    public static void main(String[] args) {
        int[] unordered_array = new int[ARRAY_LEN];

        for (int i = 0; i < unordered_array.length; i++) {
            unordered_array[i] = random.nextInt(100);
        }
        System.out.println("Array non ordinato: " + java.util.Arrays.toString(unordered_array));

        for (Ordinatore ordinatore : new Ordinatore[] {
            new BubbleSort(), new InsertionSort(), new SelectionSort()
        }) {
            int[] array = unordered_array.clone();
            System.out.println("Ordinatore: " + ordinatore.getClass().getSimpleName());
            ordinatore.ordinaArray(array);
            System.out.println(java.util.Arrays.toString(array));
        }
    }
}
