package praticheautomobilistiche;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class LettoreConfigurazione {
    static protected Map<String, Double> configurazione = init();

    static private Map<String, Double> init() {
        Map<String, Double> configurazione = new HashMap<String, Double>();

        try {
            FileReader fr = new FileReader("praticheautomobilistiche/valori.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isEmpty())
                    continue;
                
                if (linea.startsWith("#"))
                    continue;

                String[] keyValue = linea.split("=");
                configurazione.put(keyValue[0], Double.parseDouble(keyValue[1]));
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato");
        } catch (Exception e) {
            System.out.println("Errore nel parsing del file");
        }

        return configurazione;
    }

    static public Double get(String chiave) {
        return configurazione.get(chiave);
    }
}
