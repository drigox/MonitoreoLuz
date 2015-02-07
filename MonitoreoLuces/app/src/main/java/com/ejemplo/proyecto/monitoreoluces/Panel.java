package com.ejemplo.proyecto.monitoreoluces;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        /* obtener la info enviada por el bundle*/
        Bundle bundle = this.getIntent().getExtras();
        String ip = bundle.getString("datos"); //Respuesta
        final String IPP= bundle.getString("ip");
        mostrar = (TextView) findViewById(R.id.dato);
        mostrar.setText(ip);

        //Definicion del boton 1
        boton1 = (ToggleButton) findViewById(R.id.boton1);
        boton2 = (ToggleButton) findViewById(R.id.boton2);
        boton3 = (ToggleButton) findViewById(R.id.boton3);
        boton4 = (ToggleButton) findViewById(R.id.boton4);
        boton5 = (ToggleButton) findViewById(R.id.boton5);
        boton6 = (ToggleButton) findViewById(R.id.boton6);
        boton7 = (ToggleButton) findViewById(R.id.boton7);
        boton8 = (ToggleButton) findViewById(R.id.boton8);


        //Parseo info enviada desde vista 1

        final char[] parseo = new char[ip.length()]; // char para parseo
        for (int i = 0; i < ip.length(); i++) {
            parseo[i] = (char) ip.charAt(i); //parseo

            String bitparse = String.valueOf(parseo[i]); // char paseo a string bitparse

            Log.e("Taggggg", String.valueOf(parseo));
            Log.e("Taggggg", String.valueOf(parseo[0]));

            int numero = 1;                                                   //numero para comparacion
            String snumero = String.valueOf(numero);                         //tranformacion del numero a string para comparacion en if


            if (i == 4) {                                                          //comprobacion bit 0

                Log.e("largo bitparse", String.valueOf(bitparse.length()));
                if (bitparse.equals(snumero)) {                                  //Comparacion ==1
                    Log.e("TAG", "if 2");
                    boton1.performClick();                                      //Click forzado
                }
            }

            //------------------------------------------------------------------------------------------------------------------------------------
            if (i == 5) {                                                          //comprobacion bit 1

                Log.e("largo bitparse", String.valueOf(bitparse.length()));
                if (bitparse.equals(snumero)) {                                  //Comparacion ==1
                    Log.e("TAG", "if 2");
                    boton2.performClick();                                      //Click forzado
                }
            }

            //------------------------------------------------------------------------------------------------------------------------------------
            if (i == 6) {                                                          //comprobacion bit 2

                Log.e("largo bitparse", String.valueOf(bitparse.length()));
                if (bitparse.equals(snumero)) {                                  //Comparacion ==1
                    Log.e("TAG", "if 2");
                    boton3.performClick();                                      //Click forzado
                }
            }

            //------------------------------------------------------------------------------------------------------------------------------------
            if (i == 7) {                                                          //comprobacion bit 3

                Log.e("largo bitparse", String.valueOf(bitparse.length()));
                if (bitparse.equals(snumero)) {                                  //Comparacion ==1
                    Log.e("TAG", "if 2");
                    boton4.performClick();                                      //Click forzado
                }
            }

            //------------------------------------------------------------------------------------------------------------------------------------
            if (i == 8) {                                                          //comprobacion bit 4

                Log.e("largo bitparse", String.valueOf(bitparse.length()));
                if (bitparse.equals(snumero)) {                                  //Comparacion ==1
                    Log.e("TAG", "if 2");
                    boton5.performClick();                                      //Click forzado
                }
            }

            //------------------------------------------------------------------------------------------------------------------------------------
            if (i == 9) {                                                          //comprobacion bit 5

                Log.e("largo bitparse", String.valueOf(bitparse.length()));
                if (bitparse.equals(snumero)) {                                  //Comparacion ==1
                    Log.e("TAG", "if 2");
                    boton6.performClick();                                      //Click forzado
                }
            }

            //------------------------------------------------------------------------------------------------------------------------------------
            if (i == 10) {                                                          //comprobacion bit 6
                Log.e("largo bitparse", String.valueOf(bitparse.length()));
                if (bitparse.equals(snumero)) {                                  //Comparacion ==1
                    Log.e("TAG", "if 2");
                    boton7.performClick();                                      //Click forzado
                }
            }

            //------------------------------------------------------------------------------------------------------------------------------------
            if (i == 11) {                                                          //comprobacion bit 1

                Log.e("largo bitparse", String.valueOf(bitparse.length()));
                if (bitparse.equals(snumero)) {                                  //Comparacion ==1
                    Log.e("TAG", "if 2");
                    boton8.performClick();                                      //Click forzado
                }
            }


        }

        //Funcion Click en boton
        boton1.setOnClickListener(new View.OnClickListener() {




            public void onClick(View arg0) {
                int numero2 = 1;
                String snumero2 = String.valueOf(numero2);
                String bitcomparar = String.valueOf(parseo[4]);
                int port= 8080;
                //String ip="192.168.1.111";

                //Cambiar ip por IPP en cada llamado a la funcion en cada boton y cada condicion


                if (bitcomparar.equals(snumero2)) {             // revisa estado boton =1
                    char numeroaux = '0';
                    parseo[4] = numeroaux;                      //Si es 1 lo cambia a 0
                    mostrar.setText(String.valueOf(parseo));
                    MyClientTask myClientTask = new MyClientTask(IPP,port,String.valueOf(parseo));
                    myClientTask.execute();


                } else {
                    char numeroaux = '1';
                    parseo[4] = numeroaux;                      //Caso contario 0->1
                    mostrar.setText(String.valueOf(parseo));
                    MyClientTask myClientTask = new MyClientTask(IPP,port,String.valueOf(parseo));
                    myClientTask.execute();

                }
            }
        });

        // OnCLick boton 2 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        boton2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                int numero2 = 1;
                String snumero2 = String.valueOf(numero2);
                String bitcomparar = String.valueOf(parseo[5]);
                int port= 8080;
                String ip="192.168.1.111";


                if (bitcomparar.equals(snumero2)) {             // revisa estado boton =1
                    char numeroaux = '0';
                    parseo[5] = numeroaux;                      //Si es 1 lo cambia a 0
                    mostrar.setText(String.valueOf(parseo));
                    MyClientTask myClientTask = new MyClientTask(ip,port,String.valueOf(parseo));
                    myClientTask.execute();
                } else {
                    char numeroaux = '1';
                    parseo[5] = numeroaux;                      //Caso contario 0->1
                    mostrar.setText(String.valueOf(parseo));
                    MyClientTask myClientTask = new MyClientTask(ip,port,String.valueOf(parseo));
                    myClientTask.execute();
                }
            }
        });

        // OnCLick boton 3 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        boton3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                int numero2 = 1;
                String snumero2 = String.valueOf(numero2);
                String bitcomparar = String.valueOf(parseo[6]);
                int port= 8080;
                String ip="192.168.1.111";


                if (bitcomparar.equals(snumero2)) {             // revisa estado boton =1
                    char numeroaux = '0';
                    parseo[6] = numeroaux;                      //Si es 1 lo cambia a 0
                    mostrar.setText(String.valueOf(parseo));
                    MyClientTask myClientTask = new MyClientTask(ip,port,String.valueOf(parseo));
                    myClientTask.execute();
                } else {
                    char numeroaux = '1';
                    parseo[6] = numeroaux;                      //Caso contario 0->1
                    mostrar.setText(String.valueOf(parseo));
                    MyClientTask myClientTask = new MyClientTask(ip,port,String.valueOf(parseo));
                    myClientTask.execute();
                }
            }
        });

        // OnCLick boton 4 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        boton4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                int numero2 = 1;
                String snumero2 = String.valueOf(numero2);
                String bitcomparar = String.valueOf(parseo[7]);
                int port= 8080;
                String ip="192.168.1.111";


                if (bitcomparar.equals(snumero2)) {             // revisa estado boton =1
                    char numeroaux = '0';
                    parseo[7] = numeroaux;                      //Si es 1 lo cambia a 0
                    mostrar.setText(String.valueOf(parseo));
                    MyClientTask myClientTask = new MyClientTask(ip,port,String.valueOf(parseo));
                    myClientTask.execute();
                } else {
                    char numeroaux = '1';
                    parseo[7] = numeroaux;                      //Caso contario 0->1
                    mostrar.setText(String.valueOf(parseo));
                    MyClientTask myClientTask = new MyClientTask(ip,port,String.valueOf(parseo));
                    myClientTask.execute();
                }
            }
        });


        // OnCLick boton 5 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        boton5.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                int numero2 = 1;
                String snumero2 = String.valueOf(numero2);
                String bitcomparar = String.valueOf(parseo[8]);
                int port= 8080;
                String ip="192.168.1.111";


                if (bitcomparar.equals(snumero2)) {             // revisa estado boton =1
                    char numeroaux = '0';
                    parseo[8] = numeroaux;                      //Si es 1 lo cambia a 0
                    mostrar.setText(String.valueOf(parseo));
                    MyClientTask myClientTask = new MyClientTask(ip,port,String.valueOf(parseo));
                    myClientTask.execute();
                } else {
                    char numeroaux = '1';
                    parseo[8] = numeroaux;                      //Caso contario 0->1
                    mostrar.setText(String.valueOf(parseo));
                    MyClientTask myClientTask = new MyClientTask(ip,port,String.valueOf(parseo));
                    myClientTask.execute();
                }
            }
        });


        // OnCLick boton 6 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        boton6.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                int numero2 = 1;
                String snumero2 = String.valueOf(numero2);
                String bitcomparar = String.valueOf(parseo[9]);
                int port= 8080;
                String ip="192.168.1.111";


                if (bitcomparar.equals(snumero2)) {             // revisa estado boton =1
                    char numeroaux = '0';
                    parseo[9] = numeroaux;                      //Si es 1 lo cambia a 0
                    mostrar.setText(String.valueOf(parseo));
                    MyClientTask myClientTask = new MyClientTask(ip,port,String.valueOf(parseo));
                    myClientTask.execute();
                } else {
                    char numeroaux = '1';
                    parseo[9] = numeroaux;                      //Caso contario 0->1
                    mostrar.setText(String.valueOf(parseo));
                    MyClientTask myClientTask = new MyClientTask(ip,port,String.valueOf(parseo));
                    myClientTask.execute();
                }
            }
        });

        // OnCLick boton 7 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        boton7.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                int numero2 = 1;
                String snumero2 = String.valueOf(numero2);
                String bitcomparar = String.valueOf(parseo[10]);
                int port= 8080;
                String ip="192.168.1.111";


                if (bitcomparar.equals(snumero2)) {             // revisa estado boton =1
                    char numeroaux = '0';
                    parseo[10] = numeroaux;                      //Si es 1 lo cambia a 0
                    mostrar.setText(String.valueOf(parseo));
                    MyClientTask myClientTask = new MyClientTask(ip,port,String.valueOf(parseo));
                    myClientTask.execute();
                } else {
                    char numeroaux = '1';
                    parseo[10] = numeroaux;                      //Caso contario 0->1
                    mostrar.setText(String.valueOf(parseo));
                    MyClientTask myClientTask = new MyClientTask(ip,port,String.valueOf(parseo));
                    myClientTask.execute();
                }
            }
        });

        // OnCLick boton 2 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        boton8.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                int numero2 = 1;
                String snumero2 = String.valueOf(numero2);
                String bitcomparar = String.valueOf(parseo[11]);
                int port= 8080;
                String ip="192.168.1.111";


                if (bitcomparar.equals(snumero2)) {             // revisa estado boton =1
                    char numeroaux = '0';
                    parseo[11] = numeroaux;                      //Si es 1 lo cambia a 0
                    mostrar.setText(String.valueOf(parseo));
                    MyClientTask myClientTask = new MyClientTask(ip,port,String.valueOf(parseo));
                    myClientTask.execute();
                } else {
                    char numeroaux = '1';
                    parseo[11] = numeroaux;                      //Caso contario 0->1
                    mostrar.setText(String.valueOf(parseo));
                    MyClientTask myClientTask = new MyClientTask(ip,port,String.valueOf(parseo));
                    myClientTask.execute();
                }
            }
        });

    }


    public class MyClientTask extends AsyncTask<Void, Void, Void> {

        String dstAddress;
        int dstPort;
        public String response = "";

        String msgToServer;

        MyClientTask(String addr, int port, String msgTo) {
            dstAddress = addr;
            dstPort = port;
            msgToServer = msgTo;
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            Socket socket = null;
            DataOutputStream dataOutputStream = null;
            DataInputStream dataInputStream = null;

            try {
                socket = new Socket(dstAddress, dstPort);
                dataOutputStream = new DataOutputStream(
                        socket.getOutputStream());
                dataInputStream = new DataInputStream(socket.getInputStream());

                if (msgToServer != null) {
                    dataOutputStream.writeUTF(msgToServer); // Envio del mensaje
                }

                response = dataInputStream.readUTF();       //Lectura de info desde el servidor

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "UnknownHostException: " + e.toString();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "IOException: " + e.toString();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                if (dataInputStream != null) {
                    try {
                        dataInputStream.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }
    /*    @Override
        protected void onPostExecute(Void result) {
            textResponse.setText(response);
            super.onPostExecute(result);

            Toast.makeText(Inicio.this, response, Toast.LENGTH_SHORT).show();

            if (response.length()==17){


        }

    }*/

    }
}
