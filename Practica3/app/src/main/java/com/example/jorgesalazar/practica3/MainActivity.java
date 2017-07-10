package com.example.jorgesalazar.practica3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnRegEs;
    Button btnRegLi;
    Button btnEnvResta;
    Button btnMostrar;
    Button btnPrestar;
    Button btnSalir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegEs =(Button)findViewById(R.id.button22);
        btnRegLi =(Button)findViewById(R.id.button23);
        btnEnvResta =(Button)findViewById(R.id.button24);
        btnMostrar =(Button)findViewById(R.id.button25);
        btnPrestar =(Button)findViewById(R.id.button26);
        btnSalir = (Button)findViewById(R.id.button27);


        btnRegEs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, RegistrarUsuario.class);
                startActivity(intent1);
            }
        });

        btnRegLi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, RegistrarLibro.class);
                startActivity(intent2);
            }
        });

        btnEnvResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(MainActivity.this, EnviarRestauracion.class);
                startActivity(intent3);
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(MainActivity.this, MostrarLibros.class);
                startActivity(intent4);
            }
        });

        btnPrestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(MainActivity.this, PrestarLibros.class);
                startActivity(intent5);
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
