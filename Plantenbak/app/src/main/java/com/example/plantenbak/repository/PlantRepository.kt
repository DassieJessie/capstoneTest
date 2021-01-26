package com.example.plantenbak.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.plantenbak.dao.PlantDao
import com.example.plantenbak.database.PlantDatabase
import com.example.plantenbak.model.Plant

class PlantRepository(context: Context) {
    private val plantDao : PlantDao

    init{
        val database = PlantDatabase.getDatabase(context)
        plantDao = database!!.plantDao()
    }

    fun getAllPlants(): LiveData<List<Plant>> {
        return plantDao.getAllPlants()
    }

    suspend fun deleteAllPlants(){
        plantDao.deleteAllPlants()
    }

    suspend fun insertPlant(plant: Plant){
        plantDao.insertPlant(plant)
    }

    suspend fun deletePlant(plant: Plant){
        plantDao.deletePlant(plant)
    }
}