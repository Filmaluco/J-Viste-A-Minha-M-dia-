package pt.isec.gps1819g11.javisteaminhamedia.DialogInputs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
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

import pt.isec.gps1819g11.javisteaminhamedia.MainActivity;
import pt.isec.gps1819g11.javisteaminhamedia.Models.Student;
import pt.isec.gps1819g11.javisteaminhamedia.R;


@SuppressLint("ValidFragment")
public class UpdateGradesDialog extends DialogFragment implements View.OnClickListener {

    //TODO: expand for Prediction (Constructor sets hint and title text)
    public Dialog dialog;
    private Button cancel, update;
    public EditText inputGrade;
    String gradeName;
    MainActivity mainActivity;
    Student student;


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
                   float grade = Integer.parseInt(value);
                    if(grade <= 20 && grade > 9.5 ){
                        //TODO: Update grade
                        try {
                          //  student.setGrade(gradeName, grade);
                            dismiss();
                        }catch(Exception e){
                            Log.i("Excecção","UpgradeDLG exceção: "+e.toString());

                        }
                        finally{ // finally with the objetive for testing, for not stop the app in the dialog
                            dismiss();
                        }
                    }else{
                        inputGrade.setText("");
                        inputGrade.setHint("Nota inserida inválida");
                    }
                }
                break;
            default:
                break;
        }
    }
}
