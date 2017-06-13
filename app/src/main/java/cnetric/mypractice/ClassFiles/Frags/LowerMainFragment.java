package cnetric.mypractice.ClassFiles.Frags;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cnetric.mypractice.R;
import cnetric.mypractice.Util.NonSwipeableViewPager;


/**
 * A simple {@link Fragment} subclass.
 */
public class LowerMainFragment extends Fragment {

    public static String fragments[];
    private TabLayout tabLayout;
    private NonSwipeableViewPager viewPager;
    private ViewPagerAdapter mPagerAdapter;

    public LowerMainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lower_main, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        viewPager = (NonSwipeableViewPager) view.findViewById(R.id.viewpager);
        //Enable/Disable Swipe
        viewPager.setPagingEnabled(true);

        fragments = new String[]{"Videos", "Photos"};
        mPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            View customView = mPagerAdapter.getCustomView(getActivity(), i);
            tabLayout.getTabAt(i).setCustomView(customView);
        }
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    public static class ViewPagerAdapter extends SmartFragmentStatePagerAdapter {
        private int[] drawables = new int[]{R.drawable.videos_selector, R.drawable.gallery_selector};

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public View getCustomView(Context context, int pos) {
            View mView = LayoutInflater.from(context).inflate(R.layout.custom_tab_view, null);
            ImageView imageView = (ImageView) mView.findViewById(R.id.imageView);
            TextView mTextView = (TextView) mView.findViewById(R.id.textView);
            mTextView.setGravity(Gravity.CENTER);
            mTextView.setText(getPageTitle(pos));
            imageView.setImageResource(drawables[pos]);
            return mView;
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
            switch (position) {
                case 0:
                    VideosFragment videosFragment = new VideosFragment();
                    return videosFragment;
                case 1:
                    return new BaseFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}
