package pt.isec.gps1819g11.javisteaminhamedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import pt.isec.gps1819g11.javisteaminhamedia.Activities.GradesActivity;
import pt.isec.gps1819g11.javisteaminhamedia.Models.Student;
import pt.isec.gps1819g11.javisteaminhamedia.Modules.StudentManager;

public class MainActivity extends Activity {
    StudentManager studentManager;
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentManager = new StudentManager();
       // student = studentManager.loadStudent();
        Student student = new Student();


        Intent intent = new Intent(this,GradesActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("student",student); //Bundle has student serialized
        intent.putExtras(bundle); //Intent wraps bundle

        startActivity(intent); //Starting GradesActivity
    }
}
