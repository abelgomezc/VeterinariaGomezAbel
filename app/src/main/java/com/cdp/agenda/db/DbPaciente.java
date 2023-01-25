package com.cdp.agenda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.cdp.agenda.entidades.Paciente;

import java.util.ArrayList;


public class DbPaciente extends DbHelperpaciente {

    Context context;

    public DbPaciente(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarContacto(String nombre, int edad, String raza,String tamano , String datosmedicos) {
     //   txtNombre.getText().toString(),Integer.valueOf(txtedad.getText().toString()),txtraza.getText().toString(),
        //        txttamano.getText().toString(),txtdatosmedicos.getText().toString()
        long id = 0;

        try {
            DbHelperpaciente dbHelper = new DbHelperpaciente(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("raza", raza);
            values.put("edad", edad);
            values.put("tamano",tamano);
            values.put("datosmedicos",datosmedicos);

            id = db.insert(TABLE_PACIENTES, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Paciente> mostrarPacientes() {

        DbHelperpaciente dbHelper = new DbHelperpaciente(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Paciente> listapacientes = new ArrayList<>();
        Paciente paciente;
        Cursor cursorpacientes;

        cursorpacientes = db.rawQuery("SELECT * FROM " + TABLE_PACIENTES + " ORDER BY nombre ASC", null);

        if (cursorpacientes.moveToFirst()) {
            do {
                paciente = new Paciente();

                paciente.setCondigo(cursorpacientes.getInt(0));
                paciente.setRaza(cursorpacientes.getString(1));
                paciente.setEdad(cursorpacientes.getInt(2));
                paciente.setNombre(cursorpacientes.getString(3));


                paciente.setTamano(cursorpacientes.getString(4));
                paciente.setDatosmeditos(cursorpacientes.getString(5));
                listapacientes.add(paciente);
            } while (cursorpacientes.moveToNext());
        }

        cursorpacientes.close();

        return listapacientes;
    }

    public Paciente verPaciente(int id) {

        DbHelperpaciente dbHelper = new DbHelperpaciente(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Paciente paciente = null;
        Cursor cursorPacientes;

        cursorPacientes = db.rawQuery("SELECT * FROM " + TABLE_PACIENTES + " WHERE codigo = " + id + " LIMIT 1", null);

        if (cursorPacientes.moveToFirst()) {
            paciente = new Paciente();

            paciente.setCondigo(cursorPacientes.getInt(0));
            paciente.setRaza(cursorPacientes.getString(1));
            paciente.setEdad(cursorPacientes.getInt(2));
            paciente.setNombre(cursorPacientes.getString(3));


            paciente.setTamano(cursorPacientes.getString(4));
            paciente.setDatosmeditos(cursorPacientes.getString(5));

        }

        cursorPacientes.close();

        return paciente;
    }

    public boolean editarpaciente(int id, String raza, int edad, String nombre,String tamano , String datosmedicos) {

        boolean correcto = false;

        DbHelperpaciente dbHelper = new DbHelperpaciente(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_PACIENTES + " SET raza = '" + raza + "', edad = " + edad + ", nombre = '" + nombre + "',tamano ='"+tamano+"',datosmedicos='"+datosmedicos+"' WHERE codigo='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarPaciente(int id) {

        boolean correcto = false;

        DbHelperpaciente dbHelper = new DbHelperpaciente(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_PACIENTES + " WHERE codigo = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
