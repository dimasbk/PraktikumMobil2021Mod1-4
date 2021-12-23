package com.example.modul2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    DBHelper DB;
    ArrayList<String> id, judul, tahun, umur, rating, genre;
    CustomAdapater customAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =  findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.floatingActionButton);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddData.class);
                startActivity(intent);
            }
        });

        DB = new DBHelper(MainActivity.this);
        id = new ArrayList<>();
        judul = new ArrayList<>();
        tahun = new ArrayList<>();
        umur = new ArrayList<>();
        rating = new ArrayList<>();
        genre = new ArrayList<>();

        storeDataInArrays();

        customAdapater = new CustomAdapater(MainActivity.this, MainActivity.this, id, judul, tahun, umur, rating, genre );
        recyclerView.setAdapter(customAdapater);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = DB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                judul.add(cursor.getString(1));
                tahun.add(cursor.getString(2));
                umur.add(cursor.getString(3));
                rating.add(cursor.getString(4));
                genre.add(cursor.getString(5));

            }
        }
    }
}