package com.finch.commonadapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends Activity {

    private GridView gridView;

    private MyCommonAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        gridView = (GridView) this.findViewById(R.id.gridView);
        adapter = new MyCommonAdapter<String>(this, R.layout.grid_item_layout, getData()) {
            @Override
            protected void fillItemData(CommonViewHolder viewHolder, int position, String item) {
                viewHolder.setTextForTextView(R.id.textView, item);
            }
        };
        gridView.setAdapter(adapter);
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
