package com.rudenkoinc.crm.app.service;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

/**
 * @author Illia Rudenko
 */

public class ValidationUtils {

    private ValidationUtils() {
        throw new NotImplementedException("Utility classes cannot be instantiated");
    }

    public static void assertNotBlank(String username, String message) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
        if( c != null && !StringUtils.isEmpty(string)) {
            try {
                return Enum.valueOf(c, string.trim().toUpperCase());
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("specified enum has no constant with the specified name");
            }

        }
        return null;
    }

    public static void assertMinimumLength(String username, int length, String message) {
        if (username.length() < length) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertMatches(String email, Pattern regex, String message) {
        if (!regex.matcher(email).matches()) {
            throw new IllegalArgumentException(message);
        }
    }
}
