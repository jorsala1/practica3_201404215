package com.example.jorgesalazar.practica3;

import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class RegistrarLibro extends AppCompatActivity {

    Button RegistrarLi;
    Button Volver;
    EditText idlibro;
    EditText nombreLibro;
    EditText no_paginas;
    EditText cod_tema;
    EditText cod_autor;

    String results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_libro);
        RegistrarLi = (Button)findViewById(R.id.button);
        Volver = (Button)findViewById(R.id.button2);
        idlibro = (EditText)findViewById(R.id.editText);
        nombreLibro = (EditText)findViewById(R.id.editText8);
        no_paginas = (EditText)findViewById(R.id.editText9);
        cod_tema = (EditText)findViewById(R.id.editText10);
        cod_autor = (EditText)findViewById(R.id.editText11);

        RegistrarLi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncCallWS task = new AsyncCallWS();
                task.execute();

            }
        });

    }

    class AsyncCallWS extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            registrarLibro();
            return null;
        }

        private void registrarLibro() {

            String NAMESPACE = "http://ws/";
            String URL="http://192.168.1.98:8080/practica4_201404215/wsBiblio?WSDL";
            String METHOD_NAME = "insertLib";
            String SOAP_ACTION =  "http://ws/wsBiblio/";


            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("arg0",idlibro.getText().toString());
            request.addProperty("arg1",nombreLibro.getText().toString());
            request.addProperty("arg2",no_paginas.getText().toString());
            request.addProperty("arg3",cod_tema.getText().toString());
            request.addProperty("arg4",cod_autor.getText().toString());

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = false;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            try {
                androidHttpTransport.call(SOAP_ACTION, envelope);
                SoapPrimitive respuesta = (SoapPrimitive) envelope.getResponse();
                results = respuesta.toString();
            }catch(Exception e){
                Log.i("error",e.getMessage());


            }
        }
    }
}
