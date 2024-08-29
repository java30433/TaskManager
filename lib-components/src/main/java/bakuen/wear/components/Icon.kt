package bakuen.wear.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@Composable
fun Icon(modifier: Modifier, @DrawableRes res: Int, color: Color = LocalTextColor.current) {
    Image(
        modifier = modifier,
        painter = painterResource(id = res),
        contentDescription = null,
        colorFilter = ColorFilter.tint(color)
    )
}