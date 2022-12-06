package com.uog_mobile_application_development.m_hike;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.uog_mobile_application_development.m_hike.models.HikeDataModel;

import java.util.ArrayList;

public class HomeGridAdapter extends BaseAdapter {

    Context context;
    ArrayList<HikeDataModel> hikes;
    int[] image;

    LayoutInflater inflater;

    public HomeGridAdapter(Context context, int[] image, ArrayList<HikeDataModel> hikeData) {
        this.context = context;
        this.image = image;
        this.hikes = hikeData;
    }

    @Override
    public int getCount() {
        return hikes.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (inflater == null)
            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null){
            view = inflater.inflate(R.layout.home_grid_item, null);
        }

        ImageView imageView = view.findViewById(R.id.home_grid_image);
        TextView textView = view.findViewById(R.id.home_item_name);

        imageView.setImageResource(R.drawable.a);
        textView.setText(hikes.get(i).getHikeName());

        return view;
    }
}