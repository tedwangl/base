package com.wl.mylibrary.util.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Adapter基类
 * @param <E>
 * @author ymx
 */
public abstract class CusAdapter<E> extends BaseAdapter {

    protected Context context;
    protected List<E> list;
    private LayoutInflater inflater;
    //Item布局资源
    private int itemLayoutRes;

    public CusAdapter(Context context, List<E> list,int itemLayoutRes) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
        this.itemLayoutRes = itemLayoutRes;
    }

    public void setData(List<E> list){
        if(this.list!=null && !this.list.isEmpty()){
            this.list.clear();
            this.list = null;
        }
        this.list = list;
        notifyDataSetChanged();
    }

    public void addAll(List<E> list){
        this.list.addAll(list);
    }

    public void addAll(int location,List<E> data){
        this.list.addAll(location,data);
        notifyDataSetChanged();
    }

    public void removeAll(List<E> list){
        this.list.removeAll(list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public E getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = inflater.inflate(itemLayoutRes,null);
        }
        return getCustomView(position, convertView);
    }

    public abstract View getCustomView(int position, View itemView);

}