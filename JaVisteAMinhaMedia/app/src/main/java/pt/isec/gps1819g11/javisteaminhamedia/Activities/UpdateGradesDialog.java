package pt.isec.gps1819g11.javisteaminhamedia.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import pt.isec.gps1819g11.javisteaminhamedia.R;


public class UpdateGradesDialog extends Dialog implements View.OnClickListener {

    //TODO: expand for Prediction (Constructor sets hint and title text)

    public Activity activity;
    public Dialog dialog;
    private Button cancel, update;

    public UpdateGradesDialog(Activity a){
        super(a);
        this.activity=a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.update_grade_dialog);
        cancel = (Button) findViewById(R.id.btnCancel);
        update = (Button) findViewById(R.id.btnUpdate);
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
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
