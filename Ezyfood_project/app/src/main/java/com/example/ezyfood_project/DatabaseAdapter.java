package com.example.ezyfood_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DatabaseAdapter extends BaseAdapter {
    private final Database[] databases;
    private final Context context;

    public DatabaseAdapter(Context context, Database[] databases) {
        this.context = context;
        this.databases = databases;
    }
    @Override
    public int getCount() {
        return databases.length;
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
        final Database database = databases[i];

        if(view == null){
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.layout_database, null);
        }
        final ImageView imageView = (ImageView) view.findViewById(R.id.databaseImage);
        final TextView nameView = (TextView) view.findViewById(R.id.databaseName);
        final TextView priceView = (TextView) view.findViewById(R.id.databasePrice);

        imageView.setImageResource(database.getImage());
        nameView.setText(database.getName());
        priceView.setText("Rp." + database.getPrice());
        notifyDataSetChanged();
        return view;
    }
}
