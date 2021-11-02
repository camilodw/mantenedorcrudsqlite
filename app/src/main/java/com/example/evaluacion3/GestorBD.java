package com.example.evaluacion3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class GestorBD extends SQLiteOpenHelper {

String tabla_equipo = "CREATE TABLE equipo(id int primary key,marca text,modelo text,ram text,sistema text,rut text,estado text,requerimiento text,comentario text)";
    public GestorBD(@Nullable Context context, @Nullable String name,
                    @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL(tabla_equipo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}