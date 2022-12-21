package com.rudione.tranquilhaven.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.rudione.tranquilhaven.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _register = MutableStateFlow<Resource<FirebaseUser>>(Resource.Loading())
    val register: Flow<Resource<FirebaseUser>> = _register

    fun createAccountViewEmailAndPassword(email: String, password: String) {
        firebaseAuth
            .signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                it.user?.let {
                    _register.value = Resource.Success(it)
                }
            }.addOnFailureListener {
                _register.value = Resource.Failure(it.message.toString())
            }
    }
}