package com.example.basededatospractica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        ed4 = findViewById(R.id.ed4);
    }

    public void altas(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nControl = ed1.getText().toString();
        String nombre = ed2.getText().toString();
        String semestre = ed3.getText().toString();
        String carrera = ed4.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("nControl", nControl);
        registro.put("nombre", nombre);
        registro.put("semestre", semestre);
        registro.put("carrera", carrera);
        bd.insert("alumno", null, registro);
        bd.close();
        this.limpia();
        Toast.makeText(this, "Datos de usuario cargados", Toast.LENGTH_SHORT).show();
    }

    public void limpia() {
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
    }

    public void limpiar(View view) {
        this.limpia();
    }

    public void consulta(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nControl = ed1.getText().toString();

        Cursor fila = bd.rawQuery(" select nombre , semestre , carrera from alumno where nControl = " + nControl , null );
        //Cursor fila = bd.rawQuery("select nombre, semestre, carrera from usuario ", null);
        if (fila.moveToFirst()) {
            ed2.setText(fila.getString(0));
            ed3.setText(fila.getString(1));
            ed4.setText(fila.getString(2));
        } else
            Toast.makeText(this, "No existe ningun usuario con ese numero de control", Toast.LENGTH_SHORT).show();

        bd.close();
    }

    public void baja(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nControl = ed1.getText().toString();

        int cant = bd.delete(" usuario ", " nControl = " + nControl , null);
        bd.close();
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
        if (cant == 1) {
            Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el usuario", Toast.LENGTH_SHORT).show();
        }

    }

    public void modificacion(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this , "administracion" , null , 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nControl = ed1.getText().toString();
        String nombre = ed2.getText().toString();
        String semestre = ed3.getText().toString();
        String carrera = ed4.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("semestre", semestre);
        registro.put("carrera", carrera);
        int cant = bd.update("alumno" , registro , "nControl=" + nControl , null);
        bd.close();
        if (cant == 1) {
            Toast.makeText(this , "Datos modificados con exito" , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this , "No existe usuario" , Toast.LENGTH_SHORT).show();
        }

    }
}