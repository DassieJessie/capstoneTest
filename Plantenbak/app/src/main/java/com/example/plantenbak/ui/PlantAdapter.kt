package com.example.plantenbak.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plantenbak.R
import com.example.plantenbak.model.Plant
import kotlinx.android.synthetic.main.item_plant.view.*

class PlantAdapter (private val plants: List<Plant>) :
    RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(plant: Plant) {
            itemView.tvTitle.text = plant.title

            itemView.tvPlantDate.text = itemView.resources.getString(R.string.datum_plant,
                plant.plantDate.dayOfMonth.toString(),
                plant.plantDate.month.name,
                plant.plantDate.year.toString())

            itemView.tvWater.text = plant.waterPerWeek
            itemView.tvZonlicht.text = plant.zonlichtNodig
            itemView.tvNotitie.text = plant.notitie
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return plants.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(plants[position])
    }
}