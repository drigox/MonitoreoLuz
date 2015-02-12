package com.ejemplo.proyecto.monitoreoluces;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class Inicio extends ActionBarActivity {

    private EditText ip;    //IP que viene dada por el usuario
    EditText port;         //Puerto por defecto
    TextView texto1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio); //XML

        Button conectar = (Button) findViewById(R.id.conexion); //boton conectar
        ip = (EditText) findViewById(R.id.ip);                  //IP que viene dada por el usuario
        port = (EditText)findViewById(R.id.port);


        conectar.setOnClickListener(conectarOnClickListener); //Llamada a onClick
    }

    View.OnClickListener conectarOnClickListener = new View.OnClickListener() { //Funcion OnClick

        @Override
        public void onClick(View arg0) {
            String Msgip = ip.getText().toString();      //IP
            String Msgport = port.getText().toString();
            if(Msgip.equals("") || Msgport.equals("")){                        //Comprobacion de mensaje a enviar
                Msgip = null;
                Toast.makeText(Inicio.this, "IP o Puerto no escrito", Toast.LENGTH_SHORT).show(); //
            }


            else {
                Intent i = new Intent(Inicio.this, Panel.class);
                // Bundle para enviar los datos a la otra vista
                Bundle datos = new Bundle();

                datos.putString("port", Msgport);   // Almacenar IP y puerto en el bundle
                datos.putString("ip", Msgip);       // Almacenar IP y puerto en el bundle

                i.putExtras(datos);                 //Almacenar el Bundle en el intent para enviar
                startActivity(i);
            }
        }
    };

}