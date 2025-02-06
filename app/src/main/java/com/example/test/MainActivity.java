package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String gender , classes ;

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

        Button b1 = findViewById(R.id.submit);
        EditText fn = findViewById(R.id.fname);
        EditText mn = findViewById(R.id.mname);
        EditText ln = findViewById(R.id.lname);
        EditText mail = findViewById(R.id.mail);
        EditText roll = findViewById(R.id.roll);
        TextView t1 = findViewById(R.id.result);
        Spinner s1 = findViewById(R.id.gen);
        Spinner s2 = findViewById(R.id.cl);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this , R.array.gender , R.layout.spinner);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this , R.array.classes , R.layout.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(adapter);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });

        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classes = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = fn.getText().toString();
                String mname = mn.getText().toString();
                String lname = ln.getText().toString();
                String email = mail.getText().toString();
                String enroll = roll.getText().toString();

                t1.setText("Hi "+ fname + " "+mname+" "+lname+" .\nGender:-" +gender+"\nRoll No :- "+enroll+"\nMail id :- "+ email + "\nClass :-"+classes+".");
            }
        });


    }
}