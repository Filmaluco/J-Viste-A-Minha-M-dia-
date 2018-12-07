package pt.isec.gps1819g11.javisteaminhamedia.Modules;

import org.junit.Test;

import java.util.ArrayList;

import pt.isec.gps1819g11.javisteaminhamedia.Models.Course;
import pt.isec.gps1819g11.javisteaminhamedia.Models.Student;

import static org.junit.Assert.*;

public class PredictionTest {

    @Test
    public void getPrediction() {

        Student input = new Student();
        ArrayList<Course> expected = new ArrayList<>();
        Prediction output = new Prediction(input);

        ArrayList<Course> outputValue = output.getPrediction();
        float delta = 0.5F;

    }
}