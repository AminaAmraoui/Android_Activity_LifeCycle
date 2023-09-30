package com.example.tp01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(),
                "Welcome to OnCreate Method ",
                Toast.LENGTH_LONG).show();
        Snackbar s =Snackbar.make(findViewById(android.R.id.content),
                "Welcome to OnCreate Method !",Snackbar.LENGTH_LONG);
        s.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "Welcome to OnStart Method !", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "Welcome to OnResume Method !", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "Welcome to OnPause Method !", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("onStopTag", "welcome to onStop Method ! ");
    }
    @Override
    protected void onDestroy() {
        Log.i("onDestroyTag", "Welcome to onDestroy Method !");
        super.onDestroy();
    }
}