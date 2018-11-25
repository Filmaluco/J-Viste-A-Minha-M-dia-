package pt.isec.gps1819g11.javisteaminhamedia.Activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pt.isec.gps1819g11.javisteaminhamedia.MainActivity;
import pt.isec.gps1819g11.javisteaminhamedia.R;

public class AverageGradeDialog extends DialogFragment implements View.OnClickListener {

    public Dialog dialog;
    private Button cancel, update;
    public EditText inputGrade;
    TextView title;
    MainActivity mainActivity;

    public AverageGradeDialog(){}


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.update_grade_dialog, container);
        cancel = (Button) v.findViewById(R.id.btnCancel);
        update = (Button) v.findViewById(R.id.btnUpdate);
        inputGrade = (EditText) v.findViewById(R.id.grade);
        title = (TextView) v.findViewById(R.id.title);
        title.setText("Media Pretendida");
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
                        //TODO: Update average grade
                        dismiss();
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
