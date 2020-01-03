package gameoflife.main

import gameoflife.game.Game
import gameoflife.ui.GameWindow
import gameoflife.util.SaveData
import gameoflife.util.Size

val WINDOW_SIZE = Size(800, 800)

fun main()
{
    val game = Game(SaveData.readSaveFile("testsave.txt"))
    val window = GameWindow(WINDOW_SIZE, game)
    window.isVisible = true
}



// This file should facilitate game operations