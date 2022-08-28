package com.lucasqrib.cats.platform.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.lucasqrib.cats.databinding.ActivityCatListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatsMainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var binding: ActivityCatListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = binding.navHostCatList.getFragment<NavHostFragment>()
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = binding.navHostCatList.findNavController()
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}