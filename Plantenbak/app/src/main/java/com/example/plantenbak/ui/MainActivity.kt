package com.example.plantenbak.ui

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.plantenbak.R
import com.example.plantenbak.viewmodel.PlantViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val viewModel: PlantViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        navController = findNavController(R.id.nav_host_fragment)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            navController.navigate(
                R.id.action_plantenbakFragment_to_addPlantFragment
            )
        }

        fabToggler()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        navController.addOnDestinationChangedListener { _,       destination, _ ->
            menu.findItem(R.id.btnBin)?.isVisible = destination.id !in arrayOf(R.id.addPlantFragment)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.btnBin -> {
                viewModel.deleteAllPlants()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun fabToggler() {

        navController.addOnDestinationChangedListener { _,       destination, _ ->
            if (destination.id in arrayOf(R.id.addPlantFragment)) {
                findViewById<FloatingActionButton>(R.id.fab).hide()

            } else {
                findViewById<FloatingActionButton>(R.id.fab).show()
            }
        }
    }
}