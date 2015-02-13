package com.ejemplo.proyecto.monitoreoluces;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Panel extends ActionBarActivity {

    ToggleButton boton1, boton2, boton3, boton4, boton5, boton6, boton7, boton8;
    ChatClientThread chatClientThread = null;
    TextView chatMsg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

 /* obtener la info enviada por el bundle*/
        Bundle bundle = this.getIntent().getExtras();
        String ip = bundle.getString("ip"); //Recibir la ip enviada de la otra vista
        String port = bundle.getString("port"); //Recibir el puerto enviado de la otra vista
        chatMsg = (TextView) findViewById(R.id.chatmsg);
        String msgLog = "";

        //Definicion del boton 1
        boton1 = (ToggleButton) findViewById(R.id.boton1);
        boton2 = (ToggleButton) findViewById(R.id.boton2);
        boton3 = (ToggleButton) findViewById(R.id.boton3);
        boton4 = (ToggleButton) findViewById(R.id.boton4);
        boton5 = (ToggleButton) findViewById(R.id.boton5);
        boton6 = (ToggleButton) findViewById(R.id.boton6);
        boton7 = (ToggleButton) findViewById(R.id.boton7);
        boton8 = (ToggleButton) findViewById(R.id.boton8);

        chatClientThread = new ChatClientThread( ip, Integer.parseInt(port));
        chatClientThread.start();
    }

    private class ChatClientThread extends Thread {

        String dstAddress;
        int dstPort;
        public String msgLog = "";
        String msgToSend = "";
        boolean goOut = false;

        ChatClientThread(String address, int port) {
            dstAddress = address;
            dstPort = port;
        }

        @Override
        public void run() {
            Socket socket = null;
            DataOutputStream dataOutputStream = null;
            DataInputStream dataInputStream = null;

            try {
                socket = new Socket(dstAddress, dstPort);
                dataOutputStream = new DataOutputStream(
                        socket.getOutputStream());
                dataInputStream = new DataInputStream(socket.getInputStream());
                BufferedReader d= new BufferedReader(new InputStreamReader(socket.getInputStream()));

                dataOutputStream.writeBytes("*002001100110000#"); // envia el nombre de usuario
                dataOutputStream.flush();

                while (!goOut) {


                    if (dataInputStream.available() > 0) {
                        //Toast.makeText(Panel.this, "prueba1", Toast.LENGTH_LONG).show();

                        Byte b =dataInputStream.readByte();

                        String value = new String(b, "UTF-8");

                        String s = new String(b, "US-ASCII");

                        msgLog =b;

                            Panel.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Toast.makeText(Panel.this, "despues de readline", Toast.LENGTH_LONG).show();
                            }

                        });


                        Panel.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                chatMsg.setText(msgLog);
                            }
                        });
                    }

                    if(!msgToSend.equals("")){
                        dataOutputStream.writeBytes(msgToSend);
                        dataOutputStream.flush();
                        msgToSend = "";
                    }
                }

            } catch (UnknownHostException e) {
                e.printStackTrace();
                final String eString = e.toString();
                Panel.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(Panel.this, eString, Toast.LENGTH_LONG).show();
                    }

                });
            } catch (IOException e) {
                e.printStackTrace();
                final String eString = e.toString();
                Panel.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(Panel.this, eString, Toast.LENGTH_LONG).show();
                    }

                });
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
        }

        private void sendMsg(String msg){
            msgToSend = msg;
        }

        private void disconnect(){
            goOut = true;
        }
    }
}