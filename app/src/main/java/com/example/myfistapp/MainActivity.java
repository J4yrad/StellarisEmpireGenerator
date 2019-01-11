package com.example.myfistapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EmpireViewModel mViewModel = ViewModelProviders.of(this).get(EmpireViewModel.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /** Called when the user taps the Create New Empire button */
    public void createNewEmpire(View view) {
        Intent intent = new Intent(this, DisplayCreatedEmpire.class);
        startActivity(intent);
    }
    public void showAllEmpires(View view) {
        Intent intent = new Intent(this, ViewAllEmpires.class);
        startActivity(intent);
    }

}
