package com.example.jorgesalazar.practica3;

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


public class RegistrarUsuario extends AppCompatActivity {

    String results;
    Button RegistrarEst;
    Button Volver;
    EditText carnet;
    EditText cui;
    EditText nombre;
    EditText apellido;
    EditText direccion;
    EditText telefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        RegistrarEst =(Button)findViewById(R.id.button13);
        Volver = (Button)findViewById(R.id.button14);
        carnet = (EditText)findViewById(R.id.editText2);
        cui = (EditText)findViewById(R.id.editText7);
        nombre = (EditText)findViewById(R.id.editText6);
        apellido = (EditText)findViewById(R.id.editText5);
        direccion = (EditText)findViewById(R.id.editText4);
        telefono = (EditText)findViewById(R.id.editText3);



        RegistrarEst.setOnClickListener(new View.OnClickListener() {
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

            registrarse();
            return null;
        }

        private void registrarse() {
            String NAMESPACE = "http://ws/";
            String URL="http://192.168.1.98:8080/practica4_201404215/wsBiblio?WSDL";
            String METHOD_NAME = "insertEs";
            String SOAP_ACTION =  "http://ws/wsBiblio/";


                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("arg0",carnet.getText().toString());
                request.addProperty("arg1",cui.getText().toString());
                request.addProperty("arg2",nombre.getText().toString());
                request.addProperty("arg3",apellido.getText().toString());
                request.addProperty("arg4",direccion.getText().toString());
                request.addProperty("arg5",telefono.getText().toString());
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




        }//
    }
}
