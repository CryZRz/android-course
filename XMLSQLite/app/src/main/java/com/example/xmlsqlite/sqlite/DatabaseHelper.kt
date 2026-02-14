package com.example.xmlsqlite.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.xmlsqlite.Park

class DatabaseHelper(context: Context):
    SQLiteOpenHelper(
        context,
    Constants.DATABASE_NAME, null,
    Constants.DATABASE_VERSION
            ) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE ${Constants.ENTITY_PARK} (" +
                "${Constants.P_ID} INTEGER PRIMARY KEY AUTOINCREMENT, "+
        "${Constants.P_NAME} VARCHAR(50), " +
                "${Constants.P_FAV} BOOLEAN);"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun insertPark(park: Park): Long{
        val database = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(Constants.P_NAME, park.name)
            put(Constants.P_FAV, park.isFav)
        }

        return database.insert(Constants.ENTITY_PARK, null, contentValues)
    }

    fun getAllParks(): MutableList<Park>{
       val parks: MutableList<Park> = mutableListOf()
        val database = this.readableDatabase
        val query = "SELECT * FROM ${Constants.ENTITY_PARK}"

        val result = database.rawQuery(query, null)

        if (result.moveToFirst()){
            do {
                val id = result.getColumnIndex(Constants.P_ID)
                val name = result.getColumnIndex(Constants.P_NAME)
                val isFav = result.getColumnIndex(Constants.P_FAV)

                if (id >= 0 && name >= 0 && isFav >= 0){
                    val park = Park(
                        id = result.getLong(id),
                        name = result.getString(name),
                        isFav = result.getInt(isFav) == 1
                    )
                    parks.add(park)
                }
            }while (result.moveToNext())
        }

        result.close()
        return parks
    }

    fun updatePark(park: Park): Boolean{
        val database = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(Constants.P_NAME, park.name)
            put(Constants.P_FAV, park.isFav)
        }

        val result = database.update(
            Constants.ENTITY_PARK,
            contentValues,
            "${Constants.P_ID} == ${park.id}",
            null
        )

        return result == 1
    }

    fun deletePark(park: Park): Boolean{
        val database = this.readableDatabase

        val result = database.delete(
            Constants.ENTITY_PARK,
            "${Constants.P_ID} == ${park.id}",
            null
        )

        return result == 1
    }
}