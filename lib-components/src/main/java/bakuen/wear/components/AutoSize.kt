package bakuen.wear.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

@Composable
fun AutoSize(designWidth: Float, content: @Composable ()->Unit) {
    val fontScale = LocalDensity.current.fontScale
    val displayMetrics = LocalContext.current.resources.displayMetrics
    val widthPixels = displayMetrics.widthPixels
    CompositionLocalProvider(
        LocalDensity provides Density(
            density = widthPixels / designWidth,
            fontScale = fontScale
        ),
        content = content
    )
}