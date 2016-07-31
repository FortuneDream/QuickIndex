package com.example.q.quickindextest;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private QuickIndexView qiView;
    private ListView lvNames;
    private TextView tvIndex;
    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        init();
    }

    private void init() {
        final ArrayList<Person> list=new ArrayList<>();
        fillAndSortDate(list);
        lvNames.setAdapter(new NameAdapter(MainActivity.this,list));
        qiView.setListener(new QuickIndexView.onLetterUpdateListener() {
            @Override
            public void onLetterUpdate(String letter) {
//                MyToast.showToast(getApplicationContext(),letter);
                showLetter(letter);
                //根据字母定位ListView,找到集合中第一个以letter为拼音的对象，得到索引
                for (int i=0;i<list.size();i++){
                    Person person=list.get(i);
                    String l=person.getPinyin().charAt(0)+"";
                    if (TextUtils.equals(letter,l)){
                        lvNames.setSelection(i);
                    }
                }

            }
        });
    }

    private void showLetter(String letter) {
        tvIndex.setVisibility(View.VISIBLE);
        tvIndex.setText(letter);
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvIndex.setVisibility(View.GONE);
            }
        },1000);

    }

    private void fillAndSortDate(ArrayList<Person> list) {
        for (int i=0;i<Cheeses.names.length;i++){
            String name=Cheeses.names[i];
            list.add(new Person(name));
        }
        Collections.sort(list);
    }

    private void findView() {
        qiView = (QuickIndexView) findViewById(R.id.qi_view);
        lvNames = (ListView) findViewById(R.id.lv_names);
        tvIndex = (TextView) findViewById(R.id.tv_index);
    }
}
