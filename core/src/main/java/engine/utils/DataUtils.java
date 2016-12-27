package engine.utils;

import java.util.Date;
import java.util.Random;

/**
 * Created by Andrity Zhuk on 12/26/2016.
 */
public class DataUtils
{
    /**
     * Generates random integer within the specified range
     *
     * @param max - maximum allowed value
     * @param min - minimum allowed value
     * @return random integer within the specified range
     */
    public int get_random_num(int max, int min)
    {
        Random random = new Random();

        return random.nextInt(max - min) + min;
    }

    /**
     * Generates randomly generated character
     *
     * @return randomly generated character
     */
    public String getRandomChar()
    {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        return String.valueOf(alphabet.charAt(this.get_random_num(alphabet.length(), 0)));
    }

    /**
     * Generates a string of randomly generated characters
     *
     * @param amount - amount of random characters needed
     * @return string of randomly generated characters
     */
    public String getRandomChars(int amount)
    {
        StringBuffer stringBuffer = new StringBuffer();

        for(int i = 0; i < amount; i++)
        {
            stringBuffer.append(this.getRandomChar());
        }

        return stringBuffer.toString();
    }

    public long getCurrentTimeMillis()
    {
        return new Date().getTime();
    }
}
