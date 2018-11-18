package pt.isec.gps1819g11.javisteaminhamedia.Models;

import pt.isec.gps1819g11.javisteaminhamedia.Enumerations.Tag;

/**
 * @version 1.0
 */
public class Course {
    //Variables
    //----------------------------------------------------------------------------------------------
    //Private Variables
    private String name;
    private Tag tag;
    private int ects;
    private float grade;

    //----------------------------------------------------------------------------------------------
    //      CONSTRUCTOR'S
    //----------------------------------------------------------------------------------------------

    /**
     * @param name course name
     * @param tag course tag (ex: Programming)
     * @param ects ects of the course
     * @param grade current grade
     */
    public Course(String name, Tag tag, int ects, float grade) {
        this.name = name;
        this.tag = tag;
        this.ects = ects;
        this.grade = grade;
    }

    /**
     * @param name course name
     * @param tag course tag (ex: Programming)
     * @param ects ects of the course
     */
    public Course(String name, Tag tag, int ects) {
        this.name = name;
        this.tag = tag;
        this.ects = ects;
        this.grade = 0;
    }


    //----------------------------------------------------------------------------------------------
    //      GETTERS
    //----------------------------------------------------------------------------------------------

    /**
     *
     * @return course name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return tag name
     */
    public String getTag() {
        return tag.name();
    }

    /**
     *
     * @return number of ects
     */
    public int getEcts() {
        return ects;
    }

    /**
     *
     * @return current grade value
     */
    public float getGrade() {
        return grade;
    }

    //----------------------------------------------------------------------------------------------
    //      SETTERS
    //----------------------------------------------------------------------------------------------

    /**
     *
     * @param value set current grade value
     */
    public void setGrade(float value) {
        this.grade = value;
    }
}

