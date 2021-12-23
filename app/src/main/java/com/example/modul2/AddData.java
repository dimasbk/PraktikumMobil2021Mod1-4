package com.example.modul2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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


public class AddData extends AppCompatActivity {

    private TextView tr;
    private SeekBar sb;
    private Button btDialog;
    private EditText judul;
    private EditText tahun;
    private RadioGroup rg;
    private RadioButton rb;
    private TextView rating;
    private CheckBox romance, action, comedy, animation, horror, scifi;
    String genre = "";
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        judul = (EditText) findViewById(R.id.etnm);
        tahun = (EditText) findViewById(R.id.etth);
        tr = (TextView) findViewById(R.id.tr);
        sb = (SeekBar) findViewById(R.id.sb);
        rg = (RadioGroup) findViewById(R.id.rb);
        rating = (TextView) findViewById(R.id.tr);
        romance = (CheckBox) findViewById(R.id.cr);
        action = (CheckBox) findViewById(R.id.ca);
        comedy = (CheckBox) findViewById(R.id.cc);
        animation = (CheckBox) findViewById(R.id.can);
        horror = (CheckBox) findViewById(R.id.ch);
        scifi = (CheckBox) findViewById(R.id.cs);
        DB = new DBHelper(this);


        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tr.setText("" +progress+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btDialog = (Button) findViewById(R.id.btsend);
        btDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genre = "";
                String Judul = judul.getText().toString();
                String Tahun = tahun.getText().toString();
                int idradio = rg.getCheckedRadioButtonId();
                rb = (RadioButton) findViewById(idradio);

                if(Judul.equals("")){
                    judul.setError("Masukkan Judul Film");
                }else if(Tahun.equals("")) {
                    tahun.setError("Masukkan Tahun Film");
                }else if(idradio==-1){
                    Toast.makeText(AddData.this, "Pilih Salah Satu Rating Umur", Toast.LENGTH_SHORT).show();
                }else if(!romance.isChecked()&&!action.isChecked()&&!comedy.isChecked()&&!animation.isChecked()&&!horror.isChecked()&&!scifi.isChecked()){
                    Toast.makeText(AddData.this, "Pilih Minimal Satu Genre", Toast.LENGTH_SHORT).show();
                }else{
                    if(romance.isChecked()){
                        genre +=" " + romance.getText().toString();
                    }
                    if(action.isChecked()){
                        genre +=" " + action.getText().toString();
                    }
                    if(comedy.isChecked()){
                        genre +=" " + comedy.getText().toString();
                    }
                    if(animation.isChecked()){
                        genre +=" " + animation.getText().toString();
                    }
                    if(horror.isChecked()){
                        genre +=" " + horror.getText().toString();
                    }
                    if(scifi.isChecked()){
                        genre +=" " + scifi.getText().toString();
                    }
                    String Radio = rb.getText().toString();
                    String Rating = rating.getText().toString();
                    showDialog(Judul, Tahun, Radio, Rating, genre);
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(AddData.this, "Mohon Isi Data Dengan Benar", Toast.LENGTH_SHORT).show();
    }
    private void showDialog(String jdl, String thn, String rb, String rt, String gen){
        AlertDialog alertDialogBuilder = new AlertDialog.Builder(this)
                .setTitle("Apakah Data Sudah Benar?")
                .setMessage("Judul = " + jdl +"\n"+ "Tahun = " + thn + "\n" + "Batasan Umur = " + rb + "\n" + "Rating = " + rt + "\n" + "Genre = " + gen)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Boolean checkinsertdata = DB.insertfilmdata(jdl, thn, rb, rt, gen);
                        if(checkinsertdata==true) {
                            Toast.makeText(AddData.this, "Data Telah Masuk", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddData.this, HasilActivity.class);
                            intent.putExtra("JUDUL", jdl);
                            intent.putExtra("TAHUN", thn);
                            intent.putExtra("RATING", rt);
                            intent.putExtra("RADIO", rb);
                            intent.putExtra("GENRE", gen);
                            startActivity(intent);
                        }else {
                            Toast.makeText(AddData.this, "Data gagal Masuk", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel",null)
                .create();


        alertDialogBuilder.show();


    }
}