package com.example.ezyfood_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class Snack extends AppCompatActivity {
    private Database[] snacks = {
            new Database("Keripik", 5000, R.drawable.funghi),
            new Database("Cheese Roll", 9000, R.drawable.cheeseroll),
    };
    private Button myorder_btn_snacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack);

        GridView gridView = (GridView)findViewById(R.id.drinks_grid);
        DatabaseAdapter databasesAdapter = new DatabaseAdapter(this, snacks);
        gridView.setAdapter(databasesAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Database databases = snacks[i];
                Intent intent = new Intent(Snack.this, Order.class);
                Bundle extra = new Bundle();
                extra.putString("image", String.valueOf(databases.getImage()));
                extra.putString("name", databases.getName());
                extra.putString("price", String.valueOf(databases.getPrice()));
                intent.putExtras(extra);
                startActivity(intent);
            }
        });
        myorder_btn_snacks = (Button) findViewById(R.id.myorder_btn_snacks);
        myorder_btn_snacks.setOnClickListener(new View.OnClickListener() {
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
