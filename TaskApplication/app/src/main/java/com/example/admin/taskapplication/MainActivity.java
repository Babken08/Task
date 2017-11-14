package com.example.admin.taskapplication;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.CallBack {

    private List<Model> list;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.setExpanded(false, true);
        initImages();

        listView = (ListView) findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter(list, this);
        listView.setAdapter(adapter);
        ViewCompat.setNestedScrollingEnabled(listView,true);

    }

    private void initImages() {
        list = new ArrayList<>();
        int[] resours = {R.drawable.girl, R.drawable.girl1, R.drawable.grl,
                R.drawable.nkar0, R.drawable.nkar1, R.drawable.nkar2,
                R.drawable.nkar4, R.drawable.nkar5, R.drawable.nkar7,
                R.drawable.nkar8, R.drawable.nkar9, R.drawable.nkar11,
                R.drawable.nkar12, R.drawable.nkar13, R.drawable.nkar14};

        for (int i = 0; i < 12; i++) {
            Model m = new Model();
//            m.setImgOk(R.drawable.ic_check_24dp);
            m.setImg1(resours[i]);

            if ((i + 1) < resours.length - 1) {
                m.setImg2(resours[i + 1]);
            }
            if ((i + 2) < resours.length - 1) {
                m.setImg3(resours[i + 2]);
            }
            list.add(m);
        }
    }

    @Override
    public void getElementFocus(int x) {

        listView.requestFocus();
        listView.setSelection(x);

    }
}
