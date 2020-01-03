package gameoflife.util

import gameoflife.game.Entity
import gameoflife.game.EntityState
import gameoflife.game.Game
import gameoflife.main.INIT_GRID_SIZE
import java.io.File
import java.nio.charset.Charset

fun writeToFile(text: String){

}

fun readSaveFile( filename: String) : Array<Array<EntityState>> {
    val saveFile = File(filename)
    val contents = saveFile.readText()
    val lines = contents.lines()
    val arr: Array<Array<EntityState>> = Array(lines.size)
    {
            line ->
                Array(lines[line].length)
                {
                     char ->
                    if(lines[line][char].toInt() == 0) EntityState.DEAD else EntityState.ALIVE
                 }
    }
    return arr
}















