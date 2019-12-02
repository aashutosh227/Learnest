package com.example.learnest;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.support.v4.view.GravityCompat;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import java.util.ArrayList;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

public class Home2 extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction transaction = fragmentManager.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_home2);

//        frameLayout = findViewById(R.id.fragment_container);
//
//        transaction.setCustomAnimations(R.anim.enter_from_righrt, R.anim.exit_to_right, R.anim.enter_from_righrt, R.anim.exit_to_right);

        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(false);

        drawer = findViewById(R.id.drawer_layout2);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(navListener1);

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
//                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new fragment_home()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        //Load the inital home fragment in the framelayout container
        Fragment homeFragment = new fragment_home();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,homeFragment).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
            Fragment selectedFragment = null;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bottom_nav_home:
                        selectedFragment = new fragment_home();
                        break;
                    case R.id.bottom_nav_search:
                        selectedFragment = new fragment_search();
                        break;
                    case R.id.bottom_nav_favs:
                        selectedFragment = new fragment_favs();
                        break;
                    case R.id.bottom_nav_profile:
                        selectedFragment = new fragment_profile();
                        break;
                }
                //transaction.addToBackStack(null);
//                //transaction.add(R.id.fragment_container, selectedFragment, "Home_Fragment");
//                transaction.replace(R.id.fragment_container,selectedFragment).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
                    return true;
            }
    };

    private NavigationView.OnNavigationItemSelectedListener navListener1 =
            new NavigationView.OnNavigationItemSelectedListener() {

                Fragment selectedFragment = null;
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new fragment_home();
                            break;
                        case R.id.nav_logout:
                            Toast.makeText(getParent(),"Logged Out",Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new fragment_profile();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
            };

}
