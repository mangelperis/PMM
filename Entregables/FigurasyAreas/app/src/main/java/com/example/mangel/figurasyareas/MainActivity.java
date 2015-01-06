package com.example.mangel.figurasyareas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonAreas = (Button) findViewById(R.id.buttonAreas);


        buttonAreas.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0)
            {
                Intent miIntent= new Intent(MainActivity.this, Areas.class);
                startActivity(miIntent);
            }
        });


    }

}