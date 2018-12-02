package pt.isec.gps1819g11.javisteaminhamedia.Modules;

import java.text.DecimalFormat;
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
    private int higherTag;
    public Prediction(Student t){
        student = t;
        coursesLeft = 0;
        higherTag = 1;
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
                scoreLeft = student.getIntendedAverage() - student.getAverage();
                newAverage = 0F;
                switch(higherTag){
                    case 1:
                        if(c.getTag().equals("Logic"))
                            newAverage = student.getIntendedAverage() + ((scoreLeft / coursesLeft) * 0.5F);
                        else if (c.getTag().equals("Networking"))
                            newAverage = student.getIntendedAverage() + ((scoreLeft / coursesLeft) * 0.05F);
                        else if (c.getTag().equals("Neutral"))
                            newAverage = student.getIntendedAverage() + ((scoreLeft / coursesLeft) * 0.1F);
                        else
                            newAverage = student.getIntendedAverage() + ((scoreLeft / coursesLeft) * 0.16F);
                        break;
                    case 2:
                        if(c.getTag().equals("Theory"))
                            newAverage = student.getIntendedAverage() + ((scoreLeft / coursesLeft) * 0.5F);
                        else if (c.getTag().equals("Networking"))
                            newAverage = student.getIntendedAverage() + ((scoreLeft / coursesLeft) * 0.05F);
                        else if (c.getTag().equals("Neutral"))
                            newAverage = student.getIntendedAverage() + ((scoreLeft / coursesLeft) * 0.1F);
                        else
                            newAverage = student.getIntendedAverage() + ((scoreLeft / coursesLeft) * 0.16F);
                        break;
                    case 3:
                        if (c.getTag().equals("Networking"))
                            newAverage = student.getIntendedAverage() + ((scoreLeft / coursesLeft) * 0.5F);
                        else if (c.getTag().equals("Neutral"))
                            newAverage = student.getIntendedAverage() + ((scoreLeft / coursesLeft) * 0.1F);
                        else
                            newAverage = student.getIntendedAverage() + ((scoreLeft / coursesLeft) * 0.15F);
                        break;
                    case 4:
                        if(c.getTag().equals("Group"))
                            newAverage = student.getIntendedAverage() + ((scoreLeft / coursesLeft) * 0.5F);
                        else if (c.getTag().equals("Networking"))
                            newAverage = student.getIntendedAverage() + ((scoreLeft / coursesLeft) * 0.05F);
                        else if (c.getTag().equals("Neutral"))
                            newAverage = student.getIntendedAverage() + ((scoreLeft / coursesLeft) * 0.1F);
                        else
                            newAverage = student.getIntendedAverage() + ((scoreLeft / coursesLeft) * 0.16F);
                        break;
                    case 5:
                        if(c.getTag().equals("Programing"))
                            newAverage = student.getIntendedAverage() + ((scoreLeft / coursesLeft) * 0.5F);
                        else if (c.getTag().equals("Networking"))
                            newAverage = student.getIntendedAverage() + ((scoreLeft / coursesLeft) * 0.05F);
                        else if (c.getTag().equals("Neutral"))
                            newAverage = student.getIntendedAverage() + ((scoreLeft / coursesLeft) * 0.1F);
                        else
                            newAverage = student.getIntendedAverage() + ((scoreLeft / coursesLeft) * 0.16F);
                        break;
                }

                if(c.getEcts() == 3)
                    newAverage = student.getAverage() + ((scoreLeft / coursesLeft));

                if (c.getEcts() == 27 || c.getEcts() == 3)
                    prediction = newAverage * (student.getCompletedECTs() + c.getEcts());
                else
                    prediction = newAverage * student.getCompletedECTs();

                for(Course completed: this.student.getCourses().values())
                    if(completed.getGrade() >= 9.5F && completed.getGrade() <= 20F)
                        prediction -= (completed.getGrade() * completed.getEcts());

                prediction /= c.getEcts();

                if(prediction > 20F)
                    prediction /= 2;

                DecimalFormat df = new DecimalFormat("#.###");
                String averageWithOneDecimalPlace = df.format(prediction);
                prediction = Float.parseFloat(averageWithOneDecimalPlace);

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

        if(this.getStudent().getBranch().equals("RAS"))
            higherTag = 3;
        else {
            if(higherTag < countTag2)
                higherTag = 2;
            if(higherTag < countTag4)
                higherTag = 4;
            if(higherTag < countTag5)
                higherTag = 5;
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

        if(this.getTestAverage(testStudent) < student.getIntendedAverage())
            return false;
        else
            return true;

    }

    public float getTestAverage(Student t){

        float average = 0F;

        for (Course c: t.getCourses().values()) {
            if (c.getGrade() < 9.5)
                average += c.getEcts() * 20f;
            else
                average += (c.getGrade() * c.getEcts());
        }

        return average / 180;
    }

}
