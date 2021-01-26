package com.example.plantenbak.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.plantenbak.model.Plant
import com.example.plantenbak.repository.PlantRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlantViewModel (application: Application): AndroidViewModel(application) {

    private val repository = PlantRepository(application.applicationContext)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    val plants: LiveData<List<Plant>> = repository.getAllPlants()

    fun insertPlant(plant: Plant){
        ioScope.launch {
            repository.insertPlant(plant)
        }
    }

    fun deletePlant(plant: Plant){
        ioScope.launch {
            repository.deletePlant(plant)
        }
    }

    fun deleteAllPlants(){
        ioScope.launch {
            repository.deleteAllPlants()
        }
    }


}