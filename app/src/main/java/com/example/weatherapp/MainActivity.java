package com.example.weatherapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private Button checkButton;
    private Button exitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }

    public void addListenerOnButton (){

        checkButton = findViewById(R.id.CheckWeather);
        exitButton = findViewById(R.id.Exit);

        checkButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".Main2Activity");
                        startActivity(intent);
                    }
                }
        );

        exitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                        a_builder.setMessage("Chcesz zamknąć aplikacje?")
                                .setCancelable(false)
                                .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) { finish(); }
                                })
                                .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }
                                });
                        AlertDialog alert = a_builder.create();
                        alert.setTitle("Zamykanie aplikacji");
                        alert.show();
                    }
                }
        );
    }

}
