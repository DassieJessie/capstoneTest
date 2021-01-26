package com.example.plantenbak.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plantenbak.R
import com.example.plantenbak.model.Plant
import kotlinx.android.synthetic.main.fragment_plantenbak.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PlantenbakFragment : Fragment() {

    private val plants = arrayListOf<Plant>()
    private val plantAdapter = PlantAdapter(plants)

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
    }
}