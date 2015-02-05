package com.ejemplo.proyecto.monitoreoluces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;


public class Panel extends ActionBarActivity {

    private ToggleButton boton1;
    private TextView mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        /* obtener la info enviada por el bundle*/
        Bundle bundle = this.getIntent().getExtras();
        String ip= bundle.getString("datos");
        mostrar = (TextView) findViewById(R.id.dato);
        mostrar.setText(ip);

        //Definicion del boton 1
        boton1=(ToggleButton) findViewById(R.id.boton1);

        //Funcion Click en boton
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Parseo info enviada desde vista 1

        char[] parseo = new char[ip.length()]; // char para parseo
        for (int i = 0; i < ip.length(); i++) {
            parseo[i] = (char) ip.charAt(i); //parseo

            String bitparse = String.valueOf(parseo[i]); // char paseo a string bitparse
            Log.e("Tag", bitparse);

            if(i==0) {                                                          //comprobacion bit 0
                int numero=1;                                                   //numero para comparacion

                String snumero= String.valueOf(numero);                         //tranformacion del numero a string para comparacion en if
                Log.e("largo bitparse", String.valueOf(bitparse.length()));
                if (bitparse.equals(snumero)){                                  //Comparacion ==1
                    Log.e("TAG", "if 2");
                    boton1.performClick();                                      //Click forzado
                }
            }


        }






    }

}
