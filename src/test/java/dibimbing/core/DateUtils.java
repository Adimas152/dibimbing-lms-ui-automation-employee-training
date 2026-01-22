package dibimbing.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter DD_MM_YYYY = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static String todayDdMmYyyy() {
        return LocalDate.now().format(DD_MM_YYYY);
    }
    public String getDateMinusDays(int days) {
        return LocalDate.now().minusDays(days)
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static String plusDaysFromTodayDdMmYyyy(int days) {
        return LocalDate.now().plusDays(days).format(DD_MM_YYYY);
    }

    public static String plusDaysFromDdMmYyyy(String startDdMmYyyy, int days) {
        LocalDate start = LocalDate.parse(startDdMmYyyy, DD_MM_YYYY);
        return start.plusDays(days).format(DD_MM_YYYY);
    }
}
