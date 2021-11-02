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
    EditText editTxt_BuscarId,editTxt_Comentario,editTxt_Id,editTxt_Marca,editTxt_Modelo,editTxt_Ram,editTxt_So,editTxt_Rut,editTxt_Estado,editTxt_Req;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        editTxt_BuscarId=findViewById(R.id.editTxt_BuscarId);
        editTxt_Id=findViewById(R.id.editTxt_Id);
        editTxt_Marca=findViewById(R.id.editTxt_Marca);
        editTxt_Modelo=findViewById(R.id.editTxt_Modelo);
        editTxt_Ram=findViewById(R.id.editTxt_Ram);
        editTxt_So=findViewById(R.id.editTxt_So);
        editTxt_Rut=findViewById(R.id.editTxt_Rut);
        editTxt_Req=findViewById(R.id.editTxt_Req);
        editTxt_Estado=findViewById(R.id.editTxt_Estado);
        editTxt_Comentario=findViewById(R.id.editTxt_Comentario);
    }
    public void RegistroEquipo(View view){
        GestorBD gestor = new GestorBD(this, "equipo", null, 1);
        SQLiteDatabase db = gestor.getWritableDatabase();
        String id = editTxt_Id.getText().toString();
        String marca = editTxt_Marca.getText().toString();
        String modelo = editTxt_Modelo.getText().toString();
        String ram = editTxt_Ram.getText().toString();
        String so = editTxt_So.getText().toString();
        String rut = editTxt_Rut.getText().toString();
        String estado = editTxt_Estado.getText().toString();
        String req = editTxt_Req.getText().toString();
        String comentario =editTxt_Comentario.getText().toString();

        if (!id.isEmpty() && !marca.isEmpty() && !modelo.isEmpty() && !ram.isEmpty() && !so.isEmpty() && !rut.isEmpty()
                && !estado.isEmpty() && !req.isEmpty()  && !comentario.isEmpty()) {
            ContentValues fila = new ContentValues();
            fila.put("id", id);
            fila.put("marca", marca);
            fila.put("modelo", modelo);
            fila.put("ram", ram);
            fila.put("sistema", so);
            fila.put("rut", rut);
            fila.put("estado", estado);
            fila.put("requerimiento", req);
            fila.put("comentario",comentario);
            db.insert("equipo", null, fila);
            db.close();

            editTxt_Id.setText("");
            editTxt_Marca.setText("");
            editTxt_Modelo.setText("");
            editTxt_Ram.setText("");
            editTxt_So.setText("");
            editTxt_Rut.setText("");
            editTxt_Estado.setText("");
            editTxt_Req.setText("");
            editTxt_Comentario.setText("");

            Toast.makeText(this, "Equipo registrado exitosamente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Complete los campos correctamente", Toast.LENGTH_SHORT).show();
        }

    }

    public void MostrarDatos(View view){
        String id = editTxt_BuscarId.getText().toString();
        if (!id.isEmpty()){
            GestorBD gestor = new GestorBD(this, "equipo", null, 1);
            SQLiteDatabase db = gestor.getWritableDatabase();
            Cursor valid = db.rawQuery("select id from equipo where id=" + id , null);
            if (valid.moveToFirst()){
                Cursor datos = db.rawQuery("select id,marca,modelo,ram,sistema,rut,estado,requerimiento,comentario from equipo where id=" + id , null);
                if (datos.moveToFirst()){
                    editTxt_Id.setText(datos.getString(0));
                    editTxt_Marca.setText(datos.getString(1).toString());
                    editTxt_Modelo.setText(datos.getString(2).toString());
                    editTxt_Ram.setText(datos.getString(3).toString());
                    editTxt_So.setText(datos.getString(4).toString());
                    editTxt_Rut.setText(datos.getString(5).toString());
                    editTxt_Estado.setText(datos.getString(6).toString());
                    editTxt_Req.setText(datos.getString(7).toString());
                    editTxt_Comentario.setText(datos.getString(8).toString());}
                    db.close();

            }else{
                Toast.makeText(this, "Ingrese correctamente la ID", Toast.LENGTH_SHORT).show();
                db.close();
            }
        }else{
            Toast.makeText(this, "Debe ingresar Id", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarOrden(View view){
        String id = editTxt_Id.getText().toString();
        GestorBD gestor = new GestorBD(this, "equipo", null, 1);
        SQLiteDatabase db = gestor.getWritableDatabase();
        Cursor valid = db.rawQuery("select id from equipo where id=" + id , null);
        if (valid.moveToFirst()){
        db.delete("equipo","id="+id,null);
        db.close();
        Toast.makeText(this, "La Orden fue Eliminada Exitosamente", Toast.LENGTH_SHORT).show();
    }else{
        Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show();
    }
    }


    public void update(View view){
        String id = editTxt_Id.getText().toString();
        GestorBD gestor = new GestorBD(this, "equipo", null, 1);
        SQLiteDatabase db = gestor.getWritableDatabase();
        Cursor valid = db.rawQuery("select id from equipo where id=" + id , null);
        if (valid.moveToFirst()) {
            ContentValues fila = new ContentValues();
            String marca = editTxt_Marca.getText().toString();
            String modelo = editTxt_Modelo.getText().toString();
            String ram = editTxt_Ram.getText().toString();
            String so = editTxt_So.getText().toString();
            String rut = editTxt_Rut.getText().toString();
            String estado = editTxt_Estado.getText().toString();
            String req = editTxt_Req.getText().toString();
            String comentario =editTxt_Comentario.getText().toString();
            fila.put("id", id);
            fila.put("marca", marca);
            fila.put("modelo", modelo);
            fila.put("ram", ram);
            fila.put("sistema", so);
            fila.put("rut", rut);
            fila.put("estado", estado);
            fila.put("requerimiento", req);
            fila.put("comentario",comentario);
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