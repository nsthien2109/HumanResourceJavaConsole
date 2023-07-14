package validation;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean emailValidate(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean birthDayValidate(String birthday){
        LocalDate date = LocalDate.parse(birthday);
        LocalDate currentDate = LocalDate.now();
        int minYear = 1900;
        int maxYear = currentDate.getYear();
        return date.getYear() >= minYear && date.getYear() <= maxYear;
    }
}