package com.example.basededatospractica;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory , int version ) {
        super( context , name , factory , version );
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(" create table alumno ( nControl integer primary key , nombre text , semestre integer , carrera text ) " );

    }

    @Override
    public void onUpgrade(SQLiteDatabase bd , int version1 , int version2 ) {
        bd.execSQL(" drop table if exists alumno ");
        bd.execSQL(" create table alumno ( nControl integer primary key , nombre text , semestre integer , carrera text ) " );

    }
}
