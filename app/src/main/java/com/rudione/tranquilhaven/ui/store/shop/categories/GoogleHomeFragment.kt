package com.rudione.tranquilhaven.ui.store.shop.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.rudione.tranquilhaven.R
import com.rudione.tranquilhaven.data.model.Category
import com.rudione.tranquilhaven.utils.Resource
import com.rudione.tranquilhaven.viewmodel.categories.CategoryViewModel
import com.rudione.tranquilhaven.viewmodel.categories.factory.BaseCategoryViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class GoogleHomeFragment : BaseCategoryFragment() {

    @Inject
    lateinit var firestore: FirebaseFirestore

    val viewModel by viewModels<CategoryViewModel> {
        BaseCategoryViewModelFactory(firestore, Category.GoogleHome)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.offerProducts.collectLatest {
                when (it) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        offerAdapter.differ.submitList(it.data)
                    }
                    is Resource.Failure -> {
                        Snackbar.make(requireView(), it.message.toString(), Snackbar.LENGTH_LONG)
                            .show()
                    }
                    else -> Unit
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.bestProducts.collectLatest {
                when (it) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        bestProductAdapter.differ.submitList(it.data)
                    }
                    is Resource.Failure -> {
                        Snackbar.make(requireView(), it.message.toString(), Snackbar.LENGTH_LONG)
                            .show()
                    }
                    else -> Unit
                }
            }
        }
    }
}