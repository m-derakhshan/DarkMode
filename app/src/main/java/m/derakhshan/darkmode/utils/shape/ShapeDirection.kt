package m.derakhshan.darkmode.utils.shape


sealed interface ShapeDirection {
    data object ToLeft : ShapeDirection
    data object ToRight : ShapeDirection
}