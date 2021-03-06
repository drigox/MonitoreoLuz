package com.ejemplo.proyecto.monitoreoluces;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class CerrarApp extends ActionBarActivity {

 /* obtener la info enviada por el bundle*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_cerrar);


        Bundle bundle = this.getIntent().getExtras();
        String ip= bundle.getString("ip");
        String ip_server = bundle.getString("ip_server");
        String port = bundle.getString("port");
        String tMsg = ip + "+" + port + "+" + bundle.getString("cadenafinal");
        int port_server =8080;


        MyClientTask myClientTask = new MyClientTask(ip_server
                , port_server, tMsg);
        myClientTask.execute();
        //finish();

        //for(int i=0; i<1000; i++)

        //System.exit(0);

    }

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

                if (msgToServer != null) {
                    dataOutputStream.writeUTF(msgToServer);
                    CerrarApp.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                           System.exit(0);



                        }
                    });
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
            System.exit(0);
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);

            //System.exit(0);
           //finish();


        }

    }
}