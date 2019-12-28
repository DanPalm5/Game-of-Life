package gameoflife.main

import gameoflife.game.Game
import gameoflife.ui.GameWindow

const val INIT_GRID_SIZE = 20;
val WINDOW_SIZE = Pair(800, 800)

fun main(args: Array<String>)
{
    val game = Game(Pair(INIT_GRID_SIZE, INIT_GRID_SIZE))
    val window = GameWindow(WINDOW_SIZE, game)
    window.isVisible = true
}



// This file should facilitate game operations