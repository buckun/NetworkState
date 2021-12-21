package com.weatherapp.networksate;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String[] permissions = { Manifest.permission.READ_PHONE_STATE
            ,Manifest.permission.READ_PHONE_NUMBERS
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView connectionType = findViewById(R.id.connectionType);
        TextView speed = findViewById(R.id.speed);
        TextView status = findViewById(R.id.status);
        TextView strength = findViewById(R.id.strength);
        Button checkButton = findViewById(R.id.checkButton);


        if(ActivityCompat.checkSelfPermission(this.getApplicationContext(), "android.permission.READ_PHONE_STATE") != PackageManager.PERMISSION_GRANTED)        {
            ActivityCompat.requestPermissions(this, permissions,123);
        }


        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    try {
                         strength.setText("" + Connectivity.getTelephonyManager(getApplicationContext()));
                    }catch (Exception e)
                    {
                        strength.setText(0);
                    }
                }
                if (Connectivity.isConnected(getApplicationContext())){
                    status.setText("Connected");
                    if (Connectivity.isConnectedFast(getApplicationContext())){
                        speed.setText("fast");
                    }else{
                        speed.setText("low");
                    }
                }else{
                    status.setText("Disconnected");
                    connectionType.setText("no connection");
                    speed.setText("no connection");
                }

                if (Connectivity.isConnectedWifi(getApplicationContext())){
                    connectionType.setText("Connected to Wifi");
                }

                if (Connectivity.isConnectedMobile(getApplicationContext())){
                    connectionType.setText("Connected to Mobile");
                }

            }
        });



    }
}