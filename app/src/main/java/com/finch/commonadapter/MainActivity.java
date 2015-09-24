package com.finch.commonadapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ListView mListView;

    private MyCommonAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) this.findViewById(R.id.listView);
        adapter = new MyCommonAdapter<String>(this, R.layout.list_item_layout, getData()) {
            @Override
            protected void fillItemData(CommonViewHolder viewHolder, int position, String item) {
                viewHolder.setTextForTextView(R.id.tv_title, item);
            }
        };
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, GridViewActivity.class));
            }
        });
    }

    public List<String> getData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            String str = "jack" + i;
            list.add(str);
        }
        return list;
    }
}

