package com.agileinstitute;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.emptySet;

public class PasswordStrengthChecker {

    private static final Set<Rule> STANDARD_RULES = Set.of(
            new PasswordLengthRule(8),
            new PasswordContainsLetterRule(),
            new PasswordContainsDigitRule()
    );

    private static final Set<Rule> ADMIN_RULES = Set.of(
            new PasswordLengthRule(11),
            new PasswordContainsLetterRule(),
            new PasswordContainsDigitRule()
    );

    public boolean isStrongEnough(String pw) {
        return isStrongEnoughVerbose(pw).isEmpty();
    }

    public Set<String> isStrongEnoughVerbose(String candidate) {
        return isStrongEnoughVerbose(candidate, false);
    }

    public Set<String> isStrongEnoughVerbose(String candidate, boolean adminFlag) {
        if (adminFlag) {
            return new InnerPwChecker(ADMIN_RULES).invoke(candidate);
        }
        return new InnerPwChecker(STANDARD_RULES).invoke(candidate);
    }

    private static class InnerPwChecker {
        private final Set<Rule> rules;

        public InnerPwChecker(Set<Rule> rules) {
            this.rules = rules;
        }

        public Set<String> invoke(String candidate) {
            return rules.stream()
                    .map(r -> r.invoke(candidate))
                    .flatMap(Set::stream)
                    .collect(Collectors.toSet());
        }
    }

    interface Rule {
        Set<String> invoke(String candidate);
    }

    static class PasswordLengthRule implements Rule {
        final int requiredLength;

        PasswordLengthRule(int requiredLength) {
            this.requiredLength = requiredLength;
        }

        @Override
        public Set<String> invoke(String candidate) {
            if (candidate.length() >= requiredLength) {
                return emptySet();
            }
            return Set.of("Password must have at least " + requiredLength + " characters");
        }
    }

    static class PasswordContainsDigitRule implements Rule {
        @Override
        public Set<String> invoke(String candidate) {
            if (candidate.chars().anyMatch(Character::isDigit)) {
                return emptySet();
            }
            return Set.of("Password must contain at least one digit");
        }
    }

    static class PasswordContainsLetterRule implements Rule {
        @Override
        public Set<String> invoke(String candidate) {
            if (candidate.chars().anyMatch(Character::isLetter)) {
                return emptySet();
            }
            return Set.of("Password must contain at least one letter");
        }
    }

    static class PasswordContainsSpecialCharacterRule implements Rule {
        private static final Set<Integer> SPECIAL_CHARACTERS = "#".chars().boxed().collect(Collectors.toSet());

        @Override
        public Set<String> invoke(String candidate) {
            if (candidate.codePoints().noneMatch(SPECIAL_CHARACTERS::contains)) {
                return Set.of("Password must contain at least one special character");
            }
            return emptySet();
        }
    }
}
