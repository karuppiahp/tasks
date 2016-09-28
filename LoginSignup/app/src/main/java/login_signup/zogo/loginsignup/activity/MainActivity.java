package login_signup.zogo.loginsignup.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import login_signup.zogo.loginsignup.R;
import login_signup.zogo.loginsignup.adapter.ViewPagerMainAdapter;
import login_signup.zogo.loginsignup.fragment.LoginInFragment;
import login_signup.zogo.loginsignup.fragment.SignUpFragment;

/**
 * Created by karuppiah on 9/27/2016.
 */
public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //viewpager initialize
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        //tablayout initialize
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerMainAdapter adapter = new ViewPagerMainAdapter(getSupportFragmentManager());
        adapter.addFragment(new LoginInFragment(), "LOG IN");
        adapter.addFragment(new SignUpFragment(), "SIGN UP");
        viewPager.setAdapter(adapter);
    }
}
