package com.ejemplo.proyecto.monitoreoluces;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Inicio extends ActionBarActivity {

    private EditText ip;    //IP que viene dada por el usuario
    int port= 8080;         //Puerto por defecto
    TextView textResponse;  //Respuesta desde el servidor




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio); //XML

        textResponse = (TextView) findViewById(R.id.response); //Donde se muestra la respuesta del servidor
        Button conectar = (Button) findViewById(R.id.conexion); //boton conectar
        ip = (EditText) findViewById(R.id.ip);                  //IP que viene dada por el usuario


        conectar.setOnClickListener(conectarOnClickListener); //Llamada a onClick
    }

    View.OnClickListener conectarOnClickListener = new View.OnClickListener() { //Funcion OnClick

               @Override
               public void onClick(View arg0) {
                String tMsg = ip.getText().toString();      //IP
                if(tMsg.equals("")){                        //Comprobacion de mensaje a enviar
                    tMsg = null;
                    Toast.makeText(Inicio.this, "IP no escrita", Toast.LENGTH_SHORT).show(); //
                }


                MyClientTask myClientTask = new MyClientTask(ip.getText().toString(),
                        port,
                        tMsg);
                myClientTask.execute();



            }
        };

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

                    if(msgToServer != null){
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
            @Override
            protected void onPostExecute(Void result) {
                textResponse.setText(response);
                super.onPostExecute(result);

                Toast.makeText(Inicio.this, response, Toast.LENGTH_SHORT).show();

            if (response.length()==17){
             Intent i = new Intent(Inicio.this, Panel.class);
                // Bundle para enviar los datos a la otra vista
                Bundle datos = new Bundle();

               i.putExtra("datos",response);
               i.putExtra("ip", ip.getText().toString());
               startActivity(i);}

            }

        }

}
