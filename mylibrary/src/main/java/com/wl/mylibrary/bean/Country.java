package com.wl.mylibrary.bean;

/**
 * Created by pc on 2017/9/4.
 */
public class Country {

    private String name ;
    private String img_url;
    private String tips;

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
