package gameoflife.util

import gameoflife.game.Entity
import gameoflife.game.EntityState
import java.io.File

object SaveData
{
    fun writeSaveData(filename: String, board: Array<Array<EntityState>>)
    { // write to a file
        // take current array, store in file (1/0)
        val fileToWrite = File(filename)
        val stringbuilder = StringBuilder()
        for (row in board)
        {
            for (cell in row)
            {
                stringbuilder.append(cell.value)
            }
            stringbuilder.append('\n')
        }

        fileToWrite.writeText(stringbuilder.toString())
    }

    fun writeSaveData(filename: String, board: Array<Array<Entity>>)
    {   // write to a file
        // take current array, store in file (1/0)
        val fileToWrite = File(filename)
        val stringbuilder = StringBuilder()
        for (row in board)
        {
            for (cell in row)
            {
                stringbuilder.append(cell.state.value)
            }
            stringbuilder.append('\n')
        }

        fileToWrite.writeText(stringbuilder.toString())
    }

    fun readSaveData(filename: String): Array<Array<EntityState>>
    { // read file, create array from contents
        val saveFile = File(filename)
        val contents = saveFile.readText()
        val lines = contents.lines()
        return Array(lines.size)
        { line ->
            Array(lines[line].length)
            { char ->
                if (lines[line][char] == '0') EntityState.DEAD else EntityState.ALIVE
            }
        }
    }
}














