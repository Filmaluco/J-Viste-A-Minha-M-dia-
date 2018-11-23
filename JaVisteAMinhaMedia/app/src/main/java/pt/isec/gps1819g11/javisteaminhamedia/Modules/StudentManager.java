package pt.isec.gps1819g11.javisteaminhamedia.Modules;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
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
        // Control variables
    private static final String TAG = "[StudentManager]";
        // Assets Files
    private static final String USER_DATA_FILE = "student.txt";
    private static final String ISEC_DA_FILE_NAME = "isecDA.txt";
    //Private Variables
    Context context;

    //----------------------------------------------------------------------------------------------
    //      CONSTRUCTOR'S
    //----------------------------------------------------------------------------------------------

    /**
     * Is responsible for saving and updating all user related data in the system
     */
    public StudentManager(Context context) {
        this.context = context;
        if(fileExist(USER_DATA_FILE)){
            Log.i(TAG, "Student data found");
            return;
        }

        Log.i(TAG, "No data found, creating new Student data");
        try {
            context.openFileOutput(USER_DATA_FILE, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            Log.i(TAG, "Failed to create student data file");
        }

        //TODO: initial data
        //Create from template data

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

    private boolean fileExist(String fname){
        File file = context.getFileStreamPath(fname);
        return file.exists();
    }


}
