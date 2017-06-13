package cnetric.mypractice.ClassFiles;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import cnetric.mypractice.R;

public class NavigationActivity extends AppCompatActivity {
    public DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    ImageView hamburgerIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        hamburgerIcon=(ImageView)findViewById(R.id.hamburger);
        hamburgerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        createSlideMenu();
    }
    private void createSlideMenu() {


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = null;

                switch (menuItem.getItemId()) {
                    case R.id.nBla:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nOffline:
                        fragment = new HomeFragment();
                        break;

                    default:
                        break;
                }
                if (fragment != null) {
                    fragmentManager.beginTransaction().replace(R.id.frame_container, fragment)
                            .commit();
                }

                return true;

            }

        });


        mDrawerLayout = (DrawerLayout) findViewById(R.id.SlidingPanel);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close) {


            @Override
            public void onDrawerClosed(View drawerView) {
                //invalidateOptionsMenu();
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                //invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);



            }
        };



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 19Nov2015:Commented:Navigation drawer:start
       /* if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);*/
        // 19Nov2015:Commented:Navigation drawer:end

        int id = item.getItemId();

        switch (id) {

            case android.R.id.home:

                if (mDrawerLayout.isDrawerOpen(GravityCompat.START) == true) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_settings).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
