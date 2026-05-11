package com.agileinstitute;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordStrengthCheckerTest {

    @EmptySource
    @CsvSource({"1", "12", "123", "1234", "12345", "123456", "1234567"})
    @ParameterizedTest
    void shortPasswordsShouldReturnFalse(String candidate) {
        // given
        final PasswordStrengthChecker checker = new PasswordStrengthChecker();
        // when
        boolean result = checker.isStrongEnough(candidate);
        // then
        assertThat(result).isFalse();
    }

    @Test
    void longEnoughPasswordShouldReturnTrue() {
        // given
        final PasswordStrengthChecker checker = new PasswordStrengthChecker();
        // when
        String longEnoughPassword = "a2345678";
        boolean result = checker.isStrongEnough(longEnoughPassword);
        // then
        assertThat(result).isTrue();
    }

    @Test
    void passwordWithoutAlphaShouldReturnFalse() {
        // given
        final PasswordStrengthChecker checker = new PasswordStrengthChecker();
        // when
        String longEnoughPassword = "12345678";
        boolean result = checker.isStrongEnough(longEnoughPassword);
        // then
        assertThat(result).isFalse();
    }

    @Test
    void passwordWithoutNumericShouldReturnFalse() {
        // given
        final PasswordStrengthChecker checker = new PasswordStrengthChecker();
        // when
        String longEnoughPassword = "abcdefgh";
        boolean result = checker.isStrongEnough(longEnoughPassword);
        // then
        assertThat(result).isFalse();
    }

    @CsvSource({"a2", "a23", "a234", "a2345", "a23456", "a234567"})
    @ParameterizedTest
    void whenFailsDueToLengthReturnsTooShortReason(String candidate) {
        // given
        final PasswordStrengthChecker checker = new PasswordStrengthChecker();
        // when
        Set<String> result = checker.isStrongEnoughVerbose(candidate);
        // then
        assertThat(result).containsOnly("Password must have at least 8 characters");
    }

    @Test
    void passwordWithoutAlphaShouldReturnNoAlphaReason() {
        // given
        final PasswordStrengthChecker checker = new PasswordStrengthChecker();
        // when
        String longEnoughPasswordWithoutAlpha = "12345678";
        Set<String> result = checker.isStrongEnoughVerbose(longEnoughPasswordWithoutAlpha);
        // then
        assertThat(result).containsOnly("Password must contain at least one letter");
    }

    @Test
    void passwordWithoutNumericShouldReturnNoDigitReason() {
        // given
        final PasswordStrengthChecker checker = new PasswordStrengthChecker();
        // when
        String longEnoughPasswordWithNoDigits = "abcdefgh";
        Set<String> result = checker.isStrongEnoughVerbose(longEnoughPasswordWithNoDigits);
        // then
        assertThat(result).containsOnly("Password must contain at least one digit");
    }

    @Test
    void strongEnoughPasswordShouldReturnNoReason() {
        // given
        final PasswordStrengthChecker checker = new PasswordStrengthChecker();
        // when
        String strongEnoughPassword = "a2345678";
        Set<String> result = checker.isStrongEnoughVerbose(strongEnoughPassword);
        // then
        assertThat(result).isEmpty();
    }

    @Test
    void shortPasswordWithOnlyDigits() {
        // given
        final PasswordStrengthChecker checker = new PasswordStrengthChecker();
        // when
        String strongEnoughPassword = "1";
        Set<String> result = checker.isStrongEnoughVerbose(strongEnoughPassword);
        // then
        assertThat(result).containsOnly(
                "Password must have at least 8 characters",
                "Password must contain at least one letter"
        );
    }

    @Test
    void shortPasswordWithOnlyLetters() {
        // given
        final PasswordStrengthChecker checker = new PasswordStrengthChecker();
        // when
        String strongEnoughPassword = "a";
        Set<String> result = checker.isStrongEnoughVerbose(strongEnoughPassword);
        // then
        assertThat(result).containsOnly(
                "Password must have at least 8 characters",
                "Password must contain at least one digit"
        );
    }

    @Test
    void emptyPassword() {
        // given
        final PasswordStrengthChecker checker = new PasswordStrengthChecker();
        // when
        String strongEnoughPassword = "";
        Set<String> result = checker.isStrongEnoughVerbose(strongEnoughPassword);
        // then
        assertThat(result).containsOnly(
                "Password must have at least 8 characters",
                "Password must contain at least one letter",
                "Password must contain at least one digit"
        );
    }

    @Nested
    class WithAdminFlag {

        @CsvSource({"a2", "a23", "a234", "a2345", "a23456", "a234567", "a2345678", "a23456789", "a234567890"})
        @ParameterizedTest
        void failsIfNotLongEnoughReturnsTooShortReason(String candidate) {
            // given
            final PasswordStrengthChecker checker = new PasswordStrengthChecker();
            // when
            Set<String> result = checker.isStrongEnoughVerbose(candidate, true);
            // then
            assertThat(result).containsOnly("Password must have at least 11 characters");
        }

        @Test
        void passwordWithoutAlphaShouldReturnNoAlphaReason() {
            // given
            final PasswordStrengthChecker checker = new PasswordStrengthChecker();
            // when
            String longEnoughPasswordWithoutAlpha = "12345678...";
            Set<String> result = checker.isStrongEnoughVerbose(longEnoughPasswordWithoutAlpha, true);
            // then
            assertThat(result).containsOnly("Password must contain at least one letter");
        }

        @Test
        void passwordWithoutDigitShouldReturnNoDigitsReason() {
            // given
            final PasswordStrengthChecker checker = new PasswordStrengthChecker();
            // when
            String longEnoughPasswordWithoutAlpha = "abcdefgh...";
            Set<String> result = checker.isStrongEnoughVerbose(longEnoughPasswordWithoutAlpha, true);
            // then
            assertThat(result).containsOnly("Password must contain at least one digit");
        }
    }


}
