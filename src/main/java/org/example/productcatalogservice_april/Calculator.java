package org.example.productcatalogservice_april;

public class Calculator {
    int add(int num1, int num2) {
        return num1+num2;
    }

    int divide(int num1,int num2) {
        try {
            return num1 / num2;
        } catch(ArithmeticException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }
}
