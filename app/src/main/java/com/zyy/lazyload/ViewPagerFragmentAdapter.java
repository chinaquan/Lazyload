package com.zyy.lazyload;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {

    private List<Fragment> fragments;

    public ViewPagerFragmentAdapter(@NonNull FragmentManager fragmentManager, List<Fragment> fragments, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments == null ? 0 : fragments.size();
    }
}
