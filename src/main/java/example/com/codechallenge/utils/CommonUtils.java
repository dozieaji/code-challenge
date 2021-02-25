package example.com.codechallenge.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public final class CommonUtils {

    private CommonUtils() {
    }

    public static String dateToStringFormated(LocalDateTime date) {
        if (date != null && date.toString().length() > 19)
            return date.toString().substring(0, 19).replace("T", " ");
        return null;
    }
}