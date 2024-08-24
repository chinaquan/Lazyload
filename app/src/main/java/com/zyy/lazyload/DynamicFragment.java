package com.zyy.lazyload;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DynamicFragment extends LazyFragment {
    private TextView tvLoading;
    private ImageView ivBg;

    private static final String TAG = "DynamicFragment";
    private Handler mHandler = new Handler();
    @Override
    protected void initView(View rootView) {
        tvLoading = rootView.findViewById(R.id.tv_loading);
        ivBg = rootView.findViewById(R.id.iv_bg);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_dynamic;
    }

    @Override
    protected void onFragmentLoad() {
        super.onFragmentLoad();
        Log.d(TAG, "onFragmentLoad: 开始加载数据");
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onFragmentLoad: 加载数据完成");
                tvLoading.setVisibility(View.GONE);
                ivBg.setVisibility(View.VISIBLE);
            }
        }, 3000);
    }

    @Override
    protected void onFragmentLoadStop() {
        super.onFragmentLoadStop();
        Log.d(TAG, "onFragmentLoadStop: 停止加载数据");
    }
}