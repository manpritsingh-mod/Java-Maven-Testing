package com.example.calculator;

import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.within;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Calculator Tests")
class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    @DisplayName("Should add two positive numbers correctly")
    void testAddPositiveNumbers() {
        assertThat(calculator.add(2, 3)).isEqualTo(5);
        assertThat(calculator.add(10, 15)).isEqualTo(25);
    }
    
    @Test
    @DisplayName("Should add negative numbers correctly")
    void testAddNegativeNumbers() {
        assertThat(calculator.add(-2, -3)).isEqualTo(-5);
        assertThat(calculator.add(-1, 1)).isEqualTo(0);
    }
    
    @Test
    @DisplayName("Should subtract numbers correctly")
    void testSubtract() {
        assertThat(calculator.subtract(5, 3)).isEqualTo(2);
        assertThat(calculator.subtract(0, 5)).isEqualTo(-5);
        assertThat(calculator.subtract(-2, -3)).isEqualTo(1);
    }
    
    @Test
    @DisplayName("Should multiply numbers correctly")
    void testMultiply() {
        assertThat(calculator.multiply(3, 4)).isEqualTo(12);
        assertThat(calculator.multiply(-2, 3)).isEqualTo(-6);
        assertThat(calculator.multiply(0, 100)).isEqualTo(0);
    }
    
    @Test
    @DisplayName("Should divide numbers correctly")
    void testDivide() {
        assertThat(calculator.divide(10, 2)).isEqualTo(5.0);
        assertThat(calculator.divide(7, 2)).isEqualTo(3.5);
        assertThat(calculator.divide(-6, 3)).isEqualTo(-2.0);
    }
    
    @Test
    @DisplayName("Should throw exception when dividing by zero")
    void testDivideByZero() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.divide(5, 0)
        );
        assertThat(exception.getMessage()).isEqualTo("Cannot divide by zero");
    }
    
    @Test
    @DisplayName("Should calculate power correctly")
    void testPower() {
        assertThat(calculator.power(2, 3)).isEqualTo(8.0);
        assertThat(calculator.power(5, 0)).isEqualTo(1.0);
        assertThat(calculator.power(2, -2)).isEqualTo(0.25);
    }
    
    @Test
    @DisplayName("Should calculate square root correctly")
    void testSqrt() {
        assertThat(calculator.sqrt(9)).isCloseTo(3.0, within(0.001));
        assertThat(calculator.sqrt(16)).isCloseTo(4.0, within(0.001));
        assertThat(calculator.sqrt(0)).isEqualTo(0.0);
    }
    
    @Test
    @DisplayName("Should throw exception for negative square root")
    void testSqrtNegative() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.sqrt(-1)
        );
        assertThat(exception.getMessage()).contains("negative number");
    }
    
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10})
    @DisplayName("Should correctly identify even numbers")
    void testIsEven(int number) {
        assertThat(calculator.isEven(number)).isTrue();
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9})
    @DisplayName("Should correctly identify odd numbers")
    void testIsOdd(int number) {
        assertThat(calculator.isEven(number)).isFalse();
    }
    
    @Test
    @DisplayName("Should return calculator info")
    void testGetCalculatorInfo() {
        String info = calculator.getCalculatorInfo();
        assertThat(info).isNotNull();
        assertThat(info).contains("calculator");
        assertThat(info).contains("Jenkins");
    }
}

