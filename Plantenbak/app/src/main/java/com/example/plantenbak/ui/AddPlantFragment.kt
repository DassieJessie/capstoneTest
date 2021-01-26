package com.example.plantenbak.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.plantenbak.R
import com.example.plantenbak.model.Plant
import com.example.plantenbak.viewmodel.PlantViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_add_plant.*
import java.time.LocalDate


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddPlantFragment : Fragment() {

    private val viewModel: PlantViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_plant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var fabS = view.findViewById(R.id.fabSave) as FloatingActionButton

        fabS.setOnClickListener{
            addPlant()
            findNavController().popBackStack()
        }

    }

    private fun addPlant(){
        val title = etTitle.text.toString()
        val date = LocalDate.of(etYear.text.toString().toInt(), etMonth.text.toString().toInt(), etDay.text.toString().toInt())

        var selectedRB = rgWater.checkedRadioButtonId
        var rbWater = view?.findViewById(selectedRB) as RadioButton
        val waterPW = rbWater.text.toString()

        val notification = switchNotificatie.isChecked

        selectedRB = rgZon.checkedRadioButtonId
        var rbZon = view?.findViewById(selectedRB) as RadioButton
        val zonlicht = rbZon.text.toString()

        val notitie = etNotities.text.toString()


        //Log.d("GAME", "$title $platform $date")
        viewModel.insertPlant(Plant(title, date, waterPW, notification, zonlicht, notitie))
    }
}