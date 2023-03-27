package ordinamentoarray;

public class InsertionSort implements Ordinatore {
    public void ordinaArray(int[] array) {
        // Per ogni elemento dell'array tranne il primo
        for (int i = 1; i < array.length; i++) {
            int corrente = array[i];
            int j;
            for (j = i-1; j >= 0 && array[j] > corrente; j--) {
                // Muovi l'elemento avanti di uno
                array[j+1] = array[j];
            }
            array[j+1] = corrente;
        }
    }
}
