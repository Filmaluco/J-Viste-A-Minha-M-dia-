package pt.isec.gps1819g11.javisteaminhamedia;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;


import pt.isec.gps1819g11.javisteaminhamedia.Activities.AverageGradeDialog;
import pt.isec.gps1819g11.javisteaminhamedia.Activities.BranchSelectionDialog;
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
    public Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentManager = new StudentManager(this);
        student = studentManager.loadStudent();

        setupComponents();

        setupListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_items,menu);
        return true;
    }

    private void setupComponents() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gradesFragment = new GradesFragment();
        predictionFragment = new PredictionFragment();

        mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        bottomNavbar = (BottomNavigationView) findViewById(R.id.main_navbar);

        bottomNavbar.setSelectedItemId(R.id.navbar_prediction);
        setFragment(predictionFragment);
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

    public void SetStudentBranch(MenuItem item) {
        BranchSelectionDialog branchSelection = new BranchSelectionDialog();
        branchSelection.show(getSupportFragmentManager(), null);
    }

    public void SetPretendedAverage(MenuItem item) {
        AverageGradeDialog averageGrade = new AverageGradeDialog(student);
        averageGrade.show(getSupportFragmentManager(), null);
    }
}