package m.derakhshan.darkmode.utils.screen_shot

import android.graphics.Bitmap
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class ScreenshotState internal constructor() {
    private val _bitmap = mutableStateOf<Bitmap?>(null)
    val bitmap: State<Bitmap?> = _bitmap

    fun setBitmap(bitmap: Bitmap?) {
        _bitmap.value = bitmap
    }

    internal var callback: (() -> Unit)? = null
    fun capture() {
        callback?.invoke()
    }

}