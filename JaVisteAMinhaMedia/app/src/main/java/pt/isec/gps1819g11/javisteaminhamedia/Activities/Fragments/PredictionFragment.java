package pt.isec.gps1819g11.javisteaminhamedia.Activities.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        gradesPrediction = (ListView) view.findViewById(R.id.grades_prediction);
        mainActivity = (MainActivity) getActivity();
        student = mainActivity.student;

        setupPredictedGrades();

        currentAverage.setText(Float.toString(student.getAverage()));
        intendedAverage.setText(Float.toString(student.getIntendedAverage()));
        bolognaAverage.setText(Character.toString(student.getBologna()));

        return view;
    }

    private void setupPredictedGrades() {
        dataModels = new ArrayList<>();
        dataModels = student.calculatePrediction();

        adapter = new CourseAdapter(dataModels, getContext());
        adapter.setLayout(R.layout.listview_prediction_row_item);

        gradesPrediction.setAdapter(adapter);

    }

}
