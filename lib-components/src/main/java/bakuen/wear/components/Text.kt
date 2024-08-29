package bakuen.wear.components

import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

val LocalTextColor = staticCompositionLocalOf { Theme.color.onSurface }

@Composable
fun Text(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = Theme.typo.body,
    color: Color = LocalTextColor.current,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
) {
    val minLines =
        if (lineLimits is TextFieldLineLimits.MultiLine) lineLimits.minHeightInLines else 1
    val maxLines =
        if (lineLimits is TextFieldLineLimits.MultiLine) lineLimits.maxHeightInLines else 1
    BasicText(
        modifier = modifier, text = text, style = style.copy(
            color = color
        ), minLines = minLines, maxLines = maxLines,
    )
}