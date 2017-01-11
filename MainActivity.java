package com.example.hcd_fresher048.appdemo;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ListNewsFragment listNewsFragment;
    private ListNewsOfflineFragment listNewsOfflineFragment;
    private MainViewPagerAdapter mainViewPagerAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listNewsFragment = new ListNewsFragment();
        listNewsOfflineFragment = new ListNewsOfflineFragment();

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        initTab();
    }

    private void initTab() {
        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(listNewsFragment, "News");
        mainViewPagerAdapter.addFragment(listNewsOfflineFragment, "Saved");

        mViewPager.setAdapter(mainViewPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_news_fragment_action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_view:
                ListNewsFragmentDisplayInGridView listNewsFragment = new ListNewsFragmentDisplayInGridView();
                listNewsFragment.getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_list_news, listNewsFragment)
                        .commit();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
