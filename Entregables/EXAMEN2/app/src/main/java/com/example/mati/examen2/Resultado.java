package com.example.mati.examen2;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by mati on 29/01/15.
 */
public class Resultado extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);

        final TextView resultado = (TextView)findViewById(R.id.Resultado);
        ImageView imagen = (ImageView) findViewById(R.id.imagen);
        final Button btnIns = (Button)findViewById(R.id.botonInsertar);
        final Button btnLeer = (Button)findViewById(R.id.botonLeer);


       final Bundle b = getIntent().getExtras();

        final String zona = b.getString("zona");
        resultado.setText(b.getString("Resultado"));

        if (zona.equals("Zona A")){
            imagen.setImageResource(R.drawable.asia_oceania);
        }

        if (zona.equals("Zona B")){
            imagen.setImageResource(R.drawable.america_africa);
        }

        if (zona.equals("Zona C")){
            imagen.setImageResource(R.drawable.europa);
        }


//Insertar en BDD
        btnIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // double total = b.getDouble("total");

                double total1 = b.getDouble("total");

                String total = Double.toString(total1);
                SQLiteDatabase db;

                //INSERTO EN BD

                ClientesSQLiteHelper cliBDh = new ClientesSQLiteHelper(Resultado.this, "Destinos", null, 3);
                db = cliBDh.getWritableDatabase();

                //En caso de abrir de forma correcta la base de datos
                if (db != null) {

                    db.execSQL("INSERT INTO Destinos ( zona , coste) " +
                            "VALUES ('" + zona + "', '" + total + "')");

                    Toast.makeText(Resultado.this, "Insertado: "+zona+" / "+total+" €", Toast.LENGTH_SHORT).show();
                    db.close();
                }
            }
        });

        //Leer en TOASt
        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db;
                ClientesSQLiteHelper cliBDh = new ClientesSQLiteHelper(Resultado.this, "Destinos", null, 3);
                db = cliBDh.getWritableDatabase();

                String[] campos = new String[] {"zona", "coste"};

                Cursor c = db.query("Destinos", campos, null, null, null, null, null);
                int i=0;
                if (c.moveToFirst()) {

                    do {
                        String zonaDes = c.getString(0);
                        String costeDes = c.getString(1);

                        String tostring = "Registro " + i +"\n"+ zonaDes +"\n"+costeDes;

                        Toast.makeText(Resultado.this, tostring, Toast.LENGTH_SHORT).show();
                        i++;

                    } while (c.moveToNext());
                }

                db.close();
            }
        });

    }

}
