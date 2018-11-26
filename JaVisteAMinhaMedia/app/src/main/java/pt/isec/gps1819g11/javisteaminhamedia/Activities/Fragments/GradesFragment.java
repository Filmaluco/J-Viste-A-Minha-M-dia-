package pt.isec.gps1819g11.javisteaminhamedia.Activities.Fragments;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pt.isec.gps1819g11.javisteaminhamedia.Models.Student;
import pt.isec.gps1819g11.javisteaminhamedia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GradesFragment extends Fragment {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    public Student student;

    public GradesFragment() {
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
        View view = inflater.inflate(R.layout.fragment_grades, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.year_tabs);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragments(new YearFragment(), "1º Ano");
        adapter.addFragments(new YearFragment(), "2º Ano");
        adapter.addFragments(new YearFragment(), "3º Ano");
        viewPager.setAdapter(adapter);
    }

}
