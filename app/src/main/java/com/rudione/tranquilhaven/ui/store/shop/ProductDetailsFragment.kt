package com.rudione.tranquilhaven.ui.store.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rudione.tranquilhaven.R
import com.rudione.tranquilhaven.databinding.FragmentProductDetailsBinding
import com.rudione.tranquilhaven.ui.store.ShoppingActivity
import com.rudione.tranquilhaven.ui.store.shop.adapters.details.ViewPager2Images
import com.rudione.tranquilhaven.utils.hideBottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {
    private val arg by navArgs<ProductDetailsFragmentArgs>()
    private lateinit var binding: FragmentProductDetailsBinding
    private val viewPagerAdapter by lazy { ViewPager2Images() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hideBottomNavigationView()
        binding = FragmentProductDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = arg.product
        setupViewpager()

        binding.imageClose.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.apply {
            tvProductName.text = product.name
            tvProductPrice.text = "грн: ${product.price}"
            tvProductDescription.text = product.description
            tvProductAllDescription.text = product.productDetails
        }

        viewPagerAdapter.differ.submitList(product.images)
    }

    private fun setupViewpager() {
        binding.apply {
            viewPagerProductsImages.adapter = viewPagerAdapter
        }
    }
}