package com.example.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

// Allure imports
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;

@Epic("Math Utilities")
@Feature("Mathematical Helper Functions")
@DisplayName("MathUtils Tests")
class MathUtilsTest {
    
    @Test
    @Tag("smoke")
    @Story("Factorial Calculation")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify factorial calculation for various input values including edge cases")
    @DisplayName("Should calculate factorial correctly")
    void testFactorial() {
        Allure.step("Calculate 0! (should be 1)", () -> {
            assertThat(MathUtils.factorial(0)).isEqualTo(1);
        });
        
        Allure.step("Calculate 1! (should be 1)", () -> {
            assertThat(MathUtils.factorial(1)).isEqualTo(1);
        });
        
        Allure.step("Calculate 5! (should be 120)", () -> {
            assertThat(MathUtils.factorial(5)).isEqualTo(120);
        });
        
        Allure.step("Calculate 7! (should be 5040)", () -> {
            assertThat(MathUtils.factorial(7)).isEqualTo(5040);
        });
    }
    
    @Test
    @Tag("regression")
    @Story("Factorial Calculation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that factorial calculation throws exception for negative input")
    @DisplayName("Should throw exception for negative factorial")
    void testFactorialNegative() {
        Allure.step("Attempt to calculate factorial of -1", () -> {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> MathUtils.factorial(-1)
            );
            assertThat(exception.getMessage()).contains("negative numbers");
        });
    }
    
    @Test
    @Tag("sanity")
    @Story("List Operations")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify finding maximum value in different types of lists")
    @DisplayName("Should find maximum in list")
    void testFindMax() {
        Allure.step("Find max in mixed positive numbers [1, 5, 3, 9, 2]", () -> {
            assertThat(MathUtils.findMax(Arrays.asList(1, 5, 3, 9, 2))).isEqualTo(9);
        });
        
        Allure.step("Find max in negative numbers [-1, -5, -3]", () -> {
            assertThat(MathUtils.findMax(Arrays.asList(-1, -5, -3))).isEqualTo(-1);
        });
        
        Allure.step("Find max in single element list [42]", () -> {
            assertThat(MathUtils.findMax(Collections.singletonList(42))).isEqualTo(42);
        });
    }
    
    @Test
    @Tag("regression")
    @Story("List Operations")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that findMax throws exception for invalid input (null or empty list)")
    @DisplayName("Should throw exception for null or empty list")
    void testFindMaxInvalid() {
        Allure.step("Attempt to find max in null list", () -> {
            assertThrows(IllegalArgumentException.class, () -> MathUtils.findMax(null));
        });
        
        Allure.step("Attempt to find max in empty list", () -> {
            assertThrows(IllegalArgumentException.class, () -> MathUtils.findMax(Collections.emptyList()));
        });
    }
    
    @ParameterizedTest
    @Tag("regression")
    @Story("Prime Number Detection")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that known prime numbers are correctly identified")
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23})
    @DisplayName("Should correctly identify prime numbers")
    void testIsPrime(int number) {
        Allure.step("Check if " + number + " is prime", () -> {
            assertThat(MathUtils.isPrime(number)).isTrue();
        });
    }
    
    @ParameterizedTest
    @Tag("regression")
    @Story("Prime Number Detection")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that non-prime numbers are correctly identified as not prime")
    @ValueSource(ints = {1, 4, 6, 8, 9, 10, 12, 15, 16})
    @DisplayName("Should correctly identify non-prime numbers")
    void testIsNotPrime(int number) {
        Allure.step("Check if " + number + " is not prime", () -> {
            assertThat(MathUtils.isPrime(number)).isFalse();
        });
    }
}
