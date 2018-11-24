package pt.isec.gps1819g11.javisteaminhamedia.Activities.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import pt.isec.gps1819g11.javisteaminhamedia.Activities.GradesActivity;
import pt.isec.gps1819g11.javisteaminhamedia.Activities.UpdateGradesDialog;
import pt.isec.gps1819g11.javisteaminhamedia.MainActivity;
import pt.isec.gps1819g11.javisteaminhamedia.Models.Student;
import pt.isec.gps1819g11.javisteaminhamedia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstYearFragment extends Fragment {
    ListView lvSem1,lvSem2;
    ArrayAdapter<String> adapter;
    Student student;
    View view;
    public FirstYearFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



       view = inflater.inflate(R.layout.fragment_first_year, container, false);
        setupListViews();
        return view;


    }
    void setupListViews(){

        lvSem1 = (ListView) view.findViewById(R.id.listView1SEM);
       lvSem2 = (ListView ) view.findViewById(R.id.listView2SEM);

        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1); //



        /*Listas para efeitos de teste*/
        //{
        ArrayList<String> cursos = new ArrayList<>();
        cursos.add("AM1");
        cursos.add("SD");
        cursos.add("AL");
        cursos.add("IP");
        cursos.add("TWEB");
        cursos.add("GESTAO");

        ArrayList<String> cursos2 = new ArrayList<>();
        cursos2.add("AM2");
        cursos2.add("P");
        cursos2.add("E");
        cursos2.add("ME");
        cursos2.add("TAC");
        cursos2.add("FCG");


        adapter.addAll(cursos);
        lvSem1.setAdapter(adapter);
        adapter.clear();
        adapter.addAll(cursos2);
        lvSem2.setAdapter(adapter);
        //}
        lvSem1.setOnItemClickListener(new AdapterView.OnItemClickListener() { //Ao clicar nos items da primeira listview
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = lvSem1.getItemAtPosition(position);
                String gradeName = (String)o;
                Log.i("Teste","->" + gradeName);
                Log.i("Teste","Missing implemenation of dialogbox");
               // updateGradeDlg(gradeName);


            }
        });

        lvSem2.setOnItemClickListener(new AdapterView.OnItemClickListener() { //Ao clicar nos items da primeira listview
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = lvSem1.getItemAtPosition(position);
                String gradeName = (String)o;
                Log.i("Teste","->" + gradeName);
                Log.i("Teste","Missing implemenation of dialogbox");
               // updateGradeDlg(gradeName);


            }
        });
    }
   /* void updateGradeDlg(String gradeName){ //Dialog to update grade
        UpdateGradesDialog updateGradesDialog = new UpdateGradesDialog(MainActivity.this, student,"gradeName);
        updateGradesDialog.show();

    }*/
}
