package dibimbing.core;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {
    private static final Logger log = LogManager.getLogger(ConfigReader.class);

    private static final Dotenv dotenv = Dotenv.configure()
            .directory(System.getProperty("user.dir")) // root project
            .filename(".env")
            .ignoreIfMissing() // biar gak error di CI kalau env vars sudah diset
            .load();

    /**
     * Ambil config dari:
     * 1) System Environment (paling prioritas) -> cocok untuk GitHub Actions
     * 2) .env (lokal)
     */
    public static String get(String key) {
        String fromEnv = System.getenv(key);
        if (fromEnv != null && !fromEnv.isBlank()) return fromEnv;

        String fromDotenv = dotenv.get(key);
        if (fromDotenv != null && !fromDotenv.isBlank()) return fromDotenv;

        throw new RuntimeException("Missing config key: " + key + " (set as ENV VAR or in .env)");
    }

    public static String getOrDefault(String key, String defaultValue) {
        String fromEnv = System.getenv(key);
        if (fromEnv != null && !fromEnv.isBlank()) return fromEnv;

        String fromDotenv = dotenv.get(key);
        if (fromDotenv != null && !fromDotenv.isBlank()) return fromDotenv;

        return defaultValue;
    }
}
