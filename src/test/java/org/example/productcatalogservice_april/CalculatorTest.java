package org.example.productcatalogservice_april;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void TestAdd_RunsSuccessfully() {
        //Arrange
        Calculator calculator =  new Calculator();

        //Act
        int result = calculator.add(1,2);

        //Assert
        //assert(result == 3);
        assertEquals(3,result);
    }

    @Test
    void TestDivide_ThrowsArithmeticException() {
        //Arrange
        Calculator calculator = new Calculator();

        //Act and Assert
        assertThrows(ArithmeticException.class,
                () ->calculator.divide(1,0));
    }
}