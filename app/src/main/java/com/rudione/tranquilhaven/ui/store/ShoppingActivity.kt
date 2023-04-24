package com.rudione.tranquilhaven.ui.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rudione.tranquilhaven.R
import com.rudione.tranquilhaven.databinding.ActivityShoppingBinding
import com.rudione.tranquilhaven.utils.Resource
import com.rudione.tranquilhaven.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ShoppingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingBinding

    val viewModel by viewModels<CartViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = Navigation.findNavController(this, R.id.shoppingHostFragment)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        lifecycleScope.launchWhenStarted {
            viewModel.cartProducts.collectLatest {
                when(it) {
                    is Resource.Success -> {
                        val count = it.data?.size ?: 0
                        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
                        bottomNavigation.getOrCreateBadge(R.id.trolleyFragment).apply {
                            number = count
                            backgroundColor = resources.getColor(R.color.primary_dark_luxury, null)
                        }
                    }
                    else -> Unit
                }
            }
        }
    }
}