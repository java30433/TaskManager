package wat.app.taskmanager.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import bakuen.wear.components.Space
import bakuen.wear.components.calculate
import bakuen.wear.components.condition
import bakuen.wear.components.wear.verticalRotaryScroll
import wat.app.taskmanager.BuildConfig

object Const {
    val ignoreApps = setOf(
        BuildConfig.APPLICATION_ID,
        "moe.shizuku.privileged.api",
        "com.google.android.wearable.app",
        "com.google.android.gms",
        "com.android.connectivity.metrics",
        "com.android.se",
        "com.android.phone",
        "com.android.providers.calendar",
        "com.google.android.ext.services",
        "com.google.android.deskclock",
        "con.google.android.apps.handwriting.ime",
        "system"
    )
    val scrPaddingHor = 9.dp
    val scrPaddingTop = 10.dp
    val colArrangement = Arrangement.spacedBy(2.dp)
}