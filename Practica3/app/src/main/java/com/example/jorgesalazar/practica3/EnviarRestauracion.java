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

public class EnviarRestauracion extends AppCompatActivity {


    Button enviarRest;
    Button Volver1;
    EditText iderestauracion;
    EditText fecha;
    EditText idlib;
    String results;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_restauracion);

        enviarRest = (Button)findViewById(R.id.button3);
        Volver1 = (Button)findViewById(R.id.button4);
        iderestauracion= (EditText)findViewById(R.id.editText13);
        fecha = (EditText)findViewById(R.id.editText14);
        idlib = (EditText)findViewById(R.id.editText15);


        enviarRest.setOnClickListener(new View.OnClickListener() {
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

            enviarRestauracion();
            return null;
        }

        private void enviarRestauracion() {

            String NAMESPACE = "http://ws/";
            String URL="http://192.168.1.98:8080/practica4_201404215/wsBiblio?WSDL";
            String METHOD_NAME = "enviarRes";
            String SOAP_ACTION =  "http://ws/wsBiblio/";


            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("arg0",iderestauracion.getText().toString());
            request.addProperty("arg1",fecha.getText().toString());
            request.addProperty("arg2",idlib.getText().toString());


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
