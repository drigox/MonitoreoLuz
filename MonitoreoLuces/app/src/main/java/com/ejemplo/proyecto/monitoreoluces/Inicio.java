package com.ejemplo.proyecto.monitoreoluces;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Inicio extends ActionBarActivity {

    private EditText ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Button conectar = (Button) findViewById(R.id.conexion);
        ip = (EditText) findViewById(R.id.ip);

        conectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Inicio.this, Panel.class);

                /* Bundle para enviar los datos a la otra vista */
                Bundle datos = new Bundle();
                datos.putString("datos", ip.getText().toString());
                i.putExtras(datos);
                startActivity(i);
            }
        });


    }

}
