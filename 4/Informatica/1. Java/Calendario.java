import java.time.LocalDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.JapaneseEra;
import java.time.temporal.ChronoField;

public class Calendario {
    public static void main(String[] args) {
        LocalDate localdate = LocalDate.now();
        JapaneseDate jpdate = JapaneseDate.from(localdate);

        System.out.println("Mese: " + jpdate.get(ChronoField.MONTH_OF_YEAR));
        System.out.println("Anno gregoriano: " + jpdate.get(ChronoField.YEAR));
        System.out.println("Era: " + JapaneseEra.of(jpdate.get(ChronoField.ERA)));
        System.out.println("Anno dell'era: " + jpdate.get(ChronoField.YEAR_OF_ERA));
    }
}
