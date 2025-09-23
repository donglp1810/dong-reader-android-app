package com.dong.reader

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.dong.reader.ui.theme.DongReaderTheme
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DongReaderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val db = FirebaseFirestore.getInstance()
                    val user: MutableMap<String, Any> = HashMap()
                    user["firstName"] = "Dong"
                    user["lastName"] = "Le"
                    db.collection("users")
                        .add(user)
                        .addOnSuccessListener {
                            Log.d("TAG", "DocumentSnapshot added with ID: " + it.id)
                        }
                        .addOnFailureListener {
                            Log.w("TAG", "Error adding document", it)
                        }

                    Surface(modifier = Modifier.padding(innerPadding)) {
                        Text(text = "Hello World")
                    }
                }
            }
        }
    }
}
