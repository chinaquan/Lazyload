package com.zyy.lazyload;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.zyy.lazyload.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mVB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVB = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mVB.getRoot());
        initView();
    }

    private void initView() {
        initVP();
        initNavigation();
    }

    private void initVP() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecommendFragment());
        fragments.add(new FindFragment());
        fragments.add(new RoamingFragment());
        fragments.add(new DynamicFragment());
        fragments.add(new MyFragment());
        mVB.vp.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragments, getLifecycle()));
        mVB.vp.setUserInputEnabled(false);
        mVB.vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mVB.nav.getMenu().getItem(position).setChecked(true);
            }
        });
    }

    private void initNavigation() {
        mVB.nav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_recommend) {
                mVB.vp.setCurrentItem(0);
                return true;
            } else if (itemId == R.id.navigation_find) {
                mVB.vp.setCurrentItem(1);
                return true;
            } else if (itemId == R.id.navigation_roaming) {
                mVB.vp.setCurrentItem(2);
                return true;
            } else if (itemId == R.id.navigation_dynamic) {
                mVB.vp.setCurrentItem(3);
                return true;
            } else if (itemId == R.id.navigation_my) {
                mVB.vp.setCurrentItem(4);
                return true;
            }
            return false;
        });
    }
}