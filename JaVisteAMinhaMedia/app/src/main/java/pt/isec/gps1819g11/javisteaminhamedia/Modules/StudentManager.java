package pt.isec.gps1819g11.javisteaminhamedia.Modules;

import java.util.Map;

import pt.isec.gps1819g11.javisteaminhamedia.Enumerations.Branch;
import pt.isec.gps1819g11.javisteaminhamedia.Models.Course;
import pt.isec.gps1819g11.javisteaminhamedia.Models.Student;

/**
 * @version 1.0
 */
public class StudentManager {

    //Variables
    //----------------------------------------------------------------------------------------------
    //Static Variables
        // Assets Files
    private static final String ISEC_DA_FILE_NAME = "isecDA.txt";

    //----------------------------------------------------------------------------------------------
    //      CONSTRUCTOR'S
    //----------------------------------------------------------------------------------------------

    /**
     * Is responsible for saving and updating all user related data in the system
     */
    public StudentManager() {
        //TODO: check if its the first time running
            // if it is create files
    }


    //----------------------------------------------------------------------------------------------
    //      Methods
    //----------------------------------------------------------------------------------------------
    // Public Methods ------------------------------------------------------------------------------

    /**
     * Loads all previously saved data
     * @return Student instance
     */
    public Student loadStudent(){
        throw new UnsupportedOperationException("Operation not implemented yet");
    }

    /**
     *
     * Updates student branch <b>does not save alteration in memory</b>
     *
     * @param student student instance to update
     * @param newBranch to attribute the student
     */
    public void updateStudentBranch(Student student, Branch newBranch){
        throw new UnsupportedOperationException("Operation not implemented yet");
    }

    /**
     * Saves all student information in the internal storage
     * @param student
     */
    public void savesStudent(Student student){

    }

    // Private Methods -----------------------------------------------------------------------------

    /**
     * This method creates all the default information (should only be used when theres no previous data)
     *
     */
    private void createNewStudent(){
        throw new UnsupportedOperationException("Operation not implemented yet");
    }


    /**
     * Loads all coures associated with given branch
     *
     * @param branch
     * @return courses of the given branch
     */
    private Map<String, Course> loadGrades(Branch branch){
        throw new UnsupportedOperationException("Operation not implemented yet");
    }


}
