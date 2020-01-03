package gameoflife.main

import gameoflife.game.EntityState
import gameoflife.game.Game
import gameoflife.ui.GameWindow
import gameoflife.util.Size

const val INIT_GRID_SIZE = 20;
val WINDOW_SIZE = Size(800, 800)
/*val STATE_TEST = arrayOf(
    arrayOf(EntityState.DEAD, EntityState.DEAD, EntityState.ALIVE),
    arrayOf(EntityState.DEAD, EntityState.ALIVE, EntityState.ALIVE)
)*/

val STATE_TEST = arrayOf(
    arrayOf(0, 0, 1, 1, 0, 0, 1, 0, 0, 1),
    arrayOf(1, 1, 0, 1, 1, 0, 0, 0, 0, 0),
    arrayOf(1, 0, 0, 0, 0, 1, 1, 1, 1, 1),
    arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
    arrayOf(1, 1, 0, 0, 0, 1, 1, 0, 0, 1),
    arrayOf(1, 0, 1, 0, 1, 0, 1, 0, 1, 0),
    arrayOf(0, 1, 0, 1, 0, 1, 0, 1, 0, 1)
)

// Convert 0/1 to entity dead/alive. Here for debugging purposes, this will eventually
// be part of the save data read/write object
fun convStates(states: Array<Array<Int>>) : Array<Array<EntityState>>
{
    return Array(states.size)
    { row ->
            Array(states[row].size)
            { cell ->
                if(states[row][cell] == 0) EntityState.DEAD else EntityState.ALIVE
            }
    }
}

fun main(args: Array<String>)
{
/*    val game = Game(Size(INIT_GRID_SIZE, INIT_GRID_SIZE))
    val window = GameWindow(WINDOW_SIZE, game)
    window.isVisible = true*/

    val game = Game(convStates(STATE_TEST))
    val window = GameWindow(WINDOW_SIZE, game)
    window.isVisible = true
}



// This file should facilitate game operations