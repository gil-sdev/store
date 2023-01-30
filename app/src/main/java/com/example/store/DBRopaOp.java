package com.example.store;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.store.sqlite.DatabaseHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.widget.Toast;


public class DBRopaOp {
    Context context;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    // Creating a new instance of the DatabaseHelper class.
    public DBRopaOp(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    /**
     * Open the database for writing.
     */
    public void open() {
        database = dbHelper.getWritableDatabase();
    }
    /**
     * This function closes the database
     */

    public void close() {
        dbHelper.close();
    }

    /**
     * It adds a contact to the database
     */
    public void addRopa(String codigo, String nombre, String imagen, String talla, Double precio, Integer stock) {
        try {
            ContentValues values = new ContentValues();
            values.put("codigo", codigo);
            values.put("nombre", nombre);
            values.put("imagen", imagen);
            values.put("talla", talla);
            values.put("precio", precio);
            values.put("stock", stock);
            database.insert("ropa", null, values);

        }catch (Error error){
            Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * It returns a cursor object that contains all the rows in the contact table
     *
     * @return A cursor object.
     */
    public Cursor getAllRopa() {
        return database.rawQuery("SELECT * FROM contact", null);
    }

}
