package gameoflife.util

import java.io.File
import java.io.FileOutputStream
import java.nio.channels.Channels

data class Position<T>(val x: T, val y: T)
data class Size<T>(val width: T, val height: T)
data class GridPos<T>(val col: T, val row: T)