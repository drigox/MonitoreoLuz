package com.ejemplo.proyecto.monitoreoluces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;


public class Panel extends ActionBarActivity {

    private ToggleButton boton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);


        boton1=(ToggleButton) findViewById(R.id.boton1);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

}
