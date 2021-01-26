package com.example.plantenbak.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plantenbak.R
import com.example.plantenbak.model.Plant
import com.example.plantenbak.viewmodel.PlantViewModel
import kotlinx.android.synthetic.main.fragment_plantenbak.*
import androidx.recyclerview.widget.ItemTouchHelper

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PlantenbakFragment : Fragment() {

    private val plants = arrayListOf<Plant>()
    private val plantAdapter = PlantAdapter(plants)

    private val viewModel : PlantViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plantenbak, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRV()

    }

    private fun initRV(){
        rvPlants.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvPlants.adapter = plantAdapter

        rvPlants.addItemDecoration(
            DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
        )

        createItemTouchHelper().attachToRecyclerView(rvPlants)

        observeAddGame()
    }

    private fun observeAddGame(){
        viewModel.plants.observe(viewLifecycleOwner, Observer { plants ->
            //Log.d("HELP", "$plants")
            this.plants.clear()
            this.plants.addAll(plants)
            plantAdapter.notifyDataSetChanged()

        })
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                val plantToDelete = plants[position]
                viewModel.deletePlant(plantToDelete)
            }
        }

        return ItemTouchHelper(callback)
    }
}