package com.example.ezyfood_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Order extends AppCompatActivity {
    private Button orderMore_btn;
    private Button myorder_btn_order;
    public static List<DatabaseOrder> databasesOrder = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Bundle extra = getIntent().getExtras();
        ImageView imageView = (ImageView)findViewById(R.id.databaseImageOrder);
        TextView nameView = (TextView)findViewById(R.id.databaseNameOrder);
        TextView priceView = (TextView)findViewById(R.id.databasePriceOrder);

        if(extra != null){
            imageView .setImageResource(Integer.parseInt(extra.getString("image")));
            nameView.setText(extra.getString("name"));
            priceView.setText("Rp." + extra.getString("price"));
        }

        orderMore_btn = (Button) findViewById(R.id.orderMore_btn);
        orderMore_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainMenu();
            }
        });

        myorder_btn_order = (Button) findViewById(R.id.myorder_btn_order);
        myorder_btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmyOrderclass();
            }
        });
    }

    public void openMainMenu(){
        Bundle extra = getIntent().getExtras();
        EditText editText = (EditText)findViewById(R.id.quantityOrder);
        databasesOrder.add(new DatabaseOrder (extra.getString("name"), Integer.parseInt(extra.getString("price")), Integer.parseInt(extra.getString("image")), Integer.parseInt(editText.getText().toString())));
        Intent intent = new Intent(this, MainActivity.class);
        Bundle extra_order = new Bundle();
        extra_order.putString("image", extra.getString("image"));
        extra_order.putString("name", extra.getString("name"));
        extra_order.putString("price", extra.getString("price"));
        extra_order.putString("quantity", editText.getText().toString());
        intent.putExtras(extra_order);
        startActivity(intent);
    }

    public void openmyOrderclass(){
        Intent intent = new Intent(this, MyOrder.class);
        startActivity(intent);
    }
}
