package com.cdp.agenda.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelperpaciente extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "veterinaria.db";
    public static final String TABLE_PACIENTES = "t_pacientes";

    public DbHelperpaciente(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PACIENTES + "(" +
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "raza TEXT ," +
                "edad INTEGER ," +
                "nombre TEXT ,"+
                "tamano TEXT ,"+
                "datosmedicos TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PACIENTES);
        onCreate(sqLiteDatabase);

    }
}
