package com.ejemplo.proyecto.monitoreoluces;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

    private EditText ip, servidor; //IP que viene dada por el usuario
    EditText port; //Puerto por defecto
    TextView texto1;
    int puerto_server;
    TextView textResponse;
    String Msgip;
    String Msgport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio); //XML


        Button conectar = (Button) findViewById(R.id.conexion); //boton conectar
        ip = (EditText) findViewById(R.id.ip_panel); //IP que viene dada por el usuario
        servidor = (EditText) findViewById(R.id.ip_server);
        port = (EditText)findViewById(R.id.port);
        puerto_server= 8080;

        conectar.setOnClickListener(conectarOnClickListener); //Llamada a onClick
    }

    View.OnClickListener conectarOnClickListener = new View.OnClickListener() { //Funcion OnClick

        @Override
        public void onClick(View arg0) {
            String tMsg = "Conexion";
            Msgip = ip.getText().toString(); //IP
            Msgport = port.getText().toString();

            if(Msgip.equals("") || Msgport.equals("")){ //Comprobacion de mensaje a enviar
                Msgip = null;
                Toast.makeText(Inicio.this, "IP o Puerto no escrito", Toast.LENGTH_SHORT).show(); //
            }


            else {

                MyClientTask myClientTask = new MyClientTask(servidor
                        .getText().toString(), puerto_server,
                        tMsg);
                myClientTask.execute();


            }
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
            super.onPostExecute(result);

            Toast.makeText(Inicio.this, response, Toast.LENGTH_SHORT).show();

            if (response.length()==17){
                Intent i = new Intent(Inicio.this, Panel.class);
                // Bundle para enviar los datos a la otra vista
                Bundle datos = new Bundle();

                datos.putString("port", Msgport); // Almacenar IP y puerto en el bundle
                datos.putString("ip", Msgip); // Almacenar IP y puerto en el bundle
                datos.putString("estado0", response );

                i.putExtras(datos); //Almacenar el Bundle en el intent para enviar
                startActivity(i);
            }

        }
    }

}