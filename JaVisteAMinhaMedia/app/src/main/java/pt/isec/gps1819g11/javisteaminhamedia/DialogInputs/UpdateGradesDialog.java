package pt.isec.gps1819g11.javisteaminhamedia.DialogInputs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pt.isec.gps1819g11.javisteaminhamedia.Activities.Fragments.YearFragment;
import pt.isec.gps1819g11.javisteaminhamedia.MainActivity;
import pt.isec.gps1819g11.javisteaminhamedia.Models.Student;
import pt.isec.gps1819g11.javisteaminhamedia.Modules.StudentManager;
import pt.isec.gps1819g11.javisteaminhamedia.R;


@SuppressLint("ValidFragment")
public class UpdateGradesDialog extends DialogFragment implements View.OnClickListener {

    public Dialog dialog;
    private Button cancel, update;
    public EditText inputGrade;
    StudentManager studentManager;
    String gradeName;
    MainActivity mainActivity;
    Student student;

    private DialogInterface.OnDismissListener onDismissListener;


    @SuppressLint("ValidFragment")
    public UpdateGradesDialog(Student s, String gradeName){
        student = s;
        this.gradeName = gradeName;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.update_grade_dialog, container);
        cancel = (Button) v.findViewById(R.id.btnCancel);
        update = (Button) v.findViewById(R.id.btnUpdate);
        inputGrade = (EditText) v.findViewById(R.id.grade);
        mainActivity = (MainActivity) getActivity();
        studentManager = new StudentManager(mainActivity);
        cancel.setOnClickListener(this);
        update.setOnClickListener(this);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancel:{
                    dismiss();
                break;
            }
            case R.id.btnUpdate:

                String value = String.valueOf(inputGrade.getText());
                if(!value.isEmpty()){
                   float grade = Float.parseFloat(value);
                    if(grade <= 20 && grade >= 9.5 ){
                        try {
                            student.setGrade(gradeName, grade);
                            student.calculateAverage();
                            student.convertToBologna();
                            studentManager.savesStudent(student);

                            dismiss();
                        }catch(Exception e){
                            Log.i("Excecção","UpgradeDLG exceção: "+e.toString());

                        }

                    }else{
                        inputGrade.setText("");
                        inputGrade.setHintTextColor(getResources().getColor(R.color.colorPrimary));
                        inputGrade.setHint(R.string.invalid_inserted_grade);
                    }
                }
                break;
            default:
                break;
        }
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }
}
