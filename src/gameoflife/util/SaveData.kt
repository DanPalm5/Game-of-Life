package gameoflife.util

import gameoflife.game.Entity
import gameoflife.game.EntityState
import gameoflife.game.Game
import gameoflife.main.INIT_GRID_SIZE
import java.io.File
import java.nio.charset.Charset

fun writeToFile(board: Array<Array<EntityState>>, filename: String) { // write to a file
    // take current array, store in file (1/0)
    val fileToWrite = File(filename)
    val stringbuilder = StringBuilder()
    for (row in board) {
        for (cell in row) {
            stringbuilder.append(cell.ordinal)
        }
        stringbuilder.append('\n')
    }

    fileToWrite.writeText(stringbuilder.toString())
}

fun readSaveFile( filename: String) : Array<Array<EntityState>> { // read file, create array from contents
    val saveFile = File(filename)
    val contents = saveFile.readText()
    val lines = contents.lines()
    return Array(lines.size)
    {
            line ->
                Array(lines[line].length)
                {
                     char ->
                    if(lines[line][char].toInt() == 0) EntityState.DEAD else EntityState.ALIVE
                 }
    }
}















