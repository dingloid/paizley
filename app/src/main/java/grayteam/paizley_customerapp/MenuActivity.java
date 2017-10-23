package grayteam.paizley_customerapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import grayteam.paizley_customerapp.fragments.MenuItemListFragment;


public class MenuActivity extends BaseActivity {

    private FragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    DatabaseReference databaseReference;

    Bundle extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        final String section = "";

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(section);

        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] fragments = new Fragment[]{
                new Fragment(),
                new Fragment(),
            };

            private final String[] fragmentNames = new String[]{
                    getString(R.string.menu_breakfast),
                    getString(R.string.menu_lunch),
                    getString(R.string.menu_dinner),
            };

            @Override
            public Fragment getItem(int position) {
//                ((MenuItemListFragment) fragments[position]).setSectionName(section);
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return fragmentNames[position];
            }
        };

        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

}
