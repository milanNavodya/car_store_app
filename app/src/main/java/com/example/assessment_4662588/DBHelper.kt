package com.example.assessment_4662588

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(garagedb: SQLiteDatabase) {
        /*
        Create a database.
        Then create a table with columns and define column data types
         */
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + BRAND + " TEXT, " +
                MODEL + " TEXT," +
                PRICE + " TEXT" + ")")
        garagedb.execSQL(query) // Execute query
    }

    override fun onUpgrade(garagedb: SQLiteDatabase, p1: Int, p2: Int) {
        // Check if table already created
        garagedb.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(garagedb)
    }

    fun addCar(brand: String, model : String, price : String ){

        val content = ContentValues()

        // Add values to set
        content.put(BRAND, brand)
        content.put(MODEL, model)
        content.put(PRICE, price)

        // Crete a writable variable to database to insert data
        val garagedb = this.writableDatabase

        garagedb.insert(TABLE_NAME, null, content)

        garagedb.close()
    }

    fun getCarPRice(brand: String, model: String): Cursor? {

        // Crete a readable variable to read data in database
        val garagedb = this.readableDatabase

        return garagedb.rawQuery("SELECT price FROM $TABLE_NAME WHERE BRAND = '$brand' AND $MODEL = '$model'", null)

    }

    companion object{
        // define a variable for db name
        private val DATABASE_NAME = "garage"

        // define a variable for db version
        private val DATABASE_VERSION = 1

        // variable for table name
        val TABLE_NAME = "car"

        // variables for table columns
        val BRAND = "brand"
        val MODEL = "model"
        val PRICE = "price"
    }
}
