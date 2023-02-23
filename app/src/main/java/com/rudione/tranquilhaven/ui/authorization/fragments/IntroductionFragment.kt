package com.rudione.tranquilhaven.ui.authorization.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.rudione.tranquilhaven.R
import com.rudione.tranquilhaven.databinding.FragmentIntroductionBinding
import com.rudione.tranquilhaven.ui.store.ShoppingActivity
import com.rudione.tranquilhaven.viewmodel.IntroductionViewModel
import com.rudione.tranquilhaven.viewmodel.IntroductionViewModel.Companion.ACCOUNT_OPTIONS_FRAGMENTS
import com.rudione.tranquilhaven.viewmodel.IntroductionViewModel.Companion.SHOPPING_ACTIVITY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IntroductionFragment : Fragment(R.layout.fragment_introduction) {

    private lateinit var binding: FragmentIntroductionBinding
    private val viewModel by viewModels<IntroductionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroductionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.introductionBtStart.setOnClickListener {
            viewModel.startButtonClick()
            findNavController().navigate(ACCOUNT_OPTIONS_FRAGMENTS)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.navigate.observe(viewLifecycleOwner) {
                when(it) {
                    SHOPPING_ACTIVITY -> {
                        Intent(requireActivity(), ShoppingActivity::class.java).also { intent ->
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                    }
                    ACCOUNT_OPTIONS_FRAGMENTS -> {
                        findNavController().navigate(IntroductionViewModel.ACCOUNT_OPTIONS_FRAGMENTS)

                    }
                }
            }
        }
    }
}