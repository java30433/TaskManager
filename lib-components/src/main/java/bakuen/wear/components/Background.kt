package bakuen.wear.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
inline fun Surface(
    color: Color = Theme.color.surface,
    fillMaxSize: Boolean = false,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .background(color = color)
            .cleanClick()
            .condition(fillMaxSize, Modifier.fillMaxSize())
        , content = content, contentAlignment = contentAlignment
    )
}