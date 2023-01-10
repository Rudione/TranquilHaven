package com.rudione.tranquilhaven.ui.fragments.loginregister

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.rudione.tranquilhaven.R
import com.rudione.tranquilhaven.databinding.FragmentLoginBinding
import com.rudione.tranquilhaven.ui.activities.ShoppingActivity
import com.rudione.tranquilhaven.ui.fragments.loginregister.dialog.setupBottomSheetDialog
import com.rudione.tranquilhaven.utils.Resource
import com.rudione.tranquilhaven.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginTv2.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.loginTv3.setOnClickListener {
            setupBottomSheetDialog { email ->
                viewModel.resetPassword(email)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.resetPassword.collect {
                when(it) {
                   is Resource.Loading -> {}
                   is Resource.Success -> {
                        Snackbar.make(requireView(), "Reset link was sent to your email", Snackbar.LENGTH_LONG).show()
                   }
                   is Resource.Failure -> {
                        Snackbar.make(requireView(), "Error: ${it.message.toString()}", Snackbar.LENGTH_LONG).show()
                   }
                   is Resource.Unspecified -> {}
                }
            }
        }

        binding.apply {
            loginBtLogin.setOnClickListener {
                val email = loginEtEmail.text.toString().trim()
                val password = loginEtPassword.text.toString()
                viewModel.login(email, password)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.login.collect {
                when(it) {
                    is Resource.Loading -> {
                        binding.loginBtLogin.startAnimation()
                    }
                    is Resource.Failure -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                        binding.loginBtLogin.revertAnimation()
                    }
                    is Resource.Success -> {
                        binding.loginBtLogin.revertAnimation()
                        Intent(requireActivity(), ShoppingActivity::class.java).also { intent ->
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                    }
                    else -> Unit
                }
            }
        }
    }
}