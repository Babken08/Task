package com.example.admin.taskapplication;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Admin on 12.11.2017.
 */

public class MyAdapter extends BaseAdapter {
    private List<Model> list;
    private Context context;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private CallBack callBack;
    private ImageView okImage;
    private ImageView img1Ok;
    private  boolean b;
    private boolean b1;
    private ImageView img2Ok;
    private ImageView img3Ok;
    private View r2;


    public MyAdapter(List<Model> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Model getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.list_item, viewGroup, false);

        r2 = view.findViewById(R.id.relativ_img2);
        img1Ok =  view.findViewById(R.id.img1_ok);
        img2Ok = view.findViewById(R.id.img2_ok);
        img3Ok = view.findViewById(R.id.img3_ok);
        callBack = (CallBack) context;
        img1 = view.findViewById(R.id.imgeview1);
        img2 = view.findViewById(R.id.imgeview2);
        img3 = view.findViewById(R.id.imgeview3);
        img1.setImageResource(getItem(i).getImg1());
        img2.setImageResource(getItem(i).getImg2());
        img3.setImageResource(getItem(i).getImg3());


        if (getItem(i).getImg1() == 0 || getItem(i).getImg2() == 0) {


            r2.getLayoutParams().width = 500;
            r2.getLayoutParams().height = 500;
            r2.getLayoutParams().resolveLayoutDirection(3000);

            final Animation zoomAnimation = AnimationUtils.loadAnimation(context, R.anim.zoom);
            img2.startAnimation(zoomAnimation);
            notifyDataSetChanged();



        }

        final int x = i;
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickItemElement(x, img1);
                notifyDataSetChanged();
                setImageFocus();

            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getItem(x).getImg1() == 0) {
                    okclick();
                    list.remove(x);
                    notifyDataSetChanged();
                    return;
                }
                clickItemElement(x, img2);
                notifyDataSetChanged();
                setImageFocus();

            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemElement(x, img3);
                notifyDataSetChanged();
                setImageFocus();
            }
        });

        img1Ok.setImageResource(getItem(i).getImg1Ok());
        img2Ok.setImageResource(getItem(i).getImg2Ok());
        img3Ok.setImageResource(getItem(i).getImg3Ok());
        return view;
    }

    private void setImageFocus() {
        int h = 0;
        for (int j = 0; j < list.size(); j++) {
            if(getItem(j).getImg1() == 0){
                h = j;
            }
        }
        callBack.getElementFocus(h - 1);
    }

    private void clickItemElement(int x, ImageView imagView) {
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getImg1() == 0 || list.get(i).getImg3() == 0){
                if(i < x){
                    flag = true;
                }
                list.remove(i);
            }
        }

        Model m = new Model();

        if(flag) {
            if(imagView == img3){
                m.setImg2(list.get(x-1).getImg3());
                okclick();
                list.get(x-1).setImg3Ok(R.drawable.ic_check_24dp);
                list.add((x-1), m);
            }
            if(imagView == img2) {
                m.setImg2(list.get(x-1).getImg2());
                okclick();
                list.get(x-1).setImg2Ok(R.drawable.ic_check_24dp);
                list.add((x-1), m);
            }
            if(imagView == img1) {
                m.setImg2(list.get(x-1).getImg1());
                okclick();
                list.get(x-1).setImg1Ok(R.drawable.ic_check_24dp);
                list.add((x-1), m);
            }

        }else {
            if(imagView == img3){
                m.setImg2(list.get(x).getImg3());
                okclick();
                list.get(x).setImg3Ok(R.drawable.ic_check_24dp);
                list.add((x +1), m);
            }
            if(imagView == img2) {
                m.setImg2(list.get(x).getImg2());
                okclick();
                list.get(x).setImg2Ok(R.drawable.ic_check_24dp);
                list.add((x +1), m);
            }
            if(imagView == img1) {
                m.setImg2(list.get(x).getImg1());
                okclick();
                list.get(x).setImg1Ok(R.drawable.ic_check_24dp);
                list.add((x +1), m);
            }
        }
    }

    private void okclick() {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getImg1Ok() != 0){
                list.get(i).setImg1Ok(0);
            }
            if(list.get(i).getImg2Ok() != 0){
                list.get(i).setImg2Ok(0);
            }
            if(list.get(i).getImg3Ok() != 0){
                list.get(i).setImg3Ok(0);
            }
        }
    }

    public interface CallBack {
        public void getElementFocus(int x);
    }

}
