package pt.isec.gps1819g11.javisteaminhamedia.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import pt.isec.gps1819g11.javisteaminhamedia.Models.Student;
import pt.isec.gps1819g11.javisteaminhamedia.R;


public class GradesActivity extends Activity {
    ListView lvSem1,lvSem2;
    ArrayAdapter<String> adapter;

    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras(); //Getting the object serialized
        Student student = (Student)bundle.getSerializable("student"); //Diserializing the student object

        setupListViews(student);
    }

    void setupListViews(Student student){
        lvSem1 = (ListView) findViewById(R.id.listView1SEM);
        lvSem2 = (ListView) findViewById(R.id.listView2SEM);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1); //

        lvSem1.setAdapter(adapter);

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
            adapter.clear();
            lvSem2.setAdapter(adapter);
            adapter.addAll(cursos2);
        //}
        lvSem1.setOnItemClickListener(new AdapterView.OnItemClickListener() { //Ao clicar nos items da primeira listview
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = lvSem1.getItemAtPosition(position);
                String gradeName = (String)o;
                Log.i("Teste","->" + gradeName);
                int res;


                    updateGradeDlg(gradeName);


            }
        });

        lvSem2.setOnItemClickListener(new AdapterView.OnItemClickListener() { //Ao clicar nos items da primeira listview
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = lvSem1.getItemAtPosition(position);
                String gradeName = (String)o;
                Log.i("Teste","->" + gradeName);

                updateGradeDlg(gradeName);


            }
        });
    }
   void updateGradeDlg(String gradeName){ //Dialog to update grade
        UpdateGradesDialog updateGradesDialog = new UpdateGradesDialog(GradesActivity.this, student,gradeName);
        updateGradesDialog.show();

    }
}
