package pt.isec.gps1819g11.javisteaminhamedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import pt.isec.gps1819g11.javisteaminhamedia.Activities.GradesActivity;
import pt.isec.gps1819g11.javisteaminhamedia.Models.Student;
import pt.isec.gps1819g11.javisteaminhamedia.Modules.StudentManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StudentManager studentManager = new StudentManager(this);
        Student student = studentManager.loadStudent();


        Intent intent = new Intent(this,GradesActivity.class);
        startActivity(intent);
    }
}
