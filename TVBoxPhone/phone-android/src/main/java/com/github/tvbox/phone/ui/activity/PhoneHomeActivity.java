package com.github.tvbox.phone.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.github.tvbox.phone.R;
import com.github.tvbox.phone.ui.fragment.HomeFragment;
import com.github.tvbox.phone.ui.fragment.LiveFragment;
import com.github.tvbox.phone.ui.fragment.SearchFragment;
import com.github.tvbox.phone.ui.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PhoneHomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigation;

    private Fragment[] fragments = {
            new HomeFragment(),
            new LiveFragment(),
            new SearchFragment(),
            new UserFragment()
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_home_activity);

        viewPager = findViewById(R.id.viewPager);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                int[] menuIds = {R.id.nav_home, R.id.nav_live, R.id.nav_search, R.id.nav_user};
                bottomNavigation.setSelectedItemId(menuIds[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                viewPager.setCurrentItem(0);
                return true;
            } else if (itemId == R.id.nav_live) {
                viewPager.setCurrentItem(1);
                return true;
            } else if (itemId == R.id.nav_search) {
                viewPager.setCurrentItem(2);
                return true;
            } else if (itemId == R.id.nav_user) {
                viewPager.setCurrentItem(3);
                return true;
            }
            return false;
        });
    }
}