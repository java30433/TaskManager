package bakuen.wear.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import kotlin.math.sqrt

fun Modifier.calculate(block: ()->Modifier?) =
    block()?.let { then(it) } ?: then(this)

fun Modifier.condition(condition: Boolean, ifTrue: Modifier, ifFalse: Modifier = this) =
    if (condition) then(ifTrue) else then(ifFalse)

@Composable
fun Modifier.cleanClick(onClick: (() -> Unit) = {}) = then(
    Modifier.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = onClick
    )
)

@Composable
fun Modifier.inscribedBoxSize() =
    then(Modifier.size((LocalContext.current.resources.displayMetrics.widthPixels / sqrt(2f)).pxToDp()))

internal fun Modifier.nullableClick(onClick: (() -> Unit)?): Modifier {
    return then(Modifier.clickable(onClick = onClick ?: return this))
}