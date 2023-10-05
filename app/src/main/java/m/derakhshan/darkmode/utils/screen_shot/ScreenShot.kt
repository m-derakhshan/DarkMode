package m.derakhshan.darkmode.utils.screen_shot

import android.app.Activity
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import android.view.View
import androidx.compose.ui.geometry.Rect

fun View.screenshot(
    bounds: Rect,
    bitmapCallback: (Bitmap?) -> Unit
) {
    try {
        val bitmap = Bitmap.createBitmap(
            bounds.width.toInt(),
            bounds.height.toInt(),
            Bitmap.Config.ARGB_8888,
        )
        PixelCopy.request(
            (this.context as Activity).window,
            android.graphics.Rect(
                bounds.left.toInt(),
                bounds.top.toInt(),
                bounds.right.toInt(),
                bounds.bottom.toInt()
            ),
            bitmap,
            {
                when (it) {
                    PixelCopy.SUCCESS -> {
                        bitmapCallback.invoke(bitmap)
                    }

                    else -> {
                        bitmapCallback.invoke(null)
                    }
                }
            },
            Handler(Looper.getMainLooper())
        )
    } catch (e: Exception) {
        bitmapCallback.invoke(null)
    }
}