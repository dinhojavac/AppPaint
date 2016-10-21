package com.example.gcatech.paint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton colorNegro,colorBlanco,colorAzul,colorRojo,colorVerde;
    //enlazar la clase lienzo
    Lienzo lienzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar=(Toolbar)findViewById(R.id.Toolbar);
        //setSupportActionBar(toolbar);

        colorNegro=(ImageButton)findViewById(R.id.ColorNegro);
        colorBlanco=(ImageButton)findViewById(R.id.ColorBlanco);
        colorAzul=(ImageButton)findViewById(R.id.ColorAzul);
        colorRojo=(ImageButton)findViewById(R.id.ColorRojo);
        colorVerde=(ImageButton)findViewById(R.id.ColorVerde);

        //llamada a la escucha
        colorNegro.setOnClickListener(this);
        colorBlanco.setOnClickListener(this);
        colorAzul.setOnClickListener(this);
        colorRojo.setOnClickListener(this);
        colorVerde.setOnClickListener(this);

        lienzo=(Lienzo)findViewById(R.id.lienzo);

    }

    @Override
    public void onClick(View v) {
        //mejor forma de utilizar un string
        String color;
        //mas no asi
        //String color="";


        switch (v.getId()){
            case R.id.ColorNegro:
                //coloca el color que corresponde
                //llama a getTag() que es el trae el color y lo convierte a string por medio de toString()
                color=v.getTag().toString();
                lienzo.setColor(color);
                //new Dialogo().show(getFragmentManager(),"Superman en Supertangas");

                break;

            case R.id.ColorBlanco:
                color=v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.ColorAzul:
                color=v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.ColorRojo:
                color=v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.ColorVerde:
                color=v.getTag().toString();
                lienzo.setColor(color);
                break;

            default:

                break;

        }
    }
}
