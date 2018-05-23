package com.messageimposible.messageimpossible;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

public class ActivityTabs extends AppCompatActivity {

    private ViewPager viewPager;
    private PageAdapter pagerAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tab_viewer);

        pagerAdapter = new PageAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }


    public class PageAdapter extends FragmentPagerAdapter {

        private String[] tabTitles = new String[]{"CONTACTS", "CHATS", "INVITES"};

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position){

                case 0:
                    fragment = new FragmentContacts();
                    break;
                case 1:
                    fragment = new FragmentChats();
                    break;
                case 2:
                    fragment = new FragmentInvites();
                    break;

            }

            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int res_id = item.getItemId();

        if(res_id == R.id.action_search){

            Toast.makeText(getApplicationContext(), "search", Toast.LENGTH_LONG).show();

        }else if(res_id == R.id.action_addFriend){

            Toast.makeText(getApplicationContext(), "add friend", Toast.LENGTH_LONG).show();

        }else if (res_id == R.id.action_settings){

            Toast.makeText(getApplicationContext(), "settings", Toast.LENGTH_LONG).show();

        }

        return true;
    }


}

