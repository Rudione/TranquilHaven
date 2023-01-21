package com.rudione.tranquilhaven.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.rudione.tranquilhaven.R
import com.rudione.tranquilhaven.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroductionViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val firebaseAuth: FirebaseAuth
): ViewModel() {

    private val _navigate = MutableLiveData<Int>()
    val navigate: LiveData<Int> = _navigate

    init {
        val isButtonClicked = sharedPreferences.getBoolean(Constants.INTRODUCTION_KEY, false)
        val currentUser = firebaseAuth.currentUser

        if(currentUser != null) {
            viewModelScope.launch {
                _navigate.postValue(SHOPPING_ACTIVITY)
            }
        } else if(isButtonClicked) {
            viewModelScope.launch {
                _navigate.postValue(ACCOUNT_OPTIONS_FRAGMENTS)
            }
        } else {
            Unit
        }
    }

    fun startButtonClick() {
        sharedPreferences.edit().putBoolean(Constants.INTRODUCTION_KEY, true).apply()
        _navigate.postValue(ACCOUNT_OPTIONS_FRAGMENTS)
    }

    companion object {
        internal const val SHOPPING_ACTIVITY = 12345
        internal const val ACCOUNT_OPTIONS_FRAGMENTS = R.id.action_introductionFragment_to_accountOptionsFragment
    }
}
