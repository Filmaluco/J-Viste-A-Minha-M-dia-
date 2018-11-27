package pt.isec.gps1819g11.javisteaminhamedia.Models;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import pt.isec.gps1819g11.javisteaminhamedia.Enumerations.Branch;
import pt.isec.gps1819g11.javisteaminhamedia.Modules.Bologna;
import pt.isec.gps1819g11.javisteaminhamedia.Modules.Prediction;

/**
 * @version 1.0
 */

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    //Variables
    //----------------------------------------------------------------------------------------------
    //Private Variables
    private Branch branch;
    private float intendedAverage;
    private float average, predectionAverage;
    private char bologna;
    private Map<String, Course> courses;
    private int completedECTs;

    //----------------------------------------------------------------------------------------------
    //      CONSTRUCTOR'S
    //----------------------------------------------------------------------------------------------

    /**
     * Default Constructor <br>
     * Generates the Student with default values (does not generate the courses, please check the StudentManager for loading student courses effectively)
     */
    public Student()
    {
        this.intendedAverage = 12F;
        this.average = 0F;
        this.predectionAverage = 0F;
        this.bologna = '#';
        this.completedECTs = 0;
        this.courses = null;
    }

    /**
     * (does not generate the courses, please check the StudentManager for loading student courses effectively)
     *
     * @param intendedAverage average the student pretends to reach
     * @param average current student average
     * @param predectionAverage current student
     * @param bologna current studen bologna grade
     * @param completedECTs
     */
    public Student(float intendedAverage, float average, float predectionAverage, char bologna, int completedECTs) {
        this.intendedAverage = intendedAverage;
        this.average = average;
        this.predectionAverage = predectionAverage;
        this.bologna = bologna;
        this.completedECTs = completedECTs;
        this.courses = null;
    }


    //----------------------------------------------------------------------------------------------
    //      GETTERS
    //----------------------------------------------------------------------------------------------


    /**
     *
     * @return the student branch
     */
    public String getBranch() {
        return branch.name();
    }

    /**
     *
     * @return the student intended average
     */
    public float getIntendedAverage() {
        return intendedAverage;
    }

    /**
     *
     * @return the student current average
     */
    public float getAverage() {
        return average;
    }

    /**
     *
     * @return the student predicted average
     */
    public float getPredictionAverage() {
        return predectionAverage;
    }

    /**
     *
     * @return the student current bologna rank
     */
    public char getBologna() {
        return bologna;
    }

    /**
     *
     * @return number of ects the student as completed
     */
    public int getCompletedECTs() {
        return completedECTs;
    }

    /**
     *
     * @return the simulation list of the courses the student needs to improve to reach the pretended average
     */
    private String[] getSimulationGrades(){
        throw new UnsupportedOperationException("Operation not implemented yet");
    }

    /**
     *
     * @return the hashMap of the courses this student attends
     */
    public Map<String, Course> getCourses(){
        return courses;
    }



    //----------------------------------------------------------------------------------------------
    //      SETTERS
    //----------------------------------------------------------------------------------------------

    /**
     * Sets the student branch, in order to load the courses check the StudentManager documentation
     * //TODO:: add sample code?
     * @see pt.isec.gps1819g11.javisteaminhamedia.Modules.StudentManager
     * @param branch defines user current branch
     * @param courses hashMap already built with the grades
     */
    public void setBranch(Branch branch, Map<String, Course> courses) {
        this.branch = branch;
        this.courses = courses;
    }

    /**
     *
     * @param pretendedAverage sets the average the student intents to reach
     */
    public void setIntendedAverage(float pretendedAverage) {
        this.intendedAverage = pretendedAverage;
    }

    /**
     *
     * @param bologna sets the bologna score
     */
    public void setBologna(char bologna) {
        this.bologna = bologna;
    }

    /**
     *
     * @param key is the course name
     * @param value is the value to associate with that course
     */
    public void setGrade(String key, float value){
        this.courses.get(key).setGrade(value);
    }

    //----------------------------------------------------------------------------------------------
    //      Methods
    //----------------------------------------------------------------------------------------------

    // Public Methods ------------------------------------------------------------------------------

    /**
     *
     * @param year is the year the courses wanted
     * @param semester is the semester of the courses wanted
     * @return an arrayList of courses with the specified year and semester
     */
    public ArrayList<Course> getList(int year, int semester){
        ArrayList<Course> list = new ArrayList<>();

        for (Course c: courses.values()){
            if (c.getAno() == year && c.getSemestre() == semester)
                list.add(c);
        }

        return list;
    }


    // Private Methods -----------------------------------------------------------------------------
    /**
     *
     * @return float value of the calculated average
     */
    private float calculateAverage(){
        average = 0;
        int nGrades = 0;
        for(Course c : courses.values()){
            if(c.getGrade() > 9.5)
            {
                average += (c.getGrade()*c.getEcts());
                nGrades++;
            }

        }

        return average/=nGrades;
    }

    /**
     * @param c is the course which will have its grade predicted
     * @return float value of the predicted grade
     */
    private float calculatePrediction(Course c){
        float prediction = 0F;
        int newECTS = completedECTs + c.getEcts();
        float scoreLeft = intendedAverage - average;
        float newAverage = average /*+ (scoreLeft/ "numero de cadeiras que faltam fazer")*/;

        prediction = newAverage * newECTS;

        for(Course completed : courses.values())
            prediction -= (completed.getGrade() * completed.getEcts());

        prediction /= c.getEcts();


        return prediction;
    }

    /**
     * Converts the user average to the bologna ranking system
     */
    public void convertToBologna(){
        char response = '#';
        try {
             response = new Bologna().execute(this.average).get();
        } catch (Exception e) {
            Log.w(Bologna.TAG , "Failed to retreive data \n " + e);
        }
        this.setBologna(response);
    }

}

