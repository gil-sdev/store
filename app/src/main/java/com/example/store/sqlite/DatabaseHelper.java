package com.example.store.sqlite;

import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper {
    public static final String DATABASE_NAME = "productos_prueba.db";
    public static final int DATABASE_VERSION = 1;
    // A constructor that calls the superclass constructor.
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

// It creates a table called contact with three columns: id, telephone, and name.
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS ropa (id INTEGER PRIMARY KEY AUTOINCREMENT, codigo TEXT NOT NULL, nombre TEXT NOT NULL,talla TEXT NOT NULL,precio REAL NOT NULL,stock INTEGER NOT NULL)");
    }


// A method that is called when the database is upgraded.
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ropa");
        onCreate(db);
    }
}
