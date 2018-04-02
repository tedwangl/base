package com.wl.mylibrary.util.network;

import android.view.ViewGroup;

import com.wl.mylibrary.widget.TipInfoLayout;

/**
 * 网络请求参数配置
 * Created by sks on 2016/4/12.
 */
public class RequestConfig {

    //是否隐藏mainBody显示加载进度, true隐藏,false不隐藏
    private boolean isCover;

    //是否显示加载进度
    private boolean isLoading;

       //提示信息控件
    private TipInfoLayout tipInfoLayout;

    //页面主体
    private ViewGroup mainBody;

    {
        this.isCover = true;
        this.isLoading = true;
    }

    public boolean isCover() {
        return isCover;
    }

    public RequestConfig setIsCover(boolean isCover) {
        this.isCover = isCover;
        return this;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public RequestConfig setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
        return this;
    }

    public TipInfoLayout getTipInfoLayout() {
        return tipInfoLayout;
    }

    public RequestConfig setTipInfoLayout(TipInfoLayout tipInfoLayout) {
        this.tipInfoLayout = tipInfoLayout;
        return this;
    }

    public ViewGroup getMainBody() {
        return mainBody;
    }

    public RequestConfig setMainBody(ViewGroup mainBody) {
        this.mainBody = mainBody;
        return this;
    }

}
