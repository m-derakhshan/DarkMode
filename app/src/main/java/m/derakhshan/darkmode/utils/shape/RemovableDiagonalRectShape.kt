package m.derakhshan.darkmode.utils.shape

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection


class RemovableDiagonalRectShape(private val offset: Float, private val direction: ShapeDirection) :
    Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = drawPath(size = size, offset = offset, direction = direction)
        )
    }

    private fun drawPath(size: Size, offset: Float, direction: ShapeDirection): Path {
        return Path().apply {
            when (direction) {
                is ShapeDirection.ToLeft -> {
                    reset()
                    lineTo(x = 0f, y = 0f)
                    lineTo(x = 0f, y = size.height)
                    lineTo(x = (size.width - offset * 2), y = size.height)
                    lineTo(x = (size.width - offset * 1.2f), y = 0f)

                    close()
                }

                is ShapeDirection.ToRight -> {
                    reset()
                    lineTo(x = size.width, y = 0f)
                    lineTo(x = size.width, y = size.height)
                    lineTo(x = offset * 2, y = size.height)
                    lineTo(x = offset * 1.2f, y = 0f)
                    close()
                }
            }
        }
    }

}