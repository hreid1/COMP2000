package com.example.sqllite;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextAge, editTextEmail;
    Button submit, viewData, deleteData;

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

        editTextName=findViewById(R.id.editText);
        editTextAge= findViewById(R.id.editText2);
        editTextEmail=findViewById(R.id.editText3);
        submit=findViewById(R.id.button);
        viewData=findViewById(R.id.button2);
        deleteData=findViewById(R.id.button3);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataModel data = new DataModel(
                        editTextName.getText().toString(),
                        Integer.parseInt(editTextAge.getText().toString()),
                        editTextEmail.getText().toString());

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                boolean success = databaseHelper.adduser(data);

                if (success){
                    Toast.makeText(MainActivity.this,
                            "Data added", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,
                            "Data not added", Toast.LENGTH_SHORT).show();
                }


//                Toast.makeText(MainActivity.this, ""+ data.toString(), Toast.LENGTH_SHORT).show();

            }
        });


        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                List<DataModel> data = databaseHelper.getAllUsers();

                Log.d("SQLdata", data.toString());

            }
        });


        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataModel data = new DataModel(
                        editTextName.getText().toString(),
                        Integer.parseInt(editTextAge.getText().toString()),
                        editTextEmail.getText().toString());

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                boolean success = databaseHelper.deleteUser(data);

                if (success){
                    Toast.makeText(MainActivity.this, "successfully deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "not deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
