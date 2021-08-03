package com.example.android_project_8_sqlite;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static int PERMISSION_REQUEST_CODE = 100;
    private Button btn_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
            }, PERMISSION_REQUEST_CODE);
        } else {
            SQLiteHelper.builder();
        }

        /*ArrayList<Person> personArrayList = SQLiteHelper.builder().getPersons();

        if (personArrayList != null) {
            for (Person person : personArrayList) {
                Log.i("me", person.getFirstname() + " " + person.getLastname() + " " + person.getMobile());
            }
        }*/
        Person person = SQLiteHelper.builder().getPerson("hossein");
        Log.i("me", person == null ? "not exists" : person.getFirstname() + " " + person.getLastname() + " " + person.getMobile());
    }

    public void initViews() {
        btn_insert = findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_insert:
                Person person = new Person();
                person.setFirst_name("ahmad");
                person.setFirst_name("montazeri");
                person.setFirst_name("12345678");
                SQLiteHelper.builder().insertPersonTable(person);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                SQLiteHelper.builder();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}