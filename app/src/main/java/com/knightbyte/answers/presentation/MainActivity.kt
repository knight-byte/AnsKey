package com.knightbyte.answers.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory
import com.knightbyte.answers.presentation.ui.screens.MainScreen
import com.knightbyte.answers.presentation.ui.theme.AnswersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(/*context=*/this)
        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        firebaseAppCheck.installAppCheckProviderFactory(
            SafetyNetAppCheckProviderFactory.getInstance()
        )
        super.onCreate(savedInstanceState)
        setContent {
            AnswersTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                   MainScreen()
                }
            }
        }
    }
}
