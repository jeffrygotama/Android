package com.example.ezyfood_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends BaseAdapter {
    boolean meet = true;
    private final ArrayList<History> historiesOrder;
    private final Context context;
    public HistoryAdapter(Context context, ArrayList<History> databasesOrder) {
        this.historiesOrder = databasesOrder;
        this.context = context;
    }
    @Override
    public int getCount() {
        return historiesOrder.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final History h = historiesOrder.get(i);
        if(view == null){
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.layout_history, null);
        }
        final ImageView MOimageView = (ImageView) view.findViewById(R.id.ImageMyOrder_hist);
        TextView MOdateView = (TextView) view.findViewById(R.id.dateMyOrder);
        TextView MOaddressView_lat = (TextView) view.findViewById(R.id.addressMyOrder_lat);
        TextView MOaddressView_long = (TextView) view.findViewById(R.id.addressMyOrder_long);
        final TextView MOnameView = (TextView) view.findViewById(R.id.nameMyorder);
        final TextView MOpriceView = (TextView) view.findViewById(R.id.priceMyOrder);
        final TextView MOaddressView = (TextView) view.findViewById(R.id.address_mart);


        if(meet == true){
            MOdateView.setText(h.getDate().toString());
            MOaddressView.setText("Address Sender Market "+(MapsActivity.index+1));
            MOaddressView_lat.setText("Latitude: "+String.valueOf(h.getAddress().latitude));
            MOaddressView_long.setText("Longitude: "+String.valueOf(h.getAddress().longitude));

            meet = false;
        }
        for (int j = 1; j < historiesOrder.size() - 1; j++){
            History temp = historiesOrder.get(j);
            if(temp.getDate().toString().equals(historiesOrder.get(j + 1))){
                continue;
            }else{
                MOdateView.setText(temp.getDate().toString());
//                MOaddressView.setText(temp.getAlamat());
            }

        }

        MOimageView.setImageResource(h.getImage());
        MOnameView.setText(h.getName());
        MOpriceView.setText(h.getPrice() + " x " + h.getQuantity());

        return view;
    }
}
