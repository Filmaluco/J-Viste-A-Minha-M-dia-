package pt.isec.gps1819g11.javisteaminhamedia.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pt.isec.gps1819g11.javisteaminhamedia.Models.Course;
import pt.isec.gps1819g11.javisteaminhamedia.R;

public class CourseAdapter extends ArrayAdapter<Course> implements View.OnClickListener{

    private ArrayList<Course> dataSet;
    Context mContext;
    TextView textView;
    int layoutID;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView textCircle;
    }

    public CourseAdapter(ArrayList<Course> data, Context context) {
        super(context, R.layout.listview_grades_row_item, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Course dataModel=(Course) object;
      
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Course dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layoutID, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.textCircle = (TextView) convertView.findViewById(R.id.textCircle);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

            result=convertView;
        }


        lastPosition = position;

        viewHolder.txtName.setText(dataModel.getName());
        if(dataModel.getGrade() >= 9.5 && dataModel.getGrade() <= 20)
          viewHolder.textCircle.setText(Float.toString(dataModel.getGrade()));
        else{
            viewHolder.textCircle.setText(" ");
        }

        // Return the completed view to render on screen
        return convertView;
    }

    public void setLayout(int layoutID){
        this.layoutID = layoutID;
    }
}