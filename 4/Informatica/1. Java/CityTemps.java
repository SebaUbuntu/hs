import java.util.Random;

public class CityTemps {
    private static final int SAMPLES = 24;
    private static final int MIN_TEMP = 1;
    private static final int MAX_TEMP = 18;

    private int[] temps = new int[SAMPLES];

    public static void main(String[] args) {
        CityTemps ct = new CityTemps();
        ct.printTemps();
        ct.printMinMax();
        ct.printAverage();
    }

    CityTemps() {
        Random rand = new Random();

        for (int i = 0; i < temps.length; i++)
            temps[i] = rand.nextInt(MAX_TEMP - MIN_TEMP + 1) + MIN_TEMP;
    }

    public void printTemps() {
        for (int i = 0; i < temps.length; i++)
            System.out.println(temps[i]);
    }

    public void printMinMax() {
        System.out.println("Min: " + getMinValue() + ", Max: " + getMaxValue());
    }

    public void printAverage() {
        double avg = 0;

        for (int i = 0; i < temps.length; i++)
            avg += temps[i];
        avg /= temps.length;

        System.out.println("Media: " + avg);
    }

    private int getMinValue() {
        int minValue = temps[0];
        for (int i = 0; i < temps.length; i++) {
            if (temps[i] < minValue) {
                minValue = temps[i];
            }
        }
        return minValue;
    }

    private int getMaxValue() {
        int maxValue = temps[0];
        for (int i = 0; i < temps.length; i++) {
            if (temps[i] > maxValue){
                maxValue = temps[i];
            }
        }
        return maxValue;
    }
}
