package m.derakhshan.darkmode.utils.screen_shot

import android.graphics.Bitmap
import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalView

@Composable
fun rememberScreenshotState() = remember {
    ScreenshotState()
}


@Composable
fun ScreenshotScope(
    modifier: Modifier = Modifier,
    screenshotState: ScreenshotState,
    content: @Composable () -> Unit,
) {
    val view: View = LocalView.current

    var composableBounds by remember {
        mutableStateOf<Rect?>(null)
    }

    DisposableEffect(Unit) {
        screenshotState.callback = {
            composableBounds?.let { bounds ->
                if (bounds.width == 0f || bounds.height == 0f) return@let
                view.screenshot(bounds) { resultBitmap: Bitmap? ->
                    screenshotState.setBitmap(resultBitmap)
                }
            }
        }

        onDispose {
            val bmp = screenshotState.bitmap.value
            bmp?.apply {
                if (!isRecycled) {
                    recycle()
                }
            }
            screenshotState.setBitmap(null)
            screenshotState.callback = null
        }
    }

    Box(modifier = modifier
        .onGloballyPositioned {
            composableBounds = it.boundsInWindow()
        }
    ) {
        content()
    }
}
