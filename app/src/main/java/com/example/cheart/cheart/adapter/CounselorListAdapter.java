package com.example.cheart.cheart.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.cheart.cheart.R;
import com.example.cheart.cheart.app.VolleyAppController;
import com.example.cheart.cheart.model.Counselor;
import com.example.cheart.cheart.model.Movie;

import java.util.List;

/**
 * Created by Administrator on 5/7/2016.
 */
public class CounselorListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Counselor> counselorItems;
    ImageLoader imageLoader = VolleyAppController.getInstance().getImageLoader();

    public CounselorListAdapter(Activity activity, List<Counselor> movieItems) {
        this.activity = activity;
        this.counselorItems = movieItems;
    }

    @Override
    public int getCount() {
        return counselorItems.size();
    }

    @Override
    public Object getItem(int location) {
        return counselorItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.counselor_list_row, null);

        if (imageLoader == null)
            imageLoader = VolleyAppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView desc = (TextView) convertView.findViewById(R.id.desc);
        TextView id = (TextView) convertView.findViewById(R.id.id);

        // getting movie data for the row
        Counselor c = counselorItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(c.getThumbnailUrl(), imageLoader);
        Log.d("Masuk","Hehe");

        // title
        name.setText(c.getName());

        // rating
        desc.setText(c.getDescription());

        // release year
        id.setText(Integer.toString(c.getIdCounselor()));

        return convertView;
    }
}
