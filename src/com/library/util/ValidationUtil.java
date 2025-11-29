package com.library.util;

import java.util.regex.Pattern;

public class ValidationUtil {
    private ValidationUtil() {

    }

    public static final Pattern ISBN_REGEX_PATTERN =
            Pattern.compile("^([0-9]{13}|[0-9]{9}[0-9Xx])$");

    public static final Pattern VALID_NAME_SURNAME =
            Pattern.compile("[\\p{Alpha}][\\p{Alpha} ]*");

    public static final Pattern VALID_MAIL =
            Pattern.compile("^[\\p{Alnum}+_.-]+@[\\p{Alnum}.-]+$");
}
