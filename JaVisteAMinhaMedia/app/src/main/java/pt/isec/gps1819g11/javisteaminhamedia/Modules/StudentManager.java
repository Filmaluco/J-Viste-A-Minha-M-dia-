package pt.isec.gps1819g11.javisteaminhamedia.Modules;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import pt.isec.gps1819g11.javisteaminhamedia.Enumerations.Branch;
import pt.isec.gps1819g11.javisteaminhamedia.Enumerations.Tag;
import pt.isec.gps1819g11.javisteaminhamedia.Models.Course;
import pt.isec.gps1819g11.javisteaminhamedia.Models.Student;

/**
 * @version 2.0
 */
public class StudentManager {

    //Variables
    //----------------------------------------------------------------------------------------------
    //Static Variables
        // Control variables
    private static final String TAG = "[StudentManager]";
        // Assets Files
    private static final String USER_DATA_FILE = "student.bin";
    private static final String ASSET_ISEC_DA_FILE = "isecDA.txt";
    private static final String ASSET_ISEC_SI_FILE = "isecSI.txt";
    private static final String ASSET_ISEC_RAS_FILE = "isecRAS.txt";
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

        //------------------------------------------------------------------------------------------
        Log.i(TAG, "No data found, creating new Student data");
        createNewStudent();
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
        Student loadedStudent;
        ObjectInputStream in = null;

        try {
            in = new ObjectInputStream(
                    new FileInputStream(
                            new File(new File(context.getFilesDir(),"")+File.separator+USER_DATA_FILE)
                    )
            );

            loadedStudent = (Student) in.readObject();

        } catch (Exception e) {
            loadedStudent = new Student();
            Log.w(TAG, "Failed to load student data \n" + e);
        }finally {
            if(in != null){
                try {
                  in.close();
                }catch (Exception ignored){}
            }
        }

        return loadedStudent;
    }

    /**
     *
     * Updates student branch <b>does not save alteration in memory</b>
     *
     * @param student student instance to update
     * @param newBranch to attribute the student
     */
    public void updateStudentBranch(Student student, Branch newBranch){
        student.setBranch(newBranch, loadGrades(newBranch));
        this.savesStudent(student);
    }

    /**
     * Saves all student information in the internal storage
     * @param student
     */
    public void savesStudent(Student student){
        FileOutputStream fos;

        try {
            fos = context.openFileOutput(USER_DATA_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(student);
            oos.close();
        } catch (FileNotFoundException e) {
            Log.w(TAG, "Failed to create <" + USER_DATA_FILE + ">");
        } catch (IOException e) {
            Log.w(TAG, "Failed to create new student data file \n" + e);
        }
    }

    // Private Methods -----------------------------------------------------------------------------

    /**
     * This method creates all the default information (should only be used when theres no previous data)
     *
     */
    private void createNewStudent(){
        Student defaultStudent = new Student();
        defaultStudent.setBranch(Branch.DA, loadGrades(Branch.DA));
        FileOutputStream fos;

        try {
            fos = context.openFileOutput(USER_DATA_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(defaultStudent);
            oos.close();
        } catch (FileNotFoundException e) {
            Log.w(TAG, "Failed to create <" + USER_DATA_FILE + ">");
        } catch (IOException e) {
            Log.w(TAG, "Failed to create new student data file \n" + e);
        }
    }


    /**
     * Loads all courses associated with given branch
     *
     * @param branch
     * @return courses of the given branch
     */
    private Map<String, Course> loadGrades(Branch branch){

        Map<String, Course> grades = new HashMap<>();

        try {
            switch (branch) {
                case DA:
                    grades = loadGradesFromAsset(ASSET_ISEC_DA_FILE);
                    break;
                case SI:
                    grades = loadGradesFromAsset(ASSET_ISEC_SI_FILE);
                    break;
                case RAS:
                    grades = loadGradesFromAsset(ASSET_ISEC_RAS_FILE);
                    break;
                default:
                    Log.w(TAG, "Branch <" + branch.name() + "> is not yet implemented");
                    throw new UnsupportedOperationException("Branch <" + branch.name() + "> is not yet implemented");
            }
        }catch (Exception e){
                Log.w(TAG, "Branch <" + branch.name() + "> failed to load \n" + e);
                throw new UnsupportedOperationException("Branch <" + branch.name() + "> is not yet implemented");
        }

        return grades;
    }

    /**
     * Loads specific branch courses from the specified file
     * @param assetName
     * @return hash map from the specified map
     */
    private Map<String,Course> loadGradesFromAsset(String assetName) throws IllegalAccessException {
        BufferedReader reader = null;
        Map<String, Course> grades = new HashMap<>();
        if(!assetExist(assetName)){
            throw new IllegalAccessException("Can't access file <" + assetName);
        }

        try {
            reader = new BufferedReader(
                     new InputStreamReader(context.getAssets().open(assetName))
            );

            String readLine;
            while ((readLine = reader.readLine()) != null) {
                try {
                    StringTokenizer tokenizer = new StringTokenizer(readLine);
                    int ects = Integer.parseInt(tokenizer.nextToken());
                    float grade = Float.parseFloat(tokenizer.nextToken());
                    Tag tag = Tag.valueOf(tokenizer.nextToken());
                    String tempName = tokenizer.nextToken("");

                    Course tempCourse = new Course(tempName, tag, ects, grade);

                    grades.put(tempCourse.getName(), tempCourse);
                }catch (Exception e){
                    Log.w(TAG, "Failed to load line <"+readLine+"> \n" + e);
                    continue;
                }
            }


        } catch (IOException e) {
            Log.w(TAG, "Error loading asset <" + assetName + "> \n" + e );
        } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                Log.w(TAG, "Error closing asset <" + assetName + "> \n" + e );
            }
        }
    }

        return grades;
    }

    /**
     * Checks if the given file exists in the app assets
     * @param assetName Asset Name
     * @return true if exists
     */
    private boolean assetExist(String assetName) {
        try {
            InputStream file = context.getAssets().open(assetName);
        } catch (IOException e) {
            Log.w(TAG, "Asset doesn't exit");
            return false;
        }
        return true;
    }

    /**
     * Checks if the given file exists in internal storage
     * @param fname in internal storage
     * @return true if exists
     */
    private boolean fileExist(String fname){
        File file = context.getFileStreamPath(fname);
        return file.exists();
    }


}
