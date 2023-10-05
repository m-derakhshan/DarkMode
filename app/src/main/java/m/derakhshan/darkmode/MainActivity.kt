package m.derakhshan.darkmode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import m.derakhshan.darkmode.ui.theme.DarkModeTheme
import m.derakhshan.darkmode.utils.screen_shot.ScreenshotScope
import m.derakhshan.darkmode.utils.screen_shot.rememberScreenshotState
import m.derakhshan.darkmode.utils.shape.RemovableDiagonalRectShape
import m.derakhshan.darkmode.utils.shape.ShapeDirection

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val screenShotState = rememberScreenshotState()
            val isDarkMode = remember { mutableStateOf(false) }
            val offset = remember { mutableFloatStateOf(0f) }
            val scope = rememberCoroutineScope()
            val screenWidth = LocalConfiguration.current.screenWidthDp
            val screenWishPx = with(LocalDensity.current) { screenWidth.dp.toPx() }
            val animationOffset =
                animateFloatAsState(
                    targetValue = offset.floatValue,
                    label = "animation offset",
                    finishedListener = {
                        offset.floatValue = 0f
                        screenShotState.setBitmap(null)
                    },
                    animationSpec = tween(1200)
                )


            DarkModeTheme(darkTheme = isDarkMode.value) {
                Box(modifier = Modifier.fillMaxSize()) {
                    ScreenshotScope(
                        screenshotState = screenShotState,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.background)
                    ) {
                        Content(isDarkMode = isDarkMode.value, onChangeDarkMode = {
                            scope.launch {
                                screenShotState.capture()
                                offset.floatValue = screenWishPx
                                delay(100)
                                isDarkMode.value = !isDarkMode.value
                            }
                        })
                    }
                    screenShotState.bitmap.value?.asImageBitmap()?.let {
                        Image(
                            bitmap = it,
                            contentDescription = "screen shot",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(
                                    shape = RemovableDiagonalRectShape(
                                        offset = animationOffset.value,
                                        direction = if (isDarkMode.value) ShapeDirection.ToLeft else ShapeDirection.ToRight
                                    )
                                )
                        )
                    }
                }
            }
        }
    }
}






