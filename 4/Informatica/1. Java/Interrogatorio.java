import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Interrogatorio {
    private static final String FILE_NAME = "alunni.txt";

    private ArrayList<String> students = new ArrayList<String>();
    private Random random = new Random();

    public Interrogatorio() {
        String line;

        try {
            File file = new File(FILE_NAME);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null)
                students.add(line.trim());

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStudent() {
        return students.get(random.nextInt(students.size()));
    }

    public static void main(String[] args) {
        Interrogatorio interrogatorio = new Interrogatorio();

        System.out.println("Alunno estratto: " + interrogatorio.getStudent());
    }
}
