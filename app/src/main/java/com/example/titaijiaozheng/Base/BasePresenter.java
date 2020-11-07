package com.example.titaijiaozheng.Base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Presenter基类，（优化mvp模式，防止内存泄漏）
 * 教程：https://blog.csdn.net/Lcy_for_Android/article/details/89138900
 */
public abstract class BasePresenter<T> {

    /**
     * 声明一个view借口类型的弱作用
     */
    protected Reference<T> mViewRef;

    /**
     * 让弱作用与view关联
     *
     * @param view
     */
    public void attachView(T view) {
        mViewRef = new WeakReference<>(view);
    }

    /**
     * 获得view的对象
     *
     * @return
     */
    protected T getView() {
        return mViewRef.get();
    }

    /**
     * 判断是否建立了关联
     *
     * @return
     */
    public boolean isViewAttached() {
        return mViewRef != null && getView() != null;
    }

    /**
     * 解除绑定
     */
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
