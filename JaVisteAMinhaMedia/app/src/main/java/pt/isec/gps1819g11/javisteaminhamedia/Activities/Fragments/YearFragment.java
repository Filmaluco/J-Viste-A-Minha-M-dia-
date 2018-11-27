package pt.isec.gps1819g11.javisteaminhamedia.Activities.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
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
public class YearFragment extends Fragment {
    public MainActivity mainActivity;
    ListView lvSem1,lvSem2;
    ArrayList<Course> dataModels;
    Student student;
    View view;
    int year;
    public static CourseAdapter adapter;

    public YearFragment(){
        //Required empty constructor
    }

    @SuppressLint("ValidFragment")
    public YearFragment(int year) {
        this.year = year;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
            dataModels = student.getList(year, 1);

            adapter = new CourseAdapter(dataModels, getContext());
            adapter.setLayout(R.layout.listview_grades_row_item);

            lvSem1.setAdapter(adapter);

            dataModels = student.getList(year, 2);

            adapter = new CourseAdapter(dataModels, getContext());
            adapter.setLayout(R.layout.listview_grades_row_item);

            lvSem2.setAdapter(adapter);

            setListViewHeightBasedOnChildren(lvSem1);
            setListViewHeightBasedOnChildren(lvSem2);
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
                Course c = (Course)o;
                String gradeName = c.getName();
                Log.i("Teste","->" + gradeName);

                FragmentManager fm = getFragmentManager();
                UpdateGradesDialog updateGradesDialog = new UpdateGradesDialog(student,gradeName);
                updateGradesDialog.show(fm, "Update Grade");
            }
        });
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }




}
