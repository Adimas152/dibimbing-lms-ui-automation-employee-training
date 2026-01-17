package dibimbing.core;

public class TestDataUtil {

    // unique string untuk hindari duplikat Employee ID / Email
    public static String uniq() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String randomName() {
        return "Auto User " + uniq();
    }

    public static String randomEmpId() {
        return "EMP-" + uniq();
    }

    public static String randomEmail() {
        return "auto" + uniq() + "@example.com";
    }

    // untuk UI kamu sudah ada prefix +62, jadi isi hanya angka setelahnya
    public static String randomPhoneId() {
        return "812" + (int)(Math.random() * 90000000 + 10000000); // contoh: 812xxxxxxxx
    }
}
