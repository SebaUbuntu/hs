package ordinamentoarray;

public class SelectionSort implements Ordinatore {
    public void ordinaArray(int[] array) {
        // Incrementa di 1 il limite inferiore del sub array da ordinare
        for (int i = 0; i < array.length - 1; i++) {
            // Trova il minimo nel subarray da ordinare
            int indice_min = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j] < array[indice_min]) {
                    // Salvo l'indice del nuovo minimo
                    indice_min = j;
                }
            }

            // Scambia il minimo trovato con il primo elemento
            int temp = array[indice_min];
            array[indice_min] = array[i];
            array[i] = temp; 
        } 
    }
}
