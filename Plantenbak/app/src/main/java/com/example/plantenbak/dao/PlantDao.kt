package com.example.plantenbak.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.plantenbak.model.Plant

@Dao
interface PlantDao {

    @Query("SELECT * FROM plantTable ORDER BY plantDate ASC")
    fun getAllPlants(): LiveData<List<Plant>>

    @Query("DELETE FROM plantTable")
    suspend fun deleteAllPlants()

    @Insert
    suspend fun insertPlant(plant: Plant)

    @Delete
    suspend fun deletePlant(plant: Plant)
}