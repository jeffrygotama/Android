package com.example.ezyfood_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class MyOrder extends AppCompatActivity {
    private Button pay_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        GridView gridView = (GridView)findViewById(R.id.myorder_grid);
        MyOrderAdapter myOrderAdapters = new MyOrderAdapter(this, Order.databasesOrder);
        gridView.setAdapter(myOrderAdapters);

        total();

        pay_btn = (Button) findViewById(R.id.pay_btn);
        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sum = total();
                if(MainActivity.balance < sum){
                    Toast.makeText(MyOrder.this, "Please top up first", Toast.LENGTH_SHORT + 10).show();
                }else{
                    MainActivity.balance -= sum;
                    openpay();
                }

            }
        });
    }

    public int total(){
        TextView totalView_balance = (TextView) findViewById(R.id.textBalance);
        TextView totalView = (TextView) findViewById(R.id.totalPrice);
        int sum = 0;
        for(int i = 0; i< Order.databasesOrder.size(); i++) {
            int totalprice = Order.databasesOrder.get(i).getPrice() * Order.databasesOrder.get(i).getQuantity();
            sum += totalprice;
        }
        totalView_balance.setText("Balance: Rp. "+MainActivity.balance);
        totalView.setText("Price : Rp." + sum);
        return sum;
    }

    public void openpay(){
        Date date = new Date();
        for(int i = 0;i< Order.databasesOrder.size(); i++){
            HistoryOrder.histo.add(new History(Order.databasesOrder.get(i).getName(), Order.databasesOrder.get(i).getPrice(), Order.databasesOrder.get(i).getQuantity(), MapsActivity.list.get(MapsActivity.index), date, Order.databasesOrder.get(i).getImage()));
        }
        Order.databasesOrder.clear();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
