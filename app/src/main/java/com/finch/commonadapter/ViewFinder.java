package com.finch.commonadapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * @author liuzhang
 * @description
 * @date 2015年09月23日 15:46
 */
public final class ViewFinder {

    /**
     * 每项的View的sub view Map
     */
    private static SparseArray<WeakReference<View>> mViewMap = new SparseArray<WeakReference<View>>();
    /**
     * Root View的弱引用,
     * 不会阻止View对象被释放。如果该mRootView没有被外部引用，那么在重新设置了rootView之后老的rootview会被释放.
     */
    private static WeakReference<View> mRootView;

    public static void initContentView(View contentView) {
        if (contentView == null) {
            throw new RuntimeException("initContentView Method init failed,is null");
        }
        mRootView = new WeakReference<View>(contentView);
        mViewMap.clear();
    }

    public static void initContentView(Context context, int layoutId) {
        initContentView(context, null, layoutId);
    }

    public static void initContentView(Context context, ViewGroup parent, int layoutId) {
        if (context == null || layoutId <= 0) {
            throw new RuntimeException("ViewFinder init failed, mInflater == null || mContentView == null.");
        }
        View rootView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        initContentView(rootView);
    }

    public static View getContentView() {
        return mRootView.get();
    }

    public static <T extends View> T findViewById(int viewId) {
        View targetView = null;
        WeakReference<View> wf = mViewMap.get(viewId);
        if (wf != null) {
            targetView = wf.get();
        }

        if (targetView == null && mRootView != null && mRootView.get() != null) {
            targetView = mRootView.get().findViewById(viewId);
            mViewMap.put(viewId, new WeakReference<View>(targetView));
        }

        return targetView == null ? null : (T) targetView;
    }

    public static <T extends View> T findViewById(View rootView, int viewId) {
        View targetView = null;
        if (rootView != null) {
            targetView = rootView.findViewById(viewId);
        }
        return targetView == null ? null : (T) targetView;
    }

    public static void clear() {
        if (mViewMap != null) {
            mViewMap.clear();
            mViewMap = null;
        }

        if (mRootView != null) {
            mRootView.clear();
            mRootView = null;
        }
    }

}
