package com.example.mati.examen2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


    private Destino[] destinos = new Destino[]{
            new Destino("Zona A", "Asia/Oceania", 30),
            new Destino("Zona B", "America/Africa", 20),
            new Destino("Zona C", "Europa", 10)};


    RadioButton tarnormal, tarurgente;
    static double tarifa;
    int peso = 0;
    double precio;
    String zona, continente, resultado, clase, decoracion;
    CheckBox tarjeta, regalo;

    //ADAPTADOR SPINNER
    class AdaptadorDestino extends ArrayAdapter<Destino> {

        Activity context;

       public AdaptadorDestino(Activity context) {
            super(context, R.layout.resultado, destinos);
            this.context = context;
        }

        public View getDropDownView ( int position, View convertView, ViewGroup parent){
            return getView (position, convertView, parent);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.destinos, null);
            // ZONA + CONTINENTE + PRECIO
            final TextView lblZona = (TextView)item.findViewById(R.id.TxtZona);
            lblZona.setText(destinos[position].getZona());

            final TextView lblContinente = (TextView)item.findViewById(R.id.TxtContinente);
            lblContinente.setText(destinos[position].getContinente());


            final TextView lblPrecio = (TextView)item.findViewById(R.id.TxtPrecio);
            lblPrecio.setText(""+destinos[position].getPrecio());


            return(item);
        }
    }

    //PANTALLA PPAL
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INSERTO SPINNER
        final Spinner spDestinos = (Spinner)findViewById(R.id.destinos);

        AdaptadorDestino adaptador = new AdaptadorDestino(this);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spDestinos.setAdapter(adaptador);

        spDestinos.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        zona = destinos[position].getZona();
                        continente = destinos[position].getContinente();
                        precio = (destinos[position].getPrecio());
                    }
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

        final Button btnDib = (Button)findViewById(R.id.BotonDibujar);

        final Button btnCalc = (Button)findViewById(R.id.BotonCalcular);
        tarurgente=(RadioButton)findViewById(R.id.tarurgente);
        tarnormal=(RadioButton)findViewById(R.id.tarnormal);
        tarjeta = (CheckBox) findViewById(R.id.checktarjeta);
        regalo = (CheckBox) findViewById(R.id.checkregal);

        //final RadioGroup radiog = (RadioGroup)findViewById(R.id.radiogroup1);
        final EditText txtpeso = (EditText)findViewById(R.id.textpeso);

        //BOTON DIBUJAR
        btnDib.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent= new Intent(MainActivity.this, Dibujar.class);
                startActivity(miIntent);
            }
        });



        //BOTON CALCULAR
        btnCalc.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (txtpeso.getText().toString().equals("")){
                    Toast.makeText( MainActivity.this, "El campo 'peso' no puede estar vacio", Toast.LENGTH_SHORT).show();

                }else {

                    String pes = txtpeso.getText().toString();
                    peso = Integer.parseInt(pes);

                    if (peso <= 5) {
                        tarifa = precio + (peso * 1);
                    } else {
                        if (peso >= 6 && peso <= 10) {
                            tarifa = precio + (peso * 1.5);
                        } else {
                            if (peso > 10) {
                                tarifa = precio + (peso * 2);
                            }
                        }
                    }
                    //Caja regalo
                    if (regalo.isChecked() && !(tarjeta.isChecked())) {
                        decoracion = "Con caja regalo";
                    } else {
                        //Dedicada
                        if (tarjeta.isChecked() && !(regalo.isChecked())) {
                            decoracion = "Con tarjeta dedicada";
                        } else {
                            //AMBOS marcados
                            if (regalo.isChecked() && tarjeta.isChecked()) {
                                decoracion = " Con caja regalo y dedicatoria";
                            } else {
                                //Nada marcado
                                decoracion = "Sin decoracion";
                            }
                        }
                    }

                    //URGENTE = 30 % extra
                    if (tarurgente.isChecked()) {
                        tarifa = tarifa + (tarifa * 0.3);
                        clase = "urgente";
                    } else {
                        clase = "normal";
                    }


                    resultado = ("Zona: " + zona + " (" + continente + ") \nTarifa: " + clase + "\nPeso: " + peso +
                            " Kg" + "\n\nDecoracion: " + decoracion + "\nCOSTE FINAL: " + tarifa + " €");


                    Intent intent = new Intent(MainActivity.this, Resultado.class);
                    Bundle b = new Bundle();


                    b.putString("Resultado", resultado);
                    b.putString("zona", zona);

                    b.putDouble("total", tarifa);
                    // b.putDouble("Precio", tarifa);

                    intent.putExtras(b);


                    startActivity(intent);
                }
            }
        });

    }

    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        switch (item.getItemId()) {
            case R.id.Menu1:
                Toast.makeText( MainActivity.this, "Insertado en BD", Toast.LENGTH_SHORT).show();

            case R.id.Menu2:

               Intent miIntent= new Intent(MainActivity.this, Dibujar.class);
               startActivity(miIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}



