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
    char[] parseo = new char[17];
    Button buttonDisconnect;

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


        parseo = new char[]{'*', '0', '0', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#'};
        //Definicion del boton 1
        boton1 = (ToggleButton) findViewById(R.id.boton1);
        boton2 = (ToggleButton) findViewById(R.id.boton2);
        boton3 = (ToggleButton) findViewById(R.id.boton3);
        boton4 = (ToggleButton) findViewById(R.id.boton4);
        boton5 = (ToggleButton) findViewById(R.id.boton5);
        boton6 = (ToggleButton) findViewById(R.id.boton6);
        boton7 = (ToggleButton) findViewById(R.id.boton7);
        boton8 = (ToggleButton) findViewById(R.id.boton8);
        buttonDisconnect = (Button) findViewById(R.id.disconnect);


        chatClientThread = new ChatClientThread( ip, Integer.parseInt(port));
        chatClientThread.start();

        boton1.setOnClickListener(boton1OnClickListener);
        boton2.setOnClickListener(boton2OnClickListener);
        boton3.setOnClickListener(boton3OnClickListener);
        boton4.setOnClickListener(boton4OnClickListener);
        boton5.setOnClickListener(boton5OnClickListener);
        boton6.setOnClickListener(boton6OnClickListener);
        boton7.setOnClickListener(boton7OnClickListener);
        boton8.setOnClickListener(boton8OnClickListener);

        buttonDisconnect.setOnClickListener(buttonDisconnectOnClickListener);

    }

    View.OnClickListener buttonDisconnectOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if(chatClientThread==null){
                return;
            }
            chatClientThread.disconnect();
            finish();
        }

    };

    View.OnClickListener boton1OnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int numero2 = 1;
            String snumero2 = String.valueOf(numero2);
            String bitcomparar = String.valueOf(parseo[4]);

            if (bitcomparar.equals(snumero2)) { // revisa estado boton =1
                char numeroaux = '0';
                parseo[4] = numeroaux; //Si es 1 lo cambia a 0
                chatClientThread.sendMsg(String.valueOf(parseo));           //mensaje que se envia con el bit cambiado
            }

            else {
                char numeroaux = '1';
                parseo[4] = numeroaux; //Caso contario 0->1
                chatClientThread.sendMsg(String.valueOf(parseo));   //mensaje
            }

        }
    };


    View.OnClickListener boton2OnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int numero2 = 1;
            String snumero2 = String.valueOf(numero2);
            String bitcomparar = String.valueOf(parseo[5]);

            if (bitcomparar.equals(snumero2)) { // revisa estado boton =1
                char numeroaux = '0';
                parseo[5] = numeroaux; //Si es 1 lo cambia a 0

                chatClientThread.sendMsg(String.valueOf(parseo));           //mensaje que se envia con el bit cambiado
            }

            else {
                char numeroaux = '1';
                parseo[5] = numeroaux; //Caso contario 0->1

                chatClientThread.sendMsg(String.valueOf(parseo));   //mensaje
            }
        }
    };

    View.OnClickListener boton3OnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int numero2 = 1;
            String snumero2 = String.valueOf(numero2);
            String bitcomparar = String.valueOf(parseo[6]);

            if (bitcomparar.equals(snumero2)) { // revisa estado boton =1
                char numeroaux = '0';
                parseo[6] = numeroaux; //Si es 1 lo cambia a 0

                chatClientThread.sendMsg(String.valueOf(parseo));           //mensaje que se envia con el bit cambiado
            }

            else {
                char numeroaux = '1';
                parseo[6] = numeroaux; //Caso contario 0->1


                chatClientThread.sendMsg(String.valueOf(parseo));   //mensaje
            }
        }
    };

    View.OnClickListener boton4OnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int numero2 = 1;
            String snumero2 = String.valueOf(numero2);
            String bitcomparar = String.valueOf(parseo[7]);

            if (bitcomparar.equals(snumero2)) { // revisa estado boton =1
                char numeroaux = '0';
                parseo[7] = numeroaux; //Si es 1 lo cambia a 0

                chatClientThread.sendMsg(String.valueOf(parseo));           //mensaje que se envia con el bit cambiado
            }

            else {
                char numeroaux = '1';
                parseo[7] = numeroaux; //Caso contario 0->1


                chatClientThread.sendMsg(String.valueOf(parseo));   //mensaje
            }

        }
    };

    View.OnClickListener boton5OnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int numero2 = 1;
            String snumero2 = String.valueOf(numero2);
            String bitcomparar = String.valueOf(parseo[8]);

            if (bitcomparar.equals(snumero2)) { // revisa estado boton =1
                char numeroaux = '0';
                parseo[8] = numeroaux; //Si es 1 lo cambia a 0

                chatClientThread.sendMsg(String.valueOf(parseo));           //mensaje que se envia con el bit cambiado
            }

            else {
                char numeroaux = '1';
                parseo[8] = numeroaux; //Caso contario 0->1


                chatClientThread.sendMsg(String.valueOf(parseo));   //mensaje
            }

        }
    };

    View.OnClickListener boton6OnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int numero2 = 1;
            String snumero2 = String.valueOf(numero2);
            String bitcomparar = String.valueOf(parseo[9]);

            if (bitcomparar.equals(snumero2)) { // revisa estado boton =1
                char numeroaux = '0';
                parseo[9] = numeroaux; //Si es 1 lo cambia a 0

                chatClientThread.sendMsg(String.valueOf(parseo));           //mensaje que se envia con el bit cambiado
            }

            else {
                char numeroaux = '1';
                parseo[9] = numeroaux; //Caso contario 0->1


                chatClientThread.sendMsg(String.valueOf(parseo));   //mensaje
            }

        }
    };

    View.OnClickListener boton7OnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int numero2 = 1;
            String snumero2 = String.valueOf(numero2);
            String bitcomparar = String.valueOf(parseo[10]);

            if (bitcomparar.equals(snumero2)) { // revisa estado boton =1
                char numeroaux = '0';
                parseo[10] = numeroaux; //Si es 1 lo cambia a 0

                chatClientThread.sendMsg(String.valueOf(parseo));           //mensaje que se envia con el bit cambiado
            }

            else {
                char numeroaux = '1';
                parseo[10] = numeroaux; //Caso contario 0->1


                chatClientThread.sendMsg(String.valueOf(parseo));   //mensaje
            }

        }
    };

    View.OnClickListener boton8OnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int numero2 = 1;
            String snumero2 = String.valueOf(numero2);
            String bitcomparar = String.valueOf(parseo[11]);

            if (bitcomparar.equals(snumero2)) { // revisa estado boton =1
                char numeroaux = '0';
                parseo[11] = numeroaux; //Si es 1 lo cambia a 0

                chatClientThread.sendMsg(String.valueOf(parseo));           //mensaje que se envia con el bit cambiado
            }

            else {
                char numeroaux = '1';
                parseo[11] = numeroaux; //Caso contario 0->1


                chatClientThread.sendMsg(String.valueOf(parseo));   //mensaje
            }

        }
    };

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

                dataOutputStream.writeBytes("*002000000000000#"); // envia el nombre de usuario
                dataOutputStream.flush();

                while (!goOut) {

                    String aux = "";
                    aux = msgLog;



                /*    if (dataInputStream.available() > 0) {
                        //Toast.makeText(Panel.this, "prueba1", Toast.LENGTH_LONG).show();


                        msgLog += dataInputStream.readByte();

                        final char[] auxparseo = new char[17]; // char para parseo

                        Log.i("tagql", "antes del FOOOOOOOOR");
                        Log.d("tagql", msgLog);

                        if (aux.length() >34) {
                            aux="";
                            msgLog="";


                        }

                        if (aux.length() == 34) {


                            for (int i = 0; i < 34; i = i + 2) {

                                if (i == 0) {
                                    auxparseo[0] = '*';

                                } else {
                                    auxparseo[i / 2] = (char) aux.charAt(i + 1); //parseo
                                }

                                String respuesta = String.valueOf(auxparseo[i / 2]); // char paseo a string bitparse
                                String num = "8";
                                if (respuesta.equals(num)) {
                                    auxparseo[i / 2] = '0';
                                }

                                num = "9";
                                if (respuesta.equals(num)) {
                                    auxparseo[i / 2] = '1';
                                }

                                num="5";
                                if (respuesta.equals(num)) {
                                    auxparseo[i / 2] = '#';
                                }

                                num="0";
                                if (respuesta.equals(num)) {
                                    auxparseo[i / 2] = '2';
                                }



                                Log.e("Taggggg", String.valueOf(auxparseo));
                                Log.e("hahaha", String.valueOf(i / 2));

                            }

                            aux="";


                            final String finalAux = String.copyValueOf(auxparseo);
                            Panel.this.runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    chatMsg.setText(finalAux);
                                    msgLog = "";



                                }
                            });
                        }
                    }*/

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
                        //Toast.makeText(Panel.this, eString, Toast.LENGTH_LONG).show();
                        Intent volver = new Intent(Panel.this, Inicio.class);
                        startActivity(volver);
                        finish();
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