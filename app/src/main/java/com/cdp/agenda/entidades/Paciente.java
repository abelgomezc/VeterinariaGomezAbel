package com.cdp.agenda.entidades;

public class Paciente {

   private int condigo;
   private String raza;
   private int edad;
   private  String nombre;
   private  String tamano;
   private String datosmeditos;




    public int getCondigo() {
        return condigo;
    }

    public void setCondigo(int condigo) {
        this.condigo = condigo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getDatosmeditos() {
        return datosmeditos;
    }

    public void setDatosmeditos(String datosmeditos) {
        this.datosmeditos = datosmeditos;
    }
}
