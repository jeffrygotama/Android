package com.example.ezyfood_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class Drink extends AppCompatActivity {
    private Database[] drinks = {
            new Database("Latte", 10000, R.drawable.latte),
            new Database("Tea", 9000, R.drawable.tea),
    };
    private Button myorder_btn_drinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        GridView gridView = (GridView)findViewById(R.id.drinks_grid);
        DatabaseAdapter databasesAdapter = new DatabaseAdapter(this, drinks);
        gridView.setAdapter(databasesAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Database databases = drinks[i];
                Intent intent = new Intent(Drink.this, Order.class);
                Bundle extra = new Bundle();
                extra.putString("image", String.valueOf(databases.getImage()));
                extra.putString("name", databases.getName());
                extra.putString("price", String.valueOf(databases.getPrice()));
                intent.putExtras(extra);
                startActivity(intent);
            }
        });
        myorder_btn_drinks = (Button) findViewById(R.id.myorder_btn_drinks);
        myorder_btn_drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmyOrderclass();
            }
        });
    }

    public void openmyOrderclass(){
        Intent intent = new Intent(this, MyOrder.class);
        startActivity(intent);
    }
}
