package pt.isec.gps1819g11.javisteaminhamedia;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;


import pt.isec.gps1819g11.javisteaminhamedia.Activities.Fragments.GradesFragment;
import pt.isec.gps1819g11.javisteaminhamedia.Activities.Fragments.PredictionFragment;
import pt.isec.gps1819g11.javisteaminhamedia.Models.Student;
import pt.isec.gps1819g11.javisteaminhamedia.Modules.StudentManager;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavbar;
    private FrameLayout mainFrame;

    private GradesFragment gradesFragment;
    private PredictionFragment predictionFragment;

    public StudentManager studentManager;
    public Student student;
    public Toolbar tbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentManager = new StudentManager();
       //student = studentManager.loadStudent();
        Student student = new Student();





        setupComponents();

        setupListeners();
    }



    private void setupComponents() {
        gradesFragment = new GradesFragment();
        predictionFragment = new PredictionFragment();

        setFragment(gradesFragment);

        mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        bottomNavbar = (BottomNavigationView) findViewById(R.id.main_navbar);
    }

    private void setupListeners() {
        bottomNavbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.navbar_grades:
                        setFragment(gradesFragment);
                        return true;
                    case R.id.navbar_prediction:
                        setFragment(predictionFragment);
                        return true;

                    default:
                        return false;
                }

            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fManager= getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putSerializable("student",student); //Bundle has student serialized
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
