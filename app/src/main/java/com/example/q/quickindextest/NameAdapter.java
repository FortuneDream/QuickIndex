package com.example.q.quickindextest;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by YQ on 2016/7/30.
 */
public class NameAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Person> list;
    public NameAdapter(Context context, ArrayList<Person> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View root;
        ViewHolder viewHolder;
        if (view==null){
            root=View.inflate(context,R.layout.item,null);
            viewHolder=new ViewHolder();
            viewHolder.tvIndex= (TextView) root.findViewById(R.id.tv_index);
            viewHolder.tvName= (TextView) root.findViewById(R.id.tv_name);
            root.setTag(viewHolder);
        }else {
            root=view;
            viewHolder= (ViewHolder) root.getTag();
        }
        String str=null;
        Person p=list.get(i);
        String letter=p.getPinyin().charAt(0)+"";
        //根据上一个首字母决定是否显示字母
        if (i==0){
            str=letter;
        }else {
            //上一个人的拼音首字母
            String preLetter=list.get(i-1).getPinyin().charAt(0)+"";
            if (!TextUtils.equals(preLetter,letter)){
                str=letter;
            }
        }
        //通过判断str的判空来决定View的显示
        viewHolder.tvIndex.setVisibility(str==null?View.GONE:View.VISIBLE);
        viewHolder.tvName.setText(p.getName());
        viewHolder.tvIndex.setText(letter);
        return root;
    }

    static  class ViewHolder{
        TextView tvIndex;
        TextView tvName;
    }


}
