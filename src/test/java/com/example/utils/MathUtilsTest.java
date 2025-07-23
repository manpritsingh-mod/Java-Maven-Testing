package com.example.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("MathUtils Tests")
class MathUtilsTest {
    
    @Test
    @DisplayName("Should calculate factorial correctly")
    void testFactorial() {
        assertThat(MathUtils.factorial(0)).isEqualTo(1);
        assertThat(MathUtils.factorial(1)).isEqualTo(1);
        assertThat(MathUtils.factorial(5)).isEqualTo(120);
        assertThat(MathUtils.factorial(7)).isEqualTo(5040);
    }
    
    @Test
    @DisplayName("Should throw exception for negative factorial")
    void testFactorialNegative() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> MathUtils.factorial(-1)
        );
        assertThat(exception.getMessage()).contains("negative numbers");
    }
    
    @Test
    @DisplayName("Should find maximum in list")
    void testFindMax() {
        assertThat(MathUtils.findMax(Arrays.asList(1, 5, 3, 9, 2))).isEqualTo(9);
        assertThat(MathUtils.findMax(Arrays.asList(-1, -5, -3))).isEqualTo(-1);
        assertThat(MathUtils.findMax(Collections.singletonList(42))).isEqualTo(42);
    }
    
    @Test
    @DisplayName("Should throw exception for null or empty list")
    void testFindMaxInvalid() {
        assertThrows(IllegalArgumentException.class, () -> MathUtils.findMax(null));
        assertThrows(IllegalArgumentException.class, () -> MathUtils.findMax(Collections.emptyList()));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23})
    @DisplayName("Should correctly identify prime numbers")
    void testIsPrime(int number) {
        assertThat(MathUtils.isPrime(number)).isTrue();
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 6, 8, 9, 10, 12, 15, 16})
    @DisplayName("Should correctly identify non-prime numbers")
    void testIsNotPrime(int number) {
        assertThat(MathUtils.isPrime(number)).isFalse();
    }
}
