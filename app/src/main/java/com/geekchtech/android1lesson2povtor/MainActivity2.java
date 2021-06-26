package com.geekchtech.android1lesson2povtor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    EditText name, surname;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = findViewById(R.id.et_name);
        surname = findViewById(R.id.et_surname);
        save = findViewById(R.id.btn_save);
        if (getIntent() != null) {
            name.setText(getIntent().getStringExtra("naame"));
            surname.setText(getIntent().getStringExtra("surrname"));
        }
        onClick();
    }

    private void onClick() {
        save.setOnClickListener(v -> {
            Intent intent = new Intent();
            if (name.getText().toString().equals("") || surname.getText().toString().equals("")) {
                Toast.makeText(this, "Fill name, surname", Toast.LENGTH_LONG).show();
            } else {
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("surname", surname.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}