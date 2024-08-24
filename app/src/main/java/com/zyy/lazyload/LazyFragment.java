package com.zyy.lazyload;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 搭建懒加载基础架构,后续可以基于架构，基于需求修改
 */
public abstract class LazyFragment extends Fragment {
    private static final String TAG = "LazyFragment";
    private View rootView = null;               //Fragment根布局
    private boolean isViewCreated = false;      // 当前页面是否创建
    private boolean isCurrentVisible = false;   // 当前Fragment是否可见

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutRes(), container, false);
        }
        initView(rootView);
        isViewCreated = true;
        if (getUserVisibleHint()) {  // 因为被拦截，所以重新添加可见事件的分发
            setUserVisibleHint(true);
        }
        return rootView;
    }

    protected abstract void initView(View rootView);

    protected abstract int getLayoutRes();


    // 判断页面是否可见
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint: " + isVisibleToUser);
        if (isViewCreated) {
            if (!isCurrentVisible && isVisibleToUser) {
                // 不可见变成可见
                dispatchUserVisibleHint(true);
            } else if (isCurrentVisible && !isVisibleToUser) {
                // 可见变成不可见
                dispatchUserVisibleHint(false);
            }
        }
    }

    // 分发可见事件的函数
    private void dispatchUserVisibleHint(boolean isVisible) {
        isCurrentVisible = isVisible;
        if (isVisible) {
            onFragmentLoad();
        } else {
            onFragmentLoadStop();
        }
    }

    // 停止加载数据
    protected void onFragmentLoadStop() {
        Log.d(TAG, "onFragmentLoadStop: ");
    }

    // 加载数据
    protected void onFragmentLoad() {
        Log.d(TAG, "onFragmentLoad: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        if (!isCurrentVisible && getUserVisibleHint()) {
            dispatchUserVisibleHint(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
        if (isCurrentVisible && getUserVisibleHint()) {
            dispatchUserVisibleHint(false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
