package com.example.myfistapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Button GenerateButton = findViewById(R.id.generateButton);
        Button ViewButton = findViewById(R.id.viewButton);
        GenerateButton.setBackground(getDrawable(R.mipmap.view_button));
        ViewButton.setBackground(getDrawable(R.mipmap.view_button));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
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
