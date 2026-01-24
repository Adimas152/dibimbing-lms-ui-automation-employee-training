package dibimbing.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    // HARUS SAMA DENGAN UI: dd/MM/yyyy
    private static final DateTimeFormatter DD_MM_YYYY =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String todayDdMmYyyy() {
        return LocalDate.now().format(DD_MM_YYYY);
    }

    public static String plusDaysFromTodayDdMmYyyy(int days) {
        return LocalDate.now().plusDays(days).format(DD_MM_YYYY);
    }

    public static String plusDaysFromDdMmYyyy(String start, int days) {
        LocalDate base = LocalDate.parse(start, DD_MM_YYYY);
        return base.plusDays(days).format(DD_MM_YYYY);
    }
}