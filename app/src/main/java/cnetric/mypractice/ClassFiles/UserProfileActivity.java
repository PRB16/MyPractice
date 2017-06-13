package cnetric.mypractice.ClassFiles;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cnetric.mypractice.ClassFiles.Frags.FirstFragment;
import cnetric.mypractice.ClassFiles.Frags.SecondFragment;
import cnetric.mypractice.ClassFiles.Frags.ThirdFragment;
import cnetric.mypractice.R;

/**
 * Created by cnetric on 5/4/2017.
 */

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title_tv, follow_tv, sendMessage_tv, description_tv, address_tv, interest_tv;



    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Fragment fragment = null;
    private ViewPagerAdapter adapter;
//    private ScrollView scrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        idInit();
        clickListener();
    }

    // Views Id Initialize Method
    private void idInit() {
//        TextView Id
        follow_tv = (TextView) findViewById(R.id.follow_tv);
        sendMessage_tv = (TextView) findViewById(R.id.sendMessage_tv);
        description_tv = (TextView) findViewById(R.id.description_tv);
        description_tv.setText("Lorem ipsum is simply dummy text of the printing and typetesting industry. Lorem ipsum has been the industry's standard dummy text ever since the.");
        address_tv = (TextView) findViewById(R.id.address_tv);
        address_tv.setText("78 Speedwell Ave, Morristown Headquarters Plaza, Morristown, USA");
        interest_tv = (TextView) findViewById(R.id.interest_tv);

//        ViewPager SetUp
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstFragment(), "All Post");
        adapter.addFragment(new SecondFragment(), "Been There");
        adapter.addFragment(new FirstFragment(), "Tagged Post");
        adapter.addFragment(new SecondFragment(), "Gallery");
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    //    Click Event Handle Method
    private void clickListener() {
        follow_tv.setOnClickListener(this);
        sendMessage_tv.setOnClickListener(this);
        follow_tv.setTextColor(getResources().getColor(R.color.white));
        follow_tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_green_button_effect));
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.follow_tv:
                follow_tv.setTextColor(getResources().getColor(R.color.white));
                follow_tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_green_button_effect));

                sendMessage_tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                sendMessage_tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_green));
                break;
            case R.id.sendMessage_tv:
                sendMessage_tv.setTextColor(getResources().getColor(R.color.white));
                sendMessage_tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_green_button_effect));

                follow_tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                follow_tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_green));
                break;
        }
    }


}