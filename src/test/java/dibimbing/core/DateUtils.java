package dibimbing.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter UI_DATE =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String todayUiDate() {
        return LocalDate.now().format(UI_DATE);
    }

    public static String plusDaysFromTodayUi(int days) {
        return LocalDate.now().plusDays(days).format(UI_DATE);
    }

    public static String plusDaysFromUi(String uiDate, int days) {
        LocalDate start = LocalDate.parse(uiDate, UI_DATE);
        return start.plusDays(days).format(UI_DATE);
    }
}