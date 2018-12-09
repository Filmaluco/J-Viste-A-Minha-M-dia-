package pt.isec.gps1819g11.javisteaminhamedia.Activities.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import pt.isec.gps1819g11.javisteaminhamedia.Activities.CourseAdapter;
import pt.isec.gps1819g11.javisteaminhamedia.MainActivity;
import pt.isec.gps1819g11.javisteaminhamedia.Models.Course;
import pt.isec.gps1819g11.javisteaminhamedia.Models.Student;
import pt.isec.gps1819g11.javisteaminhamedia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PredictionFragment extends Fragment {

    TextView currentAverage;
    TextView intendedAverage;
    TextView bolognaAverage;
    TextView predictionGradesInfo;
    ListView gradesPrediction;
    Student student;
    MainActivity mainActivity;
    ArrayList<Course> dataModels;
    public static CourseAdapter adapter;

    public PredictionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prediction_screen, container, false);
        currentAverage = (TextView) view.findViewById(R.id.current_average);
        intendedAverage = (TextView) view.findViewById(R.id.intended_average);
        bolognaAverage = (TextView) view.findViewById(R.id.bologna_average);
        predictionGradesInfo = (TextView) view.findViewById(R.id.prediction_grades_info);
        gradesPrediction = (ListView) view.findViewById(R.id.grades_prediction);
        mainActivity = (MainActivity) getActivity();
        student = mainActivity.student;

        ImageView sync = view.findViewById(R.id.btnUpdate_bologna_average);
        sync.setClickable(true);
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.convertToBologna();
                ((TextView)mainActivity.findViewById(R.id.bologna_average)).setText(String.valueOf(student.getBologna()));
            }
        });
        setupPredictedGrades();

        currentAverage.setText(Float.toString(student.getAverage()));
        intendedAverage.setText(Float.toString(student.getIntendedAverage()));
        bolognaAverage.setText(Character.toString(student.getBologna()));

        return view;
    }

    private void setupPredictedGrades() {
        dataModels = new ArrayList<>();

        dataModels = student.calculatePrediction();

        for(int i = 0 ; i< dataModels.size() ;i++){
            if(dataModels.get(i).getGrade()>20 || dataModels.get(i).getGrade()<9.5){
                dataModels.remove(i);
            }
        }

        if (dataModels.isEmpty()){
            predictionGradesInfo.setTextColor(getResources().getColor(R.color.colorPrimary));
            predictionGradesInfo.setTextSize(20);
            predictionGradesInfo.setText(getResources().getText(R.string.prediciton_grades_error));
        }else{
            predictionGradesInfo.setTextColor(getResources().getColor(R.color.colorAccent));
            predictionGradesInfo.setTextSize(14);
            predictionGradesInfo.setText(getResources().getText(R.string.prediciton_grades_info));
        }

        adapter = new CourseAdapter(dataModels, getContext());
        adapter.setLayout(R.layout.listview_prediction_row_item);

        gradesPrediction.setAdapter(adapter);

    }

}
