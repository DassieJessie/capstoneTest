package com.example.plantenbak.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.plantenbak.converter.Converter
import com.example.plantenbak.dao.PlantDao
import com.example.plantenbak.model.Plant

@Database(entities = [Plant::class], version = 2, exportSchema = false)
@TypeConverters(Converter::class)
abstract class PlantDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao

    companion object {
        private const val DATABASE_NAME = "PLANT_DATABASE"

        @Volatile
        private var plantDatabaseInstance: PlantDatabase? = null

        fun getDatabase(context: Context): PlantDatabase? {

            //make database
            if (plantDatabaseInstance == null) {
                synchronized(PlantDatabase::class.java) {
                    if (plantDatabaseInstance == null) {
                        plantDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            PlantDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }

            return plantDatabaseInstance
        }
    }
}