package pt.isec.gps1819g11.javisteaminhamedia.Models;

import java.io.Serializable;
import java.util.Map;
import pt.isec.gps1819g11.javisteaminhamedia.Enumerations.Branch;

/**
 * @version 1.0
 */
public class Student implements Serializable{

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
    private int getCompletedECTs() {
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
    private Map<String, Course> getCourses(){
        return courses;
    }

    //----------------------------------------------------------------------------------------------
    //      SETTERS
    //----------------------------------------------------------------------------------------------

    /**
     *
     * @param branch defines user current branch
     * @param courses hashMap already built with the grades
     */
    public void setBranch(Branch branch, Map<String, Course> courses) {
        this.branch = branch;
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

    // Private Methods -----------------------------------------------------------------------------
    /**
     *
     * @return float value of the calculated average
     */
    private float calculateAverage(){
        throw new UnsupportedOperationException("Operation not implemented yet");
    }

    /**
     *
     * @return float value of the predicted average
     */
    private float calculatePrediction(){
        throw new UnsupportedOperationException("Operation not implemented yet");
    }

    /**
     *
     * @param average to convert to bologna ranking system
     * @return null if it couldn't convert <br>
     *     char value of the conversation
     */
    private char convertToBologna(float average){
        throw new UnsupportedOperationException("Operation not implemented yet");
    }

}

