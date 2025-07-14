package com.luxboros.deletio.ui

import android.content.pm.ActivityInfo
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
actual fun ScreenOrientation() {
    val activity = LocalActivity.current
    LaunchedEffect(Unit) {
        activity?.requestedOrientation =
            ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
    }
}