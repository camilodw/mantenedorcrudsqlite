package com.example.evaluacion3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTxt_BuscarId,editTxt_Id,editTxt_So;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        editTxt_BuscarId    = findViewById( R.id.editTxt_BuscarId );
        editTxt_Id          = findViewById( R.id.editTxt_Id );
        editTxt_So          = findViewById( R.id.editTxt_So );
    }

    public void Store(View view){
        GestorBD gestor     = new GestorBD(this, "equipo", null, 1);
        SQLiteDatabase db   = gestor.getWritableDatabase();
        String id           = editTxt_Id.getText().toString();
        String so           = editTxt_So.getText().toString();

        if (!id.isEmpty() && !so.isEmpty()) {
            ContentValues fila = new ContentValues();
            fila.put("id", id);
            fila.put("sistema", so);
            db.insert("equipo", null, fila);
            db.close();
            editTxt_Id.setText("");
            editTxt_So.setText("");
            Toast.makeText(this, "Equipo registrado exitosamente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Complete los campos correctamente", Toast.LENGTH_SHORT).show();
        }

    }

    public void search(View view){
        String id = editTxt_BuscarId.getText().toString();
        if (!id.isEmpty()){
            GestorBD gestor = new GestorBD(this, "equipo", null, 1);
            SQLiteDatabase db = gestor.getWritableDatabase();
            Cursor valid = db.rawQuery("select id from equipo where id=" + id , null);
            if (valid.moveToFirst()){
                Cursor datos = db.rawQuery("select id,sistema from equipo where id=" + id , null);
                if (datos.moveToFirst()){
                    editTxt_Id.setText(datos.getString(0));
                    editTxt_So.setText(datos.getString(1).toString());
                    db.close();

            }else{
                Toast.makeText(this, "Ingrese correctamente la ID", Toast.LENGTH_SHORT).show();
                db.close();
            }
        }else{
            Toast.makeText(this, "Debe ingresar Id", Toast.LENGTH_SHORT).show();
        }
        }
    }

        public void Delete(View view){
            String id           = editTxt_Id.getText().toString();
            GestorBD gestor     = new GestorBD(this, "equipo", null, 1);
            SQLiteDatabase db   = gestor.getWritableDatabase();
            Cursor valid        = db.rawQuery("select id from equipo where id=" + id , null);
            if (valid.moveToFirst()){
                db.delete("equipo","id="+id,null);
                db.close();
                Toast.makeText(this, "La Orden fue Eliminada Exitosamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show();
            }
    }


    public void update (View view){
        String id = editTxt_Id.getText().toString();
        GestorBD gestor = new GestorBD(this, "equipo", null, 1);
        SQLiteDatabase db = gestor.getWritableDatabase();
        Cursor valid = db.rawQuery("select id from equipo where id=" + id , null);
        if (valid.moveToFirst()) {
            ContentValues fila = new ContentValues();
            String so = editTxt_So.getText().toString();
            fila.put("id", id);
            fila.put("sistema", so);
            db.update("equipo", fila, "id=" + id, null);
            Cursor datos = db.rawQuery("select estado from equipo where id=" + id, null);
            db.close();
            Toast.makeText(this, "Modificado correctamente", Toast.LENGTH_SHORT).show();
        }
        else{
            db.close();
            Toast.makeText(this, "Debe primero buscar el registro para luego poder actualizar", Toast.LENGTH_SHORT).show();
        }
}}