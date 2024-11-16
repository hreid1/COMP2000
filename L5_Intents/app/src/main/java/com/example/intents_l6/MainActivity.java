package com.example.intents_l6;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Intents: facilitates communication between different components of an app,
        //          as well as between different applications.

        // types of intents:
        // 1- Explicit Intents
        Button btn = findViewById(R.id.btn);
        EditText inText = findViewById(R.id.editTextText);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Explicit Intent
//                goToSecondActivity();




                String text = inText.getText().toString();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("text", text);
                startActivity(intent);


            }
        });


        // 2- Implicit Intents
        Button btn2 = findViewById(R.id.openBrowser);
        btn2.setVisibility(View.INVISIBLE);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gotolink();
            }
        });

    }


    public void goToSecondActivity(){
        Intent intent = new Intent(this, SecondActivity.class );
        startActivity(intent);
    }










    public void gotolink(){
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com"));
        startActivity(intent);
    }

}