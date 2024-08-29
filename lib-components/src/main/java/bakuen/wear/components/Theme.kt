package bakuen.wear.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kyant.m3color.hct.Hct
import com.kyant.m3color.scheme.SchemeTonalSpot

object Theme {
    var color = ColorScheme.fromPrimary(keyColor = Color(0xFF_769CDF))
    val typo = Typo(
        body = TextStyle.Default.copy(
            color = color.onSurface,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp
        )
    )

}

data class ColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceContainer: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
) {
    companion object {
        fun fromPrimary(
            keyColor: Color,
            isDark: Boolean = true,
            pureDark: Boolean = true,
            contrastLevel: Double = 0.0
        ): ColorScheme {
            val hct = Hct.fromInt(keyColor.toArgb())
            val scheme = SchemeTonalSpot(hct, isDark, contrastLevel)
            val surface =
                if (pureDark) if (isDark) 0xFF000000.toInt() else 0xFFFFFFFF.toInt() else scheme.surface
            return ColorScheme(
                primary = Color(scheme.primary),
                onPrimary = Color(scheme.onPrimary),
                primaryContainer = Color(scheme.primaryContainer),
                onPrimaryContainer = Color(scheme.onPrimaryContainer),
                surface = Color(surface),
                surfaceContainer = Color(scheme.surfaceContainer),
                onSurface = Color(scheme.onSurface),
                onSurfaceVariant = Color(scheme.outline),
                outline = Color(scheme.outline)
            )
        }
    }
}

data class Typo(
    val body: TextStyle,
    val bodySmall: TextStyle = body.copy(
        fontSize = body.fontSize.times(0.9f)
    ),
    val title: TextStyle = body.copy(
        fontWeight = FontWeight.Bold
    ),
    val titleSmall: TextStyle = title.copy(
        fontSize = title.fontSize.times(0.9f)
    )
)