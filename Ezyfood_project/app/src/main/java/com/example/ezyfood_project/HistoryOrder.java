package com.example.ezyfood_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;

public class HistoryOrder extends AppCompatActivity {
    public static ArrayList<History> histo = new ArrayList<>();

    private Button back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        if(histo.size() > 0){
        }

        GridView gridView = (GridView)findViewById(R.id.myhistory_grid);
        HistoryAdapter histAdapters = new HistoryAdapter(this, histo);
        gridView.setAdapter(histAdapters);

        back_btn = (Button) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back_to_main();
            }
        });
    }
    public void back_to_main(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
