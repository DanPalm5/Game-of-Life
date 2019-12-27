package gameoflife.main

import gameoflife.game.Game
import gameoflife.ui.Window

const val INIT_GRID_SIZE = 20;

fun main(args: Array<String>)
{
    val game = Game(Pair(INIT_GRID_SIZE, INIT_GRID_SIZE))
    val window = Window(Pair(INIT_GRID_SIZE, INIT_GRID_SIZE), game)
    window.isVisible = true
}



// This file should facilitate game operations