package com.example.modul2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText etjdlup, etthnup;
    RadioGroup rgupdate;
    CheckBox romance, action, comedy, animation, horror, scifi;
    Button btupdate,btdelete;
    SeekBar sbup;
    RadioButton rb1, rb2, rb3, rb;
    TextView rat;
    String id, judul, jdl, tahun, thn, umur, umr, rating, ratnew, genre, gen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        etjdlup = findViewById(R.id.etjdlup);
        etthnup = findViewById(R.id.etthnup);
        btupdate = findViewById(R.id.btupdate);
        btdelete = findViewById(R.id.btdelete);
        sbup = findViewById(R.id.sbup);
        rb1 = findViewById(R.id.rb1up);
        rb2 = findViewById(R.id.rb2up);
        rb3 = findViewById(R.id.rb3up);
        rgupdate = findViewById(R.id.rgupdate);
        rat = findViewById(R.id.trup);
        romance = findViewById(R.id.crup);
        action = findViewById(R.id.caup);
        comedy = findViewById(R.id.ccup);
        animation = findViewById(R.id.canup);
        horror = findViewById(R.id.chup);
        scifi = findViewById(R.id.csup);

        getIntentData();

        sbup.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rat.setText("" +progress+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gen = "";
                jdl = etjdlup.getText().toString();
                thn = etthnup.getText().toString();
                int idradio = rgupdate.getCheckedRadioButtonId();
                rb = (RadioButton) findViewById(idradio);

                if(etjdlup.equals("")){
                    etjdlup.setError("Masukkan Judul Film");
                }else if(etthnup.equals("")) {
                    etthnup.setError("Masukkan Tahun Film");
                }else if(idradio==-1){
                    Toast.makeText(UpdateActivity.this, "Pilih Salah Satu Rating Umur", Toast.LENGTH_SHORT).show();
                }else if(!romance.isChecked()&&!action.isChecked()&&!comedy.isChecked()&&!animation.isChecked()&&!horror.isChecked()&&!scifi.isChecked()){
                    Toast.makeText(UpdateActivity.this, "Pilih Minimal Satu Genre", Toast.LENGTH_SHORT).show();
                }else {
                    if (romance.isChecked()) {
                        gen += " " + romance.getText().toString();
                    }
                    if (action.isChecked()) {
                        gen += " " + action.getText().toString();
                    }
                    if (comedy.isChecked()) {
                        gen += " " + comedy.getText().toString();
                    }
                    if (animation.isChecked()) {
                        gen += " " + animation.getText().toString();
                    }
                    if (horror.isChecked()) {
                        gen += " " + horror.getText().toString();
                    }
                    if (scifi.isChecked()) {
                        gen += " " + scifi.getText().toString();
                    }
                    umr = rb.getText().toString();

                    ratnew = rat.getText().toString();
                }
                DBHelper myDB = new DBHelper(UpdateActivity.this);
                myDB.updateData(id, jdl, thn, umr, ratnew, gen);
            }
        });

        btdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogConfirm(id);
            }
        });

    }

    void dialogConfirm(String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus Data");
        builder.setMessage("Apakah anda yakin ingin menghapus data ini?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper myDB = new DBHelper(UpdateActivity.this);
                myDB.deleteData(id);
                finish();
            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.create().show();
    }

    void getIntentData(){
        if(getIntent().hasExtra("id1") &&
                getIntent().hasExtra("judul1") &&
                getIntent().hasExtra("tahun1") &&
                getIntent().hasExtra("umur1") &&
                getIntent().hasExtra("rating1") &&
                getIntent().hasExtra("genre1")){
            id = getIntent().getStringExtra("id1");
            judul = getIntent().getStringExtra("judul1");
            tahun = getIntent().getStringExtra("tahun1");
            umur = getIntent().getStringExtra("umur1");
            rating = getIntent().getStringExtra("rating1");
            genre = getIntent().getStringExtra("genre1");

            etjdlup.setText(judul);
            etthnup.setText(tahun);
            if(umur == "Semua Umur"){
                rb1.setChecked(true);

            }else if(umur == "13+"){

                rb2.setChecked(true);

            }else if(umur == "18+"){

                rb3.setChecked(true);
            }else{
                rb1.setChecked(false);
                rb2.setChecked(false);
                rb3.setChecked(false);
            }

        }else{
            Toast.makeText(this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        }
    }
}