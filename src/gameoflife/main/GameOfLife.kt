package gameoflife.main

import gameoflife.game.Game
import gameoflife.ui.GameWindow
import gameoflife.util.SaveData
import gameoflife.util.Size

val WINDOW_SIZE = Size(800, 800)

fun main()
{
//    Util.unpackResource("./resources/DefaultGameState.save")
//    val game = Game(SaveData.readSaveData("./resources/DefaultGameState.save"))
    val window = GameWindow(WINDOW_SIZE)
    window.isVisible = true
}



// This file should facilitate game operations