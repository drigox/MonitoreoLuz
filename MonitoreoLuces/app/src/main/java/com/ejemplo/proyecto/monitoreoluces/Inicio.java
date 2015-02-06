package com.ejemplo.proyecto.monitoreoluces;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

    private EditText ip;
    int port= 8080;
    TextView textResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        textResponse = (TextView) findViewById(R.id.response);
        Button conectar = (Button) findViewById(R.id.conexion);
        ip = (EditText) findViewById(R.id.ip);


        conectar.setOnClickListener(conectarOnClickListener);
    }

    View.OnClickListener conectarOnClickListener = new View.OnClickListener() {

               @Override
               public void onClick(View arg0) {
                String tMsg = ip.getText().toString();
                if(tMsg.equals("")){
                    tMsg = null;
                    Toast.makeText(Inicio.this, "IP no escrita", Toast.LENGTH_SHORT).show();
                }

                MyClientTask myClientTask = new MyClientTask(ip
                        .getText().toString(), port,
                        tMsg);
                myClientTask.execute();


             /*   Intent i = new Intent(Inicio.this, Panel.class);
                // Bundle para enviar los datos a la otra vista
               datos.putString("datos", ip.getText().toString());
               i.putExtras(datos);
                startActivity(i);
            */
            }
        };

    

        public class MyClientTask extends AsyncTask<Void, Void, Void> {

            String dstAddress;
            int dstPort;
            String response = "";
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
                        dataOutputStream.writeUTF(msgToServer);
                    }

                    response = dataInputStream.readUTF();

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
            }

        }

}
