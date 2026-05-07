package com.agileinstitute;

import java.util.HashSet;
import java.util.Set;

public class PasswordStrengthChecker {

    public static final int MINIMUM_LENGTH = 8;

    public boolean isStrongEnough(String pw) {
        return isStrongEnoughVerbose(pw).isEmpty();
    }

    private boolean isLongEnough(String pw) {
        return pw.length() >= MINIMUM_LENGTH;
    }

    private boolean hasNumeric(String pw) {
        return pw.chars()
                .mapToObj(c -> (char) c)
                .anyMatch(Character::isDigit);
    }

    private boolean hasChar(String pw) {
        return pw.chars()
                .mapToObj(c -> (char) c)
                .anyMatch(Character::isAlphabetic);
    }

    public Set<String> isStrongEnoughVerbose(String candidate) {
        final Set<String> reasons = new HashSet<>();
        if (!isLongEnough(candidate)) {
            reasons.add("Password must have at least 8 characters");
        }
        if (!hasChar(candidate)) {
            reasons.add("Password must contain at least one letter");
        }
        if (!hasNumeric(candidate)) {
            reasons.add("Password must contain at least one digit");
        }
        return reasons;
    }
}
