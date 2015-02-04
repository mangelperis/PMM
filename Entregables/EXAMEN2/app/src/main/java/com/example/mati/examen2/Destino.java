package com.example.mati.examen2;

/**
 * Created by mati on 29/01/15.
 */
public class Destino {

        private String zona;
        private String continente;
        private double precio;


        public Destino(String zona, String continente, double precio){
            this.zona = zona;
            this.continente = continente;
            this.precio = precio;
        }

        public String getZona(){
            return zona;
        }

        public String getContinente(){
            return continente;
        }

        public double getPrecio(){
            return precio;
        }




}
