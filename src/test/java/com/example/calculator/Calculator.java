package com.example.calculator;

/**
 * A simple calculator class for testing Jenkins pipeline.
 * This class intentionally has some checkstyle violations for testing lint.
 */
public class Calculator {
    
    private static final double EPSILON = 0.0001;
    
    /**
     * Adds two numbers.
     */
    public int add(int a, int b) {
        return a + b;
    }
    
    /**
     * Subtracts second number from first.
     */
    public int subtract(int a, int b) {
        return a - b;
    }
    
    /**
     * Multiplies two numbers.
     */
    public int multiply(int a, int b) {
        return a * b;
    }
    
    /**
     * Divides first number by second.
     * @throws IllegalArgumentException if divisor is zero
     */
    public double divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return (double) dividend / divisor;
    }
    
    /**
     * Calculates power of a number.
     */
    public double power(double base, int exponent) {
        if (exponent == 0) return 1.0;
        if (exponent < 0) return 1.0 / power(base, -exponent);
        
        double result = 1.0;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
    
    /**
     * Calculates square root using Newton's method.
     */
    public double sqrt(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number");
        }
        if (number == 0) return 0;
        
        double guess = number / 2;
        while (Math.abs(guess * guess - number) > EPSILON) {
            guess = (guess + number / guess) / 2;
        }
        return guess;
    }
    
    // Intentional checkstyle violation: missing javadoc
    public boolean isEven(int number) {
        return number % 2 == 0;
    }
    
    // Intentional checkstyle violation: long line
    public String getCalculatorInfo() {
        return "This is a simple calculator implementation for testing Jenkins CI/CD pipeline with shared library functionality including unit tests, lint checks, and Allure reporting.";
    }
}
