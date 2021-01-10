package com.example.ezyfood_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button drinks_btn;
    private Button foods_btn;
    private Button snacks_btn;
    private Button topup_btn_menu;
    private Button myorder_btn;
    private Button mymaps_btn;
    private Button myhistory_btn;
    public static int balance = 0;
    public static boolean met = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drinks_btn = (Button) findViewById(R.id.drinks_btn);
        drinks_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendrinkclass();
            }
        });

        snacks_btn = (Button) findViewById(R.id.snacks_btn);
        snacks_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensnackclass();
            }
        });

        foods_btn = (Button) findViewById(R.id.foods_btn);
        foods_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openfoodclass();
            }
        });

        topup_btn_menu = (Button) findViewById(R.id.topup_btn_menu);
        topup_btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opentopupclass();
            }
        });

        mymaps_btn = (Button) findViewById(R.id.mymaps_btn);
        mymaps_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmapsclass();
            }
        });

        myhistory_btn = (Button) findViewById(R.id.myhistory_btn);
        myhistory_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhistoryclass();
            }
        });

        myorder_btn = (Button) findViewById(R.id.myorder_btn);
        myorder_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmyOrderclass();
            }
        });
    }
    public void opendrinkclass(){
        Intent intent = new Intent(this, Drink.class);
        startActivity(intent);
    }
    public void opensnackclass(){
        Intent intent = new Intent(this, Snack.class);
        startActivity(intent);
    }
    public void openfoodclass(){
        Intent intent = new Intent(this, Food.class);
        startActivity(intent);
    }
    public void openmyOrderclass(){
        Intent intent = new Intent(this, MyOrder.class);
        startActivity(intent);
    }
    public void opentopupclass(){
        Intent intent = new Intent(this, Topup.class);
        startActivity(intent);
    }

    public void openmapsclass(){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("local","mart2");
        startActivity(intent);
    }

    public void openhistoryclass(){
        Intent intent = new Intent(this, HistoryOrder.class);
        startActivity(intent);
    }
}