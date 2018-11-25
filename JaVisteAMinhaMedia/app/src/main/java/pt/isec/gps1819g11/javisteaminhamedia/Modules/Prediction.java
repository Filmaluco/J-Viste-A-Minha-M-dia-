package pt.isec.gps1819g11.javisteaminhamedia.Modules;

import pt.isec.gps1819g11.javisteaminhamedia.Models.Student;

/**
 * This module is responsible for the prediction grade system
 *
 * @version 0.0
 */
public class Prediction {

    public float getPrediction(Student s, Course c){
        int countLeft;
        float prediction = 0F;
        int newECTS = s.getCompletedECTs() + c.getEcts();
        float scoreLeft = s.getIntendedAverage() - s.getAverage();
        for(Course x : s.getCourses().values())
            if(x.getGrade() < 9.5)
                countLeft++;
        float newAverage = s.getAverage() + (scoreLeft / countLeft);

        prediction = newAverage * newECTS;

        for(Course completed : s.getCourses().values())
            prediction -= (completed.getGrade() * completed.getEcts());
        prediction /= c.getEcts();
        return prediction;
    }
}
