package pt.isec.gps1819g11.javisteaminhamedia.DialogInputs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import pt.isec.gps1819g11.javisteaminhamedia.Enumerations.Branch;
import pt.isec.gps1819g11.javisteaminhamedia.MainActivity;
import pt.isec.gps1819g11.javisteaminhamedia.R;

public class BranchSelectionDialog extends DialogFragment implements View.OnClickListener {
    public Dialog dialog;
    private Button cancel, update;
    private RadioGroup branchSelection;
    private RadioButton branchSelected;
    private MainActivity mainActivity;

    public BranchSelectionDialog(){}


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.branch_selection_dialog, container);
        cancel = (Button) v.findViewById(R.id.btnCancel);
        update = (Button) v.findViewById(R.id.btnUpdate);
        branchSelection = (RadioGroup) v.findViewById(R.id.rg_branch_selection);
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
                int selectedBranchOption = branchSelection.getCheckedRadioButtonId();

                switch (selectedBranchOption){
                    case R.id.rb_da:
                        mainActivity.studentManager.updateStudentBranch(mainActivity.student, Branch.DA);
                        break;
                    case R.id.rb_si:
                        mainActivity.studentManager.updateStudentBranch(mainActivity.student, Branch.SI);
                        break;

                    case R.id.rb_rd:
                        mainActivity.studentManager.updateStudentBranch(mainActivity.student, Branch.RAS);
                        break;
                }
                dismiss();
                break;
            default:
                break;
        }

        mainActivity.recreate();
    }
}
