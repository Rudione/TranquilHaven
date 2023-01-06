package com.rudione.tranquilhaven.ui.fragments.loginregister

import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.rudione.tranquilhaven.R
import com.rudione.tranquilhaven.data.model.User
import com.rudione.tranquilhaven.databinding.FragmentRegisterBinding
import com.rudione.tranquilhaven.utils.RegisterValidation
import com.rudione.tranquilhaven.utils.Resource
import com.rudione.tranquilhaven.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

private const val TAG = "RegisterFragment"

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()
    //private lateinit var vibrator: Vibrator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)
        // vibrator = context!!.getSystemService(Vibrator::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* val background = binding.background
        background.loadUrl("file:///android_asset/background.html") */

        binding.registerTv2.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.apply {
            registerBtLogin.setOnClickListener {
                val user = User(
                    registerEtFirstName.text.toString().trim(),
                    registerEtLastName.text.toString().trim(),
                    registerEtEmail.text.toString().trim()
                )
                val password = registerEtPassword.text.toString()
                viewModel.createAccountViewEmailAndPassword(user, password)
                //vibrator.vibrate(1000)
            }
        }

        lifecycleScope.launch {
            viewModel.register.collect() {
                when(it) {
                    is Resource.Loading -> {
                        binding.registerBtLogin.startAnimation()
                    }
                    is Resource.Success -> {
                        Log.d("AAA", it.data.toString())
                        binding.registerBtLogin.revertAnimation()
                    }
                    is Resource.Failure -> {
                        Log.d(TAG, it.message.toString())
                        binding.registerBtLogin.revertAnimation()
                    }
                    else -> Unit
                }
            }
        }

        lifecycleScope.launch {
            viewModel.validation.collect { validation ->
                if(validation.email is RegisterValidation.Failed) {
                    withContext(Dispatchers.Main) {
                        binding.registerBtLogin.apply {
                            requestFocus()
                            error = validation.email.message
                        }
                    }
                }
                if(validation.password is RegisterValidation.Failed) {
                    withContext(Dispatchers.Main) {
                        binding.registerEtPassword.apply {
                            requestFocus()
                            error = validation.password.message
                        }
                    }
                }
            }
        }
    }
}