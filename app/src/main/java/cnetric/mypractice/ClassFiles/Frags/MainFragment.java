package cnetric.mypractice.ClassFiles.Frags;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cnetric.mypractice.R;
import cnetric.mypractice.Util.NonSwipeableViewPager;

public class MainFragment extends Fragment {
    public static String fragments[] = new String[]{"", "", ""};
    NonSwipeableViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerAdapter mPagerAdapter;
    FrameLayout frameLayout;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        viewPager = (NonSwipeableViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        frameLayout = (FrameLayout) view.findViewById(R.id.frame_layout);

        //Enable/Disable Swipe
        viewPager.setPagingEnabled(true);

        mPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //Fragment Transaction
        LowerMainFragment lowerMainFragment = new LowerMainFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.frame_layout, lowerMainFragment).commit();

        return view;
    }

    public static class ViewPagerAdapter extends FragmentStatePagerAdapter {
        // Three simple fragments
        BaseFragment fragA;
        BaseFragment fragB;
        BaseFragment fragC;
        private List<Map<String, Object>> maps = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments[position];
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            switch (position) {
                case 0:
                    fragA = new BaseFragment();
                    fragA.setArguments(bundle);
                    return fragA;
                case 1:
                    fragB = new BaseFragment();
                    fragB.setArguments(bundle);
                    return fragB;
                case 2:
                    fragC = new BaseFragment();
                    fragC.setArguments(bundle);
                    return fragC;
            }
            return null;
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }
}
