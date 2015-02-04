package com.example.mati.examen2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class Dibujar extends Activity {

    float base, altura,radio, centroHorizontal, centroVertical;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MiDibujo(this));
        radio = 55;
        base = 200;
        altura = 300;
    }


    class MiDibujo extends View {

        public MiDibujo(Context c) {

            super(c);
        }

        protected void onDraw(Canvas lienzo) {

            lienzo.drawColor(Color.WHITE);
            Paint miPincel= new Paint();

            centroHorizontal = lienzo.getWidth()/2;
            centroVertical = lienzo.getHeight()/2;

            miPincel.setStyle(Paint.Style.STROKE);
            miPincel.setColor(Color.BLACK);
            miPincel.setStrokeWidth(7);

            //CABEZA
            lienzo.drawCircle(centroHorizontal, centroVertical/2, radio, miPincel);


            //CUERPO
            lienzo.drawLine(centroHorizontal,
                    (centroVertical/2)+radio,
                   centroHorizontal+(base/2),
                    (centroVertical/4)+radio+altura,
                    miPincel);

            lienzo.drawLine(centroHorizontal-(base/2),
                    (centroVertical/4)+radio+altura,
                    centroHorizontal,
                    (centroVertical/2)+radio,
                    miPincel);


                    //Base triangulo
                    lienzo.drawLine(centroHorizontal+(base/2),
                            (centroVertical/4)+radio+altura,
                            centroHorizontal-(base/2),
                            (centroVertical/4)+radio+altura,
                            miPincel);

            //PIERNA IZQUIERDA
            lienzo.drawLine(centroHorizontal-(base/4),
                    (centroVertical/4)+radio+altura+1,
                    centroHorizontal-(base/4),
                    lienzo.getHeight()- centroVertical/2,
                    miPincel);
            //PIERNA DERECHA
            lienzo.drawLine(centroHorizontal+(base/4),
                    (centroVertical/4)+radio+altura+1,
                    centroHorizontal+(base/4),
                    lienzo.getHeight()- centroVertical/2,
                    miPincel);

            //Copy
            miPincel.setColor(Color.BLUE);
            miPincel.setStyle(Paint.Style.FILL_AND_STROKE);
            miPincel.setTextSize(20);
            miPincel.setStrokeWidth(3);
            lienzo.drawText("CopyRight©: Miguel Angel Peris Iborra", centroHorizontal/4 ,lienzo.getHeight()-10 ,  miPincel);



        }
    }
}
