package com.company.rateinfo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static LocalDate convertDate(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(date, formatter);
    }
}
