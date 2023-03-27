import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.InputStream;
import java.util.ArrayList;

public class Domande {
    ArrayList<Domanda> domande;

    public Domande() {
        domande = new ArrayList<Domanda>();
    }

    public ArrayList<Domanda> getDomande() {
        return domande;
    }

    /**
     * Carica le domande da un file JSON
     * @param path il percorso del file JSON
     */
    public void importFromFile(String path) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            JSONTokener tokener = new JSONTokener(is);
            JSONObject json = new JSONObject(tokener);

            for (Categoria categoria : Categoria.values()) {
                if (!json.has(categoria.toString()))
                    continue;

                JSONArray array = json.getJSONArray(categoria.toString());

                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);

                    String domanda = obj.getString("domanda");
                    String risposta = obj.getString("risposta");
                    String[] opzioni = null;

                    if (obj.has("opzioni")) {
                        opzioni = new String[4];
                        JSONArray opzioni_array = obj.getJSONArray("opzioni");
                        for (int j = 0; j < opzioni_array.length(); j++) {
                            opzioni[j] = opzioni_array.getString(j);
                        }
                    }

                    if (opzioni != null)
                        domande.add(new DomandaRispostaChiusa(categoria, domanda, risposta, opzioni));
                    else
                        domande.add(new DomandaRispostaAperta(categoria, domanda, risposta));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Restituisce una lista di domande appartenenti alla categoria specificata
     * @param categoria
     * @return ArrayList di domande
     */
    public ArrayList<Domanda> getDomandeStessaCategoria(Categoria categoria) {
        ArrayList<Domanda> domande_stessa_categoria = new ArrayList<Domanda>();
        for (Domanda domanda : domande) {
            if (domanda.getCategoria() == categoria) {
                domande_stessa_categoria.add(domanda);
            }
        }
        return domande_stessa_categoria;
    }
}
