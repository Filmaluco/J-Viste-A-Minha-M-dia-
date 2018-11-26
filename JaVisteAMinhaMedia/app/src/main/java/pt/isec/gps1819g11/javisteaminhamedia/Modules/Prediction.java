package pt.isec.gps1819g11.javisteaminhamedia.Modules;

import java.util.ArrayList;

import pt.isec.gps1819g11.javisteaminhamedia.Enumerations.Branch;
import pt.isec.gps1819g11.javisteaminhamedia.Enumerations.Tag;
import pt.isec.gps1819g11.javisteaminhamedia.Models.Course;
import pt.isec.gps1819g11.javisteaminhamedia.Models.Student;

/**
 * This module is responsible for the prediction grade system
 *
 * @version 0.0
 */
public class Prediction {

    private Student student;
    private int coursesLeft;
    public Prediction(Student t){
        student = t;
        coursesLeft = 0;
    }

    public Student getStudent() {
        return student;
    }

    /**
     * Calculates the predicted grades for the course that the student hasn't completed yet
     * @return an ArrayList with the predicted grades, will be empty if they can't be predicted
     */
    public ArrayList<Course> getPrediction(){
        int countLeft = 0;
        float prediction;
        ArrayList<Course> predictedCourses = new ArrayList<>();

        if(!canPredict())
            return predictedCourses;

        if(!isReachable())
            return predictedCourses;

        for (Course c: this.getStudent().getCourses().values()) {
            prediction = 0F;
            if(c.getGrade() < 9.5){
                int newECTS = student.getCompletedECTs() + c.getEcts();
                float scoreLeft = student.getIntendedAverage() - student.getAverage();
                float newAverage = student.getAverage() + (scoreLeft / countLeft);

                prediction = newAverage * newECTS;

                for(Course completed: this.student.getCourses().values())
                    prediction -= (completed.getGrade() * completed.getEcts());
                prediction /= c.getEcts();
            }
            predictedCourses.add(new Course(c.getName(), Tag.valueOf(c.getTag()), c.getEcts(), prediction, c.getAno(), c.getSemestre()));
        }

        return predictedCourses;
    }

    /**
     * checks if the remaining grades are predictable or not by checking the number of courses completed by tag
     * @return whether the grade is predictable or not
     */
    public boolean canPredict(){
        int countTag1 = 0;
        int countTag2 = 0;
        int countTag3 = 0;
        int countTag4 = 0;
        int countTag5 = 0;
        int countTag6 = 0;

        //TODO switch
        for (Course c: this.getStudent().getCourses().values()) {
            if (c.getGrade() >= 9.5){
                if(c.getTag().equals("Logic"))
                    countTag1++;
                if(c.getTag().equals("Theory"))
                    countTag2++;
                if(c.getTag().equals("Networking"))
                    countTag3++;
                if(c.getTag().equals("Group"))
                    countTag4++;
                if(c.getTag().equals("Programing"))
                    countTag5++;
                if(c.getTag().equals("Neutral"))
                    countTag6++;
            }
            else
                coursesLeft++;
        }

        if(countTag1 > 0 && countTag2 > 0 && countTag3 > 0 && countTag4 > 0 && countTag5 > 0 && countTag6 > 0)
            return true;
        else
            return false;
    }

    /**
     * Checks if the student can reach the intended average with the one he currently has
     * @return whether the prediction is reachable or not
     */
    public boolean isReachable(){
        Student testStudent = new Student(student.getIntendedAverage(),
                                            student.getAverage(),
                                            student.getPredictionAverage(),
                                            student.getBologna(),
                                            student.getCompletedECTs());

        testStudent.setBranch(Branch.valueOf(student.getBranch()),student.getCourses());

        for(Course c: testStudent.getCourses().values())
            if(c.getGrade() < 9.5)
                c.setGrade(20F);

        if(ECTSCalculator.getAverage(testStudent) < student.getIntendedAverage())
            return false;
        else
            return true;

    }

}
