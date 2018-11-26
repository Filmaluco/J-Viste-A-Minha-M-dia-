package pt.isec.gps1819g11.javisteaminhamedia.Activities.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

import pt.isec.gps1819g11.javisteaminhamedia.Activities.CourseAdapter;
import pt.isec.gps1819g11.javisteaminhamedia.DialogInputs.UpdateGradesDialog;
import pt.isec.gps1819g11.javisteaminhamedia.Enumerations.Tag;
import pt.isec.gps1819g11.javisteaminhamedia.MainActivity;
import pt.isec.gps1819g11.javisteaminhamedia.Models.Course;
import pt.isec.gps1819g11.javisteaminhamedia.Models.Student;
import pt.isec.gps1819g11.javisteaminhamedia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondYearFragment extends Fragment {
    public MainActivity mainActivity;
    ListView lvSem1,lvSem2;
    ArrayList<Course> dataModels;
    Student student;
    View view;
    public static CourseAdapter adapter;


    public SecondYearFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainActivity = (MainActivity)getActivity();
        student = mainActivity.student;

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_year, container, false);
        setupListViews();

        return view;
    }

    void setupListViews(){
        try {
            lvSem1 = (ListView) view.findViewById(R.id.listView1SEM);
            lvSem2 = (ListView) view.findViewById(R.id.listView2SEM);

            dataModels = new ArrayList<>();
            dataModels = student.getList(2, 1);

            adapter = new CourseAdapter(dataModels, getContext());
            lvSem1.setAdapter(adapter);

            dataModels = student.getList(2, 2);

            adapter = new CourseAdapter(dataModels, getContext());
            lvSem2.setAdapter(adapter);

        }catch (Exception e){
            Log.i("Exceção","setupListViews exceção " + e);
        }


        lvSem1.setOnItemClickListener(new AdapterView.OnItemClickListener() { //Ao clicar nos items da primeira listview
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = lvSem1.getItemAtPosition(position);
                Course c = (Course)o;
                String gradeName = c.getName();
                Log.i("Teste","->" + gradeName);

                FragmentManager fm = getFragmentManager();
                UpdateGradesDialog updateGradesDialog = new UpdateGradesDialog(student,gradeName);
                updateGradesDialog.show(fm, "Update Grade");
            }
        });

        lvSem2.setOnItemClickListener(new AdapterView.OnItemClickListener() { //Ao clicar nos items da primeira listview
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = lvSem2.getItemAtPosition(position);
                String gradeName = (String)o;
                Log.i("Teste","->" + gradeName);

                FragmentManager fm = getFragmentManager();
                UpdateGradesDialog updateGradesDialog = new UpdateGradesDialog(student,gradeName);
                updateGradesDialog.show(fm, "Update Grade");
            }
        });
    }
}