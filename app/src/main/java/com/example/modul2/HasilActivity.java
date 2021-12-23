package com.example.modul2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HasilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        Button button = findViewById(R.id.buttonmenu);

        Intent intent = getIntent();
        String mjudul = intent.getStringExtra("JUDUL");
        String mtahun = intent.getStringExtra("TAHUN");
        String mradio = intent.getStringExtra("RADIO");
        String mrating = intent.getStringExtra("RATING");
        String mgenre = intent.getStringExtra("GENRE");

        TextView Judul = findViewById(R.id.judul);
        TextView Tahun = findViewById(R.id.tahun);
        TextView Radio = findViewById(R.id.radio);
        TextView Rating = findViewById(R.id.rating);
        TextView Genre = findViewById(R.id.genre);

        Judul.setText("Judul : " + mjudul);
        Tahun.setText("tahun : " + mtahun);
        Radio.setText("Rating Umur : " + mradio);
        Rating.setText("Judul : " + mrating);
        Genre.setText("Judul : " + mgenre);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HasilActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(HasilActivity.this, "Data Berhasil Dibuat", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(HasilActivity.this, "Telah Keluar Dari Preview Data", Toast.LENGTH_SHORT).show();
    }
}