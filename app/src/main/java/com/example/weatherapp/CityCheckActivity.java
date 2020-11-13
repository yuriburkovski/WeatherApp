package com.example.weatherapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.model.DataModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CityCheckActivity extends AppCompatActivity {

    private Button exit2Button;
    private Button check2Button;
    private EditText podajMiasto;
    private OkHttpClient client;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addListenerOnButton2();

    }

    public void addListenerOnButton2() {
        exit2Button = findViewById(R.id.exit2);
        check2Button = findViewById(R.id.Check2);
        podajMiasto = findViewById(R.id.editText2);
        check2Button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String city = podajMiasto.toString();
                        getJson(city);
                    }

                }

        );
        exit2Button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder a2_builder = new AlertDialog.Builder(CityCheckActivity.this);
                        a2_builder.setMessage("Chcesz wyjść w menu główne?")
                                .setCancelable(false)
                                .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert2 = a2_builder.create();
                        alert2.setTitle("Zamykam");
                        alert2.show();
                    }
                }
        );
    }

    private void getJson(String city) {
        client = new OkHttpClient();
        final DataModel data = new DataModel();
        result = findViewById(R.id.result);
        Request request = new Request.Builder()
                .url("http://api.openweathermap.org/data/2.5/weather?q=szczecin,pl&APPID=1d4cbd5eae1fbb37f92be746f37e4615&units=metric")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result.setText("Failure!");
                    }
                });

            }

            @Override
            public void onResponse(Call call, final Response response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
//                            result.setText(response.body().string());
                            String jsonStr = response.body().string();
                            JSONObject jsObj = new JSONObject(jsonStr);
//                            data.clouds = jsObj.getString("clouds");
                            data.temp = jsObj.getJSONObject("main").getDouble("temp");
                            data.feels_like = jsObj.getJSONObject("main").getDouble("feels_like");
                            data.pressure = jsObj.getJSONObject("main").getInt("pressure");
                            data.humidity = jsObj.getJSONObject("main").getInt("humidity");
                            data.wind = jsObj.getJSONObject("wind").getInt("speed");
                            result.setText(//"Clouds: " + data.clouds +
                                    "\nTemp: " + data.temp +
                                            "\nFeels like: " + data.feels_like +
                                            "\nPressure: " + data.pressure +
                                            "\nHumidity: " + data.humidity +
                                            "\nWind: " + data.wind);
                        } catch (IOException | JSONException e) {
                            result.setText("Error");
                        }
                    }
                });
            }
        });

    }

}
