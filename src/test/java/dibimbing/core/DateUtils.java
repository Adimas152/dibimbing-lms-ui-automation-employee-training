package dibimbing.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

//    private static final DateTimeFormatter UI_DATE =
//            DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//    public static String todayUiDate() {
//        return LocalDate.now().format(UI_DATE);
//    }
//
//    public static String plusDaysFromTodayUi(int days) {
//        return LocalDate.now().plusDays(days).format(UI_DATE);
//    }
//
//    public static String plusDaysFromUi(String uiDate, int days) {
//        LocalDate start = LocalDate.parse(uiDate, UI_DATE);
//        return start.plusDays(days).format(UI_DATE);
//    }
private static final DateTimeFormatter HTML_DATE =
        DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String todayHtmlDate() {
        return LocalDate.now().format(HTML_DATE);
    }

    public static String plusDaysFromTodayHtml(int days) {
        return LocalDate.now().plusDays(days).format(HTML_DATE);
    }

    public static String plusDaysFromHtml(String htmlDate, int days) {
        LocalDate start = LocalDate.parse(htmlDate, HTML_DATE);
        return start.plusDays(days).format(HTML_DATE);
    }


}