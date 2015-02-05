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
    private ToggleButton boton2;
    private ToggleButton boton3;
    private ToggleButton boton4;
    private ToggleButton boton5;
    private ToggleButton boton6;
    private ToggleButton boton7;
    private ToggleButton boton8;

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
        boton2=(ToggleButton) findViewById(R.id.boton2);
        boton3=(ToggleButton) findViewById(R.id.boton3);
        boton4=(ToggleButton) findViewById(R.id.boton4);
        boton5=(ToggleButton) findViewById(R.id.boton5);
        boton6=(ToggleButton) findViewById(R.id.boton6);
        boton7=(ToggleButton) findViewById(R.id.boton7);
        boton8=(ToggleButton) findViewById(R.id.boton8);

        //Funcion Click en boton
        boton1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0){

                if(boton1.isChecked())

                    mostrar.setText("Botón Personalizado: ON");
                else
                    mostrar.setText("Botón Personalizado: OFF");
                    }



        });



        //Parseo info enviada desde vista 1

        char[] parseo = new char[ip.length()]; // char para parseo
        for (int i = 0; i < ip.length(); i++) {
            parseo[i] = (char) ip.charAt(i); //parseo

            String bitparse = String.valueOf(parseo[i]); // char paseo a string bitparse

            Log.e("Taggggg", String.valueOf(parseo));
            Log.e("Taggggg", String.valueOf(parseo[0]));

            int numero=1;                                                   //numero para comparacion
            String snumero= String.valueOf(numero);                         //tranformacion del numero a string para comparacion en if



            if(i==0) {                                                          //comprobacion bit 0

                Log.e("largo bitparse", String.valueOf(bitparse.length()));
                if (bitparse.equals(snumero)){                                  //Comparacion ==1
                    Log.e("TAG", "if 2");
                    boton1.performClick();                                      //Click forzado
                }
            }

        //------------------------------------------------------------------------------------------------------------------------------------
            if(i==1) {                                                          //comprobacion bit 1

                Log.e("largo bitparse", String.valueOf(bitparse.length()));
                if (bitparse.equals(snumero)){                                  //Comparacion ==1
                    Log.e("TAG", "if 2");
                    boton2.performClick();                                      //Click forzado
                }
            }

        //------------------------------------------------------------------------------------------------------------------------------------
            if(i==2) {                                                          //comprobacion bit 2

                Log.e("largo bitparse", String.valueOf(bitparse.length()));
                if (bitparse.equals(snumero)){                                  //Comparacion ==1
                    Log.e("TAG", "if 2");
                    boton3.performClick();                                      //Click forzado
                }
            }

        //------------------------------------------------------------------------------------------------------------------------------------
            if(i==3) {                                                          //comprobacion bit 3

                Log.e("largo bitparse", String.valueOf(bitparse.length()));
                if (bitparse.equals(snumero)){                                  //Comparacion ==1
                    Log.e("TAG", "if 2");
                    boton4.performClick();                                      //Click forzado
                }
            }

        //------------------------------------------------------------------------------------------------------------------------------------
            if(i==4) {                                                          //comprobacion bit 4

                Log.e("largo bitparse", String.valueOf(bitparse.length()));
                if (bitparse.equals(snumero)){                                  //Comparacion ==1
                    Log.e("TAG", "if 2");
                    boton5.performClick();                                      //Click forzado
                }
            }

        //------------------------------------------------------------------------------------------------------------------------------------
            if(i==5) {                                                          //comprobacion bit 5

                Log.e("largo bitparse", String.valueOf(bitparse.length()));
                if (bitparse.equals(snumero)){                                  //Comparacion ==1
                    Log.e("TAG", "if 2");
                    boton6.performClick();                                      //Click forzado
                }
            }

        //------------------------------------------------------------------------------------------------------------------------------------
            if(i==6) {                                                          //comprobacion bit 6
                Log.e("largo bitparse", String.valueOf(bitparse.length()));
                if (bitparse.equals(snumero)){                                  //Comparacion ==1
                    Log.e("TAG", "if 2");
                    boton7.performClick();                                      //Click forzado
                }
            }

            //------------------------------------------------------------------------------------------------------------------------------------
            if(i==7) {                                                          //comprobacion bit 1

                Log.e("largo bitparse", String.valueOf(bitparse.length()));
                if (bitparse.equals(snumero)){                                  //Comparacion ==1
                    Log.e("TAG", "if 2");
                    boton8.performClick();                                      //Click forzado
                }
            }


        }
    }

}
