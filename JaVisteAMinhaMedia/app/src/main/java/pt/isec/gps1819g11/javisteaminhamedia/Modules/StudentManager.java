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
    //Protected Variables
    Context context;

    //----------------------------------------------------------------------------------------------
    //      CONSTRUCTOR'S
    //----------------------------------------------------------------------------------------------

    /**
     * Is responsible for saving and updating all user related data in the system
     */
    public StudentManager(Context context) {
        this.context = context;
        if(context.getFileStreamPath(USER_DATA_FILE).exists()){
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
        Student loadedStudent = new Student();
        ObjectInputStream in = null;

        try {
            in = new ObjectInputStream(
                    new FileInputStream(
                            new File(new File(context.getFilesDir(),"")+File.separator+USER_DATA_FILE)
                    )
            );

            loadedStudent = (Student) in.readObject();

        } catch (FileNotFoundException e) {
            Log.e(TAG, "Failed to load student data, file not founded \n", e);
        } catch (IOException e) {
            Log.e(TAG, "Failed to load student data, failed to open file <"+USER_DATA_FILE+" \n", e);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "Failed to load student data, failed to load data from memory \n", e);
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
        student.setBranch(newBranch, loadCourses(newBranch));
        this.savesStudent(student);
    }

    /**
     * Saves all student information in the internal storage
     * @param student
     */
    public void savesStudent(Student student){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = context.openFileOutput(USER_DATA_FILE, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(student);
        } catch (FileNotFoundException e) {
            Log.w(TAG, "Failed to create <" + USER_DATA_FILE + ">");
        } catch (IOException e) {
            Log.w(TAG, "Failed to create new student data file \n" + e);
        }finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing userData <" + USER_DATA_FILE + "> \n", e);
                }
            }
        }
    }

    // Private Methods -----------------------------------------------------------------------------

    /**
     * This method creates all the default information (should only be used when theres no previous data)
     *
     */
    private void createNewStudent(){
        Student defaultStudent = new Student();
        defaultStudent.setBranch(Branch.DA, loadCourses(Branch.DA));
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = context.openFileOutput(USER_DATA_FILE, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(defaultStudent);
        } catch (FileNotFoundException e) {
            Log.w(TAG, "Failed to create <" + USER_DATA_FILE + ">");
        } catch (IOException e) {
            Log.w(TAG, "Failed to create new student data file \n" + e);
        }finally {
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing user data <" + USER_DATA_FILE + "> \n", e);
                }
            }
        }
    }


    /**
     * Loads all courses associated with given branch
     *
     * @param branch
     * @return courses of the given branch
     */
    private Map<String, Course> loadCourses(Branch branch){

        Map<String, Course> courses = new HashMap<>();

        try {
            switch (branch) {
                case DA:
                    courses = loadCourseFromAsset(ASSET_ISEC_DA_FILE);
                    break;
                case SI:
                    courses = loadCourseFromAsset(ASSET_ISEC_SI_FILE);
                    break;
                case RAS:
                    courses = loadCourseFromAsset(ASSET_ISEC_RAS_FILE);
                    break;
                default:
                    Log.w(TAG, "Branch <" + branch.name() + "> is not yet implemented");
                    throw new UnsupportedOperationException("Branch <" + branch.name() + "> is not yet implemented" );
            }
        }catch (IllegalAccessException e){
                Log.e(TAG, "Branch <" + branch.name() + "> failed to load from memory \n", e);
                throw new IllegalStateException("Branch <" + branch.name() + "> was not loaded");
        }

        return courses;
    }

    /**
     * Loads specific branch courses from the specified file
     * @param assetName
     * @return hash map from the specified map
     */
    private Map<String,Course> loadCourseFromAsset(String assetName) throws IllegalAccessException {
        BufferedReader reader = null;
        Map<String, Course> course = new HashMap<>();
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
                    int ano  = Integer.parseInt(tokenizer.nextToken());
                    int semestre =  Integer.parseInt(tokenizer.nextToken());
                    int ects = Integer.parseInt(tokenizer.nextToken());
                    float grade = Float.parseFloat(tokenizer.nextToken());
                    Tag tag = Tag.valueOf(tokenizer.nextToken());
                    String tempName = tokenizer.nextToken("");

                    Course tempCourse = new Course(tempName, tag, ects, grade,ano,semestre);

                    course.put(tempCourse.getName(), tempCourse);
                }catch (NumberFormatException e){
                    Log.e(TAG, "Failed to parse the value on <"+readLine+">", e);
                }catch (IllegalArgumentException  e){
                    Log.e(TAG, "Failed to load the tag on <"+readLine+"> \n", e);
                }
                catch (Exception e){
                    Log.e(TAG, "Failed to load line <"+readLine+"> \n", e);
                }
            }

        } catch (IOException e) {
            Log.e(TAG, "Error loading asset <" + assetName + "> \n", e );
            throw new IllegalAccessException("Can't access file <" + assetName);
        } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                Log.e(TAG, "Error closing asset <" + assetName + "> \n", e );
            }
        }
    }

        return course;
    }

    /**
     * Checks if the given file exists in the app assets
     * @param assetName Asset Name
     * @return true if exists
     */
    private boolean assetExist(String assetName) {
        InputStream file = null;
        try {
            file = context.getAssets().open(assetName);
        } catch (IOException e) {
            Log.w(TAG, "Asset doesn't exit");
        }finally {
            if(file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    Log.e(TAG, "Failed to close asset <" + assetName + ">", e);
                }
            }

        }
        return file != null;
    }




}
