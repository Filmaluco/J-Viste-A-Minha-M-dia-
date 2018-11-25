package pt.isec.gps1819g11.javisteaminhamedia.Modules;

import pt.isec.gps1819g11.javisteaminhamedia.Models.Course;
import pt.isec.gps1819g11.javisteaminhamedia.Models.Student;

/**
 * This module is responsible for the prediction grade system
 *
 * @version 0.0
 */
public class Prediction {
<<<<<<< HEAD

    public float getPrediction(Student s, Course c){
        int countLeft = 0;
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
=======
>>>>>>> parent of 37b3c8e... Merge pull request #6 from Filmaluco/BranchVasco
}
