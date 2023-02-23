package com.rudione.tranquilhaven.ui.store.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.rudione.tranquilhaven.databinding.FragmentShopBinding
import com.rudione.tranquilhaven.ui.store.shop.adapters.ShopViewPagerAdapter
import com.rudione.tranquilhaven.ui.store.shop.categories.*

class ShopFragment : Fragment() {

    private lateinit var binding: FragmentShopBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesFragments = arrayListOf(
            MainCategoryFragment(),
            AmazonAlexaFragment(),
            GoogleHomeFragment(),
            HomeKitFragment(),
            SmartThingsFragment()
        )

        binding.viewPagerShop.isUserInputEnabled = false

        val viewPagerAdapter = ShopViewPagerAdapter(
            categoriesFragments, childFragmentManager, lifecycle
        )
        binding.viewPagerShop.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPagerShop) { tab, position ->
            when (position) {
                0 -> tab.text = "Main"
                1 -> tab.text = "AmazonAlexa"
                2 -> tab.text = "GoogleHome"
                3 -> tab.text = "HomeKit"
                4 -> tab.text = "SmartThings"
            }
        }.attach()
    }
}