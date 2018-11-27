package pt.isec.gps1819g11.javisteaminhamedia.Modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        for (Course c: t.getCourses().values()) {
            if(c.getGrade() < 9.5)
                coursesLeft++;
        }
    }

    public Student getStudent() {
        return student;
    }

    /**
     * Calculates the predicted grades for the course that the student hasn't completed yet
     * @return an ArrayList with the predicted grades, will be empty if they can't be predicted
     */
    public ArrayList<Course> getPrediction(){
        float prediction;
        int newECTS;
        float scoreLeft;
        float newAverage;
        ArrayList<Course> predictedCourses = new ArrayList<>();

        if(!canPredict())
            return predictedCourses;

        if(!isReachable())
            return predictedCourses;

        for (Course c: this.getStudent().getCourses().values()) {
            prediction = 0F;
            if(c.getGrade() < 9.5){
                newECTS = student.getCompletedECTs() + c.getEcts();
                scoreLeft = student.getIntendedAverage() - student.getAverage();
                newAverage = student.getAverage() + (scoreLeft / coursesLeft);

                prediction = newAverage * newECTS;

                for(Course completed: this.student.getCourses().values())
                    prediction -= (completed.getGrade() * completed.getEcts());
                prediction /= c.getEcts();

                predictedCourses.add(new Course(c.getName(),
                                                Tag.valueOf(c.getTag()),
                                                c.getEcts(),
                                                prediction,
                                                c.getAno(),
                                                c.getSemestre()));
            }



        }

        return predictedCourses;
    }

    /**
     * checks if the remaining grades are predictable or not by checking the number of courses completed by tag
     * @return whether the grade is predictable or not
     */
    public boolean canPredict(){

        if (student.getCompletedECTs()==180)
            return false;

        int countTag1 = 0;
        int countTag2 = 0;
        int countTag3 = 0;
        int countTag4 = 0;
        int countTag5 = 0;

        for (Course c: this.getStudent().getCourses().values()) {
            if (c.getGrade() >= 9.5){
                switch (c.getTag()){
                    case "Logic":
                        countTag1++;
                        break;
                    case "Theory":
                        countTag2++;
                        break;
                    case "Networking":
                        countTag3++;
                        break;
                    case "Group":
                        countTag4++;
                        break;
                    case "Programing":
                        countTag5++;
                        break;
                    case "Neutral":
                        break;
                }
            }
        }

        if(countTag1 > 0 && countTag2 > 0 && countTag3 > 0 && countTag4 > 0 && countTag5 > 0)
            return true;
        else
            return false;
    }

    /**
     * Checks if the student can reach the intended average with the one he currently has
     * @return whether the prediction is reachable or not
     */
    public boolean isReachable(){

        float testIntendedAverage = student.getIntendedAverage();
        float testAverage = student.getAverage();
        float testPredictionAverage = student.getPredictionAverage();
        char testBologna = student.getBologna();
        int testCompletedECTS = student.getCompletedECTs();


        Student testStudent = new Student(testIntendedAverage ,
                                            testAverage,
                                            testPredictionAverage,
                                            testBologna,
                                            testCompletedECTS);

        String testString = student.getBranch();
        Branch testBranch = Branch.valueOf(testString);
        Map<String, Course> testMap = new HashMap<>();
        testMap.putAll(student.getCourses());
        testStudent.setBranch(testBranch,testMap);

        for(Course c: testStudent.getCourses().values())
            if(c.getGrade() < 9.5)
                c.setGrade(20F);

        if(this.getTestAverage(testStudent) < student.getIntendedAverage())
            return false;
        else
            return true;

    }

    public float getTestAverage(Student t){

        float average = 0F;

        for (Course c: t.getCourses().values()) {
            average += (c.getGrade() * c.getEcts());
        }

        return average / 180;
    }

}
