package com.dong.reader.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dong.reader.model.MUser
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor() : ViewModel() {
//    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun signInUserWithEmailAndPassword(email: String, password: String, callback: () -> Unit) =
        viewModelScope.launch {
            try {
                if (_loading.value == false) {
                    _loading.value = true
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d(
                                    "ReaderApp",
                                    "signInUserWithEmailAndPassword: success ${task.result.user?.uid}"
                                )
                                callback.invoke()
                            } else {
                                Log.d(
                                    "ReaderApp",
                                    "signInUserWithEmailAndPassword: Failed ${task.result}"
                                )
                            }
                            _loading.value = false
                        }
                        .addOnFailureListener {
                        }
                }
            } catch (_: Exception) {
            }
        }

    fun createUserWithEmailAndPassword(email: String, password: String, callback: () -> Unit) =
        viewModelScope.launch {
            try {
                if (_loading.value == false) {
                    _loading.value = true
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val displayName = task.result.user?.email?.split('@')?.get(0)
                                createUser(displayName)
                                callback.invoke()
                            } else {
                                Log.d(
                                    "ReaderApp",
                                    "createUserWithEmailAndPassword: Failed ${task.result}"
                                )
                            }
                            _loading.value = false
                        }
                        .addOnFailureListener {
                        }
                }
            } catch (_: Exception) {
            }
        }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = MUser(
            id = null,
            userId = userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            quote = "Life is great",
            profession = "Android Developer"
        ).toMap()
        Firebase.firestore.collection("users").add(user)

    }
}