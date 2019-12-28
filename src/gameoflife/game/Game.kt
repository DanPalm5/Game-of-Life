package gameoflife.game

/**
 * Instance of the game
 * Game board is in row-major form (row, column)
 */
class Game(
    val boardSize: Pair<Int, Int>,

    // Initial states for each entity (dead or alive)
    private val initStates: Array<Array<EntityState>> = Array(boardSize.first) { Array(boardSize.second) { EntityState.DEAD } })
{
    // initialize window and 2D array for game board
    val gameBoard: Array<Array<Entity>> = Array(boardSize.first)
    { row ->
        Array(boardSize.second)
        { cell ->
            Entity(initStates[row][cell], Pair(row, cell))
        }
    }

    /**
     * Update entity states based on their siblings
     */
    fun update()
    {

    }

}

/* Rules

cares about cells that are horizontally, vertically, or diagonally
adjacent

Any live cell with fewer than two live neighbours dies, as if by underpopulation.
Any live cell with two or three live neighbours lives on to the next generation.
Any live cell with more than three live neighbours dies, as if by overpopulation.
Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
These rules, which compare the behavior of the automaton to real life, can be condensed into the following:

Any live cell with two or three neighbors survives.
Any dead cell with three live neighbors becomes a live cell.
All other live cells die in the next generation. Similarly, all other dead cells stay dead.
*/


// Logic here with updating 2d array