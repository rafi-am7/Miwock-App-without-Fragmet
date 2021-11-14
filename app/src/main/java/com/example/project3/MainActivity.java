package com.example.project3;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


/*        ArrayList<String> restuarant = new ArrayList<String>();
        restuarant.add("mermaid");
        restuarant.add("goni");
        restuarant.remove("mermaid");
        int numofResturants = restuarant.size();
        Log.i("MainA", restuarant.get(0));

        String[] numberEnglish = new String[10];
        numberEnglish[0]="One";
*/




        TextView colorTextView = findViewById(R.id.colorsTextView);
        colorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(i);
            }
        });

        TextView famlyTextView = findViewById(R.id.familyTextView);
        famlyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(i);
            }
        });




    }


    public void openNumber(View view) {
        Intent i = new Intent(this,NumbersActivity.class);
        startActivity(i);
    }



    public void openPhrases(View view) {
        Intent i= new Intent(this, PhrasesActivity.class);
        startActivity(i);
    }
}