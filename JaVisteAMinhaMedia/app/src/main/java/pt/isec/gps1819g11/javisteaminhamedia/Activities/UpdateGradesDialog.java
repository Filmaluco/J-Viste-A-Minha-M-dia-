package pt.isec.gps1819g11.javisteaminhamedia.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pt.isec.gps1819g11.javisteaminhamedia.Models.Student;
import pt.isec.gps1819g11.javisteaminhamedia.R;


public class UpdateGradesDialog extends Dialog implements View.OnClickListener {

    //TODO: expand for Prediction (Constructor sets hint and title text)

    public Activity activity;
    public Dialog dialog;
    private Button cancel, update;
    public EditText inputGrade;
    Student student;
    String gradeName;
    Activity a; //To save the context of the activity who calls this class
    public UpdateGradesDialog(Activity a, Student student, String gradeName){
        super(a);
        this.a=a;
        this.activity=a;
        this.gradeName = gradeName;
        this.student = student;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.update_grade_dialog);
        cancel = (Button) findViewById(R.id.btnCancel);
        update = (Button) findViewById(R.id.btnUpdate);
        inputGrade = (EditText) findViewById(R.id.grade);
        cancel.setOnClickListener(this);
        update.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnCancel:
                activity.finish();
                break;
            case R.id.btnUpdate:
                //TODO: Update grade
                String value = String.valueOf(inputGrade.getText());
                if(!value.isEmpty()){
                   float grade = Integer.parseInt(value);
                    if(grade < 20 && grade > 9.5 ){
                        //student.setGrade(gradeName,grade);
                        dismiss();
                    }else{
                        Toast.makeText(a.getBaseContext(),"Nota inserida inválida", Toast.LENGTH_SHORT).show();

                    }
                }


                break;
            default:
                break;
        }


    }
}
