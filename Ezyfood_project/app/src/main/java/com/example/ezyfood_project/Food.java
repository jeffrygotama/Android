package com.example.ezyfood_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class Food extends AppCompatActivity {
    private Database[] foods = {
            new Database("funghi", 70000, R.drawable.funghi),
            new Database("Takoyaki", 18000, R.drawable.takoyaki),
    };
    private Button myorder_btn_foods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        GridView gridView = (GridView)findViewById(R.id.drinks_grid);
        DatabaseAdapter databasesAdapter = new DatabaseAdapter(this, foods);
        gridView.setAdapter(databasesAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Database databases = foods[i];
                Intent intent = new Intent(Food.this, Order.class);
                Bundle extra = new Bundle();
                extra.putString("image", String.valueOf(databases.getImage()));
                extra.putString("name", databases.getName());
                extra.putString("price", String.valueOf(databases.getPrice()));
                intent.putExtras(extra);
                startActivity(intent);
            }
        });
        myorder_btn_foods = (Button) findViewById(R.id.myorder_btn_foods);
        myorder_btn_foods.setOnClickListener(new View.OnClickListener() {
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
