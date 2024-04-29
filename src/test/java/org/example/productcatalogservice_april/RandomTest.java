package org.example.productcatalogservice_april;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class RandomTest {

//    public static  void main(String a[]) {
//        testRandom();
//    }

    @Test
    void testRandom() {
        Random random = new Random();
        int n = random.nextInt(10);
        assert(n < 5);
    }
}
