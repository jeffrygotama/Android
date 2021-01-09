package com.example.ezyfood_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Topup extends AppCompatActivity {
    private Button topup_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);

        calculate();

        topup_btn = (Button) findViewById(R.id.topup_btn);
        topup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topupbalance();
            }
        });
    }

    public void topupbalance() {
        EditText editText = (EditText) findViewById(R.id.howmuch);
        MainActivity.balance += Integer.parseInt(editText.getText().toString());
        Toast.makeText(Topup.this, "Topup of Rp. "+editText.getText().toString()+" has succeeded", Toast.LENGTH_SHORT + 10).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void calculate(){
        TextView textView = (TextView)findViewById(R.id.textBalance);
        textView.setText("Balance : Rp." + MainActivity.balance);
    }
}
