package ordinamentoarray;

public class BubbleSort implements Ordinatore {
    public void ordinaArray(int[] array) {
        int array_size;
        boolean ordinato;
        // Per ogni elemento dell'array, in reverse
        for (array_size = array.length, ordinato = false; array_size > 0 || !ordinato; array_size--) {
            int riordinamenti = 0;
            // Per ogni elemento dell'array, escludendo gli elementi già ordinati
            for (int i = 0; i < array_size - 1; i++) {
                int corrente = array[i];
                int dopo = array[i + 1];

                if (corrente > dopo) {
                    // Invertiamo i due elementi
                    array[i] = dopo;
                    array[i + 1] = corrente;
                    riordinamenti++;
                }
            }
            if (riordinamenti == 0) {
                // Fermiamoci se non ci sono stati riordinamenti
                ordinato = true;
            }
        }
    }
}
