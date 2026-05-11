package com.agileinstitute;

import com.agileinstitute.PasswordStrengthChecker.PasswordContainsDigitRule;
import com.agileinstitute.PasswordStrengthChecker.PasswordContainsLetterRule;
import com.agileinstitute.PasswordStrengthChecker.PasswordContainsSpecialCharacterRule;
import com.agileinstitute.PasswordStrengthChecker.PasswordLengthRule;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordRuleTests {

    @Nested
    class PasswordLengthRuleTests {
        @Test
        void shortPasswordsShouldReturnTooShortMessage() {
            // given
            final PasswordLengthRule rule = new PasswordLengthRule(8);
            // when
            Set<String> result = rule.invoke("1234567");
            // then
            assertThat(result).containsOnly("Password must have at least 8 characters");
        }

        @Test
        void longEnoughPasswordShouldReturnNoMessages() {
            // given
            final PasswordLengthRule rule = new PasswordLengthRule(8);
            // when
            Set<String> result = rule.invoke("12345678");
            // then
            assertThat(result).isEmpty();
        }
    }

    @Nested
    class PasswordContainsLetterRuleTests {
        @Test
        void passwordWithoutLetterShouldReturnNoLetterReason() {
            // given
            final PasswordContainsLetterRule rule = new PasswordContainsLetterRule();
            final String passwordWithoutLetter = "1";
            // when
            Set<String> result = rule.invoke(passwordWithoutLetter);
            // then
            assertThat(result).containsOnly("Password must contain at least one letter");
        }

        @Test
        void passwordWithLetterShouldReturnEmptySet() {
            // given
            final PasswordContainsLetterRule rule = new PasswordContainsLetterRule();
            // when
            String passwordWithLetter = "a";
            Set<String> result = rule.invoke(passwordWithLetter);
            // then
            assertThat(result).isEmpty();
        }
    }


    @Nested
    class PasswordContainsDigitRuleTests {
        @Test
        void passwordWithoutDigitShouldReturnNoDigitReason() {
            // given
            final PasswordContainsDigitRule rule = new PasswordContainsDigitRule();
            final String passwordWithoutDigit = "a";
            // when
            Set<String> result = rule.invoke(passwordWithoutDigit);
            // then
            assertThat(result).containsOnly("Password must contain at least one digit");
        }

        @Test
        void passwordWithDigitShouldReturnEmptySet() {
            // given
            final PasswordContainsDigitRule rule = new PasswordContainsDigitRule();
            // when
            String passwordWithDigit = "1";
            Set<String> result = rule.invoke(passwordWithDigit);
            // then
            assertThat(result).isEmpty();
        }
    }

    @Nested
    class PasswordContainsSpecialCharacterRuleTests {
        @Test
        void passwordWithoutASpecialCharacterShouldReturnNoSpecialCharacterReason() {
            // given
            final PasswordContainsSpecialCharacterRule rule = new PasswordContainsSpecialCharacterRule();
            final String passwordWithoutDigit = "a";
            // when
            Set<String> result = rule.invoke(passwordWithoutDigit);
            // then
            assertThat(result).containsOnly("Password must contain at least one special character");
        }

        @Test
        void passwordWithASpecialCharacterShouldReturnEmpty() {
            // given
            final PasswordContainsSpecialCharacterRule rule = new PasswordContainsSpecialCharacterRule();
            final String passwordWithSpecialCharacter = "#";
            // when
            Set<String> result = rule.invoke(passwordWithSpecialCharacter);
            // then
            assertThat(result).isEmpty();
        }
    }
}
