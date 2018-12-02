package pt.isec.gps1819g11.javisteaminhamedia.Modules;

import java.util.Map;

import pt.isec.gps1819g11.javisteaminhamedia.Models.Course;
import pt.isec.gps1819g11.javisteaminhamedia.Models.Student;

/**
 * This module is responsible for calculation of the ECTS average
 *
 * @version 0.0
 */
public class ECTSCalculator {
    public static float getAverage(Student student){
        float average = 0;
        int completedECTS = 0;

        for(Course c : student.getCourses().values()){
            if(c.getGrade() >= 9.5 && c.getGrade() <= 20)
            {
                average += (c.getGrade()*c.getEcts());
                completedECTS += c.getEcts();
            }

        }
       return average / completedECTS;
    }
}
