package com.example.cloudnews

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.cloudnews.domain.usecases.AppEntryUseCases
import com.example.cloudnews.presentation.onboarding.OnBoardingScreen
import com.example.cloudnews.presentation.onboarding.OnBoardingViewModel
import com.example.cloudnews.ui.theme.CloudNewsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        lifecycleScope.launch {
            appEntryUseCases.readAppEntry().collect {
                Log.d("test", it.toString())
            }
        }
        setContent {
            CloudNewsTheme {
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    val viewModel: OnBoardingViewModel = hiltViewModel()
                    OnBoardingScreen(event = viewModel::onEvent)
                }
            }
        }
    }
}