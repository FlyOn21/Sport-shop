package utils;

import java.util.Random;

public class RandomGenerator {

    private final Random random = new Random();
    public String generate6DigitString() {
        int randomNumber = random.nextInt(900000) + 100000;
        return String.valueOf(randomNumber);
    }
}
