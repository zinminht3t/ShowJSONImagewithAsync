package me.zinminhtet.jsonimage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends ArrayAdapter<String> {

    private List<String> items;
    int resource;

    public MyAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        this.resource = resource;
        this.items = items;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(resource, null);
        String eid = items.get(position);
        if (eid != null) {
            TextView e = (TextView) v.findViewById(R.id.textView);
            e.setText(eid);
            final ImageView IMAGE = (ImageView) v.findViewById(R.id.imageView2);
            new AsyncTask<String, Void, Bitmap>() {
                @Override
                protected Bitmap doInBackground(String... params) {
                    return Employee.getPhoto(true, params[0]);
                }

                @Override
                protected void onPostExecute(Bitmap result) {

                    IMAGE.setImageBitmap(result);
                }
            }.execute(eid);
        }
        return v;
    }
}