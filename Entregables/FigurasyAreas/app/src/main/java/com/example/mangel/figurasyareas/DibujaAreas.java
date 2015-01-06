package com.example.mangel.figurasyareas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class DibujaAreas extends Activity {

    float radio, ancho, alto, base, altura;
    String control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MiDibujo(this));

        Bundle  miBundleRecoger = getIntent().getExtras();

        control = miBundleRecoger.getString("Control");

        if(control.equals("circulo")){
            radio = Float.parseFloat(miBundleRecoger.getString("Radio"));
        }

        if(control.equals("rectangulo")){
            alto = Float.parseFloat(miBundleRecoger.getString("Alto"));
            ancho = Float.parseFloat(miBundleRecoger.getString("Ancho"));

        }

        if(control.equals("triangulo")) {
            base = Float.parseFloat(miBundleRecoger.getString("Base"));
            altura = Float.parseFloat(miBundleRecoger.getString("Altura"));
        }

    }

    class MiDibujo extends View {

        public MiDibujo(Context c) {

            super(c);
        }

        protected void onDraw(Canvas lienzo){

            lienzo.drawColor(Color.WHITE);
            Paint miPincel= new Paint();

            miPincel.setStyle(Paint.Style.FILL);
            miPincel.setColor(Color.BLACK);
            miPincel.setStrokeWidth(3);
            //miPincel.setTextSize(90);

            Paint miPincel2 = new Paint();
            miPincel2.setStyle(Paint.Style.STROKE);
            miPincel2.setColor(Color.RED);
            miPincel2.setStrokeWidth(5);
            miPincel2.setTextSize(80);


            if(control.equals("circulo")){
                lienzo.drawCircle(lienzo.getWidth() / 2, lienzo.getHeight() / 2, radio, miPincel);
                lienzo.drawText("Area: "+String.valueOf((radio*radio*Math.PI)), 50, lienzo.getHeight()-50, miPincel2);
            }

            if(control.equals("rectangulo")){
                lienzo.drawRect(((lienzo.getWidth()/2)-(ancho/2)),
                                ((lienzo.getHeight()/2)-(alto/2)),
                                ((lienzo.getWidth()/2)+(ancho/2)),
                                ((lienzo.getHeight()/2)+(alto/2)),
                                miPincel);
                lienzo.drawText("Area: "+String.valueOf((ancho*alto)), 50, lienzo.getHeight()-50, miPincel2);

            }

            if(control.equals("triangulo")) {
                lienzo.drawLine((lienzo.getWidth()/2)-(base/2),
                                 lienzo.getHeight()/2+(altura/2),
                                 lienzo.getWidth()/2,
                                 lienzo.getHeight()/2-(altura/2),
                                 miPincel);

                lienzo.drawLine(lienzo.getWidth()/2,
                                lienzo.getHeight()/2-(altura/2),
                               (lienzo.getWidth()/2)+(base/2),
                                lienzo.getHeight()/2+(altura/2),
                                miPincel);

                lienzo.drawLine((lienzo.getWidth()/2)+(base/2),
                                 lienzo.getHeight()/2+(altura/2),
                                (lienzo.getWidth()/2)-(base/2),
                                 lienzo.getHeight()/2+(altura/2),
                                 miPincel);
                lienzo.drawText("Area: "+String.valueOf((base*altura)/2), 50, lienzo.getHeight()-50, miPincel2);

            }

        }
    }

}
