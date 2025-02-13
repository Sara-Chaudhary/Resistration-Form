package com.example.test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.database.Cursor;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    DBHelper myDB;
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

        myDB = new DBHelper(this);
        EditText fn = findViewById(R.id.fname);
        EditText mn = findViewById(R.id.mname);
        EditText ln = findViewById(R.id.lname);
        EditText email = findViewById(R.id.mail);
        EditText enroll = findViewById(R.id.roll);
        TextView t1 = findViewById(R.id.result);
        Spinner s1 = findViewById(R.id.gen);
        Spinner s2 = findViewById(R.id.cl);

        Button insert = findViewById(R.id.INSERT);
        Button update = findViewById(R.id.update);
        Button del = findViewById(R.id.delete);
        Button view = findViewById(R.id.all);

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
                //do nothing
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = fn.getText().toString();
                String mname = mn.getText().toString();
                String lname = ln.getText().toString();
                String roll = enroll.getText().toString();
                String mail = email.getText().toString();
                if( fname.isEmpty() || lname.isEmpty() || roll.isEmpty() || mail.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_LONG).show();
                }
                boolean isInserted = myDB.insertData(fname,mname,lname,gender,mail,classes,roll);
                Toast.makeText(MainActivity.this, isInserted ? "Data Inserted" : "Insertion Failed" , Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = fn.getText().toString();
                String mname = mn.getText().toString();
                String lname = ln.getText().toString();
                String roll = enroll.getText().toString();
                String mail = email.getText().toString();
                if( fname.isEmpty() || lname.isEmpty() || roll.isEmpty() || mail.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_LONG).show();
                }
                boolean isUpdated = myDB.updateData(fname,mname,lname,gender,mail,classes,roll);
                Toast.makeText(MainActivity.this, isUpdated ? "Data Updated" : "Update Failed", Toast.LENGTH_SHORT).show();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roll = enroll.getText().toString();
                if(roll.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter Enrollment no.", Toast.LENGTH_SHORT).show();
                }
                boolean isDeleted = myDB.deleteData(roll);
                Toast.makeText(MainActivity.this, isDeleted ? "Data Deleted" : "Deletion Failed", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Cursor res = myDB.getAllData();
                if(res.getCount()==0){
                    t1.setText("NO DATA FOUND");
                    return;
                }
                StringBuilder buffer = new StringBuilder();
                while(res.moveToNext()){
                    buffer.append("ENROLL: ").append(res.getString(0)).append("\n");
                    buffer.append("NAME: ").append(res.getString(1)).append(res.getString(2)).append(res.getString(3)).append("\n");
                    buffer.append("GENDER: ").append(res.getString(4)).append("\n");
                    buffer.append("EMAIL: ").append(res.getString(5)).append("\n");
                    buffer.append("CLASS: ").append(res.getString(6)).append("\n\n");
                }

                t1.setText(buffer.toString());
            }
        });

    }

}