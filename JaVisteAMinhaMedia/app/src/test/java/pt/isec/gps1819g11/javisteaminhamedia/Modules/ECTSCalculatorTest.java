package pt.isec.gps1819g11.javisteaminhamedia.Modules;

import org.junit.Test;

import pt.isec.gps1819g11.javisteaminhamedia.Models.Student;

import static org.junit.Assert.*;

public class ECTSCalculatorTest {

    @Test
    public void getAverage() {

        Student input = new Student();

        float output = ECTSCalculator.getAverage(input);

        float expected = 10F;
        float delta = 0.01F;

        assertEquals(expected, output, delta);
    }
}