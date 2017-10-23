package grayteam.paizley_customerapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class MenuActivity extends BaseActivity {

    private FragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    Bundle extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
//            private final Fragment[] fragments = new Fragment[]{
//                new Fragment(),
//                new Fragment(),
//            };
//
//            private final String[] fragmentNames = new String[]{
//                    getString(R.string.menu_breakfast),
//                    getString(R.string.menu_lunch),
//                    getString(R.string.menu_dinner),
//            };
//
//            @Override
//            public Fragment getItem(int position) {
//                return null;
//            }
//
//            @Override
//            public int getCount() {
//                return 0;
//            }
//        };
    }

}
