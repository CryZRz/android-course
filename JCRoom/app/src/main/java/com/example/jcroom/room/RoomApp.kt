package com.example.jcroom.room

import android.app.Application
import androidx.compose.ui.unit.Constraints
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.jcroom.room.entities.User
import com.example.jcroom.room.entities.UserAuth

class RoomApp: Application() {
    companion object{
        lateinit var db: AppDatabase
        lateinit var auth: UserAuth
    }

    override fun onCreate() {
        super.onCreate()

        val MIGRATION_1_2 = object : Migration(Constants.DB_INIT_VERSION, Constants.DB_2026_INIT_VERSION){
            override fun migrate(db: SupportSQLiteDatabase) {
                super.migrate(db)
                db.execSQL("ALTER TABLE ${Constants.E_INSECTS} ADD COLUMN uid " +
                        "INTEGER NOT NULL DEFAULT 1 ")
            }
        }

        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            Constants.DB_NAME
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }
}