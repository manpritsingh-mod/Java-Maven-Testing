package com.example.calculator;

import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.within;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

// Allure imports
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;

@Epic("Calculator Application")
@Feature("Basic Calculator Operations")
@DisplayName("Calculator Tests")
class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    @Tag("smoke")
    @Story("Addition Operations")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that calculator can add two positive numbers correctly")
    @DisplayName("Should add two positive numbers correctly")
    void testAddPositiveNumbers() {
        Allure.step("Add 2 + 3", () -> {
            assertThat(calculator.add(2, 3)).isEqualTo(5);
        });
        
        Allure.step("Add 10 + 15", () -> {
            assertThat(calculator.add(10, 15)).isEqualTo(25);
        });
    }
    
    @Test
    @Tag("sanity")
    @Story("Addition Operations")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that calculator handles negative numbers in addition correctly")
    @DisplayName("Should add negative numbers correctly")
    void testAddNegativeNumbers() {
        Allure.step("Add -2 + (-3)", () -> {
            assertThat(calculator.add(-2, -3)).isEqualTo(-5);
        });
        
        Allure.step("Add -1 + 1 (should equal 0)", () -> {
            assertThat(calculator.add(-1, 1)).isEqualTo(0);
        });
    }
    
    @Test
    @Tag("smoke")
    @Story("Subtraction Operations")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that calculator can subtract numbers correctly including edge cases")
    @DisplayName("Should subtract numbers correctly")
    void testSubtract() {
        Allure.step("Subtract 5 - 3", () -> {
            assertThat(calculator.subtract(5, 3)).isEqualTo(2);
        });
        
        Allure.step("Subtract 0 - 5", () -> {
            assertThat(calculator.subtract(0, 5)).isEqualTo(-5);
        });
        
        Allure.step("Subtract -2 - (-3)", () -> {
            assertThat(calculator.subtract(-2, -3)).isEqualTo(1);
        });
    }
    
    @Test
    @Tag("sanity")
    @Story("Multiplication Operations")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify multiplication functionality with positive, negative, and zero values")
    @DisplayName("Should multiply numbers correctly")
    void testMultiply() {
        Allure.step("Multiply 3 × 4", () -> {
            assertThat(calculator.multiply(3, 4)).isEqualTo(12);
        });
        
        Allure.step("Multiply -2 × 3", () -> {
            assertThat(calculator.multiply(-2, 3)).isEqualTo(-6);
        });
        
        Allure.step("Multiply 0 × 100", () -> {
            assertThat(calculator.multiply(0, 100)).isEqualTo(0);
        });
    }
    
    @Test
    @Tag("smoke")
    @Story("Division Operations")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify division functionality with various number combinations")
    @DisplayName("Should divide numbers correctly")
    void testDivide() {
        Allure.step("Divide 10 ÷ 2", () -> {
            assertThat(calculator.divide(10, 2)).isEqualTo(5.0);
        });
        
        Allure.step("Divide 7 ÷ 2 (decimal result)", () -> {
            assertThat(calculator.divide(7, 2)).isEqualTo(3.5);
        });
        
        Allure.step("Divide -6 ÷ 3", () -> {
            assertThat(calculator.divide(-6, 3)).isEqualTo(-2.0);
        });
    }
    
    @Test
    @Tag("regression")
    @Story("Division Operations")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that division by zero throws appropriate exception")
    @DisplayName("Should throw exception when dividing by zero")
    void testDivideByZero() {
        Allure.step("Attempt to divide 5 by 0", () -> {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.divide(5, 0)
            );
            assertThat(exception.getMessage()).isEqualTo("Cannot divide by zero");
        });
    }
    
    @Test
    @Tag("regression")
    @Story("Advanced Operations")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify power calculation with positive, zero, and negative exponents")
    @DisplayName("Should calculate power correctly")
    void testPower() {
        Allure.step("Calculate 2^3", () -> {
            assertThat(calculator.power(2, 3)).isEqualTo(8.0);
        });
        
        Allure.step("Calculate 5^0 (should be 1)", () -> {
            assertThat(calculator.power(5, 0)).isEqualTo(1.0);
        });
        
        Allure.step("Calculate 2^(-2)", () -> {
            assertThat(calculator.power(2, -2)).isEqualTo(0.25);
        });
    }
    
    @Test
    @Tag("sanity")
    @Story("Advanced Operations")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify square root calculation for perfect squares and zero")
    @DisplayName("Should calculate square root correctly")
    void testSqrt() {
        Allure.step("Calculate √9", () -> {
            assertThat(calculator.sqrt(9)).isCloseTo(3.0, within(0.001));
        });
        
        Allure.step("Calculate √16", () -> {
            assertThat(calculator.sqrt(16)).isCloseTo(4.0, within(0.001));
        });
        
        Allure.step("Calculate √0", () -> {
            assertThat(calculator.sqrt(0)).isEqualTo(0.0);
        });
    }
    
    @Test
    @Tag("regression")
    @Story("Advanced Operations")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that square root of negative number throws appropriate exception")
    @DisplayName("Should throw exception for negative square root")
    void testSqrtNegative() {
        Allure.step("Attempt to calculate √(-1)", () -> {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.sqrt(-1)
            );
            assertThat(exception.getMessage()).contains("negative number");
        });
    }
    
    @ParameterizedTest
    @Tag("regression")
    @Story("Utility Operations")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that even numbers are correctly identified")
    @ValueSource(ints = {2, 4, 6, 8, 10})
    @DisplayName("Should correctly identify even numbers")
    void testIsEven(int number) {
        Allure.step("Check if " + number + " is even", () -> {
            assertThat(calculator.isEven(number)).isTrue();
        });
    }
    
    @ParameterizedTest
    @Tag("regression")
    @Story("Utility Operations")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that odd numbers are correctly identified as not even")
    @ValueSource(ints = {1, 3, 5, 7, 9})
    @DisplayName("Should correctly identify odd numbers")
    void testIsOdd(int number) {
        Allure.step("Check if " + number + " is odd (not even)", () -> {
            assertThat(calculator.isEven(number)).isFalse();
        });
    }
    
    @Test
    @Tag("sanity")
    @Story("Utility Operations")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Verify that calculator returns proper information string")
    @DisplayName("Should return calculator info")
    void testGetCalculatorInfo() {
        Allure.step("Get calculator information", () -> {
            String info = calculator.getCalculatorInfo();
            assertThat(info).isNotNull();
            assertThat(info).contains("calculator");
            assertThat(info).contains("Jenkins");
        });
    }
}

