package gameoflife.game
import gameoflife.util.*

/**
 * Instance of the game
 * Game board is in row-major form (row, column)
 */
class Game(
    // Initial states for each entity (dead or alive)
    initStates: Array<Array<EntityState>> = Array(1) { Array(1) { EntityState.DEAD } })
{

    val boardSize: Size<Int> = Size(initStates.getOrNull(0)?.size ?: 0, initStates.size)

    // initialize window and 2D array for game board
    val gameBoard: Array<Array<Entity>> = Array(initStates.size)
    { row ->
        Array(initStates[row].size)
        { column ->
            Entity(initStates.getOrNull(row)?.getOrNull(column) ?: EntityState.DEAD, GridPos(row, column))
        }
    }

    /**
     * Update entity states based on their siblings
     */
    fun update()
    {
        var aliveSibs = 0
        var deadSibs =  0
        for((rowIndex,rowArray) in gameBoard.withIndex()) { //(row index, array of rows)
            for((columnIndex, entity) in rowArray.withIndex()) { // (cell index in row array, state of cell)
                   /*when {
                        // north sib
                       entity.getSib(gameBoard, entity.northSib)?.isDead() ?: false -> deadSibs += 1
                       entity.getSib(gameBoard, entity.northSib)?.isAlive() ?: false -> aliveSibs +=1

                       // northwest sib
                       entity.getSib(gameBoard, entity.northWestSib)?.isDead() ?: false -> deadSibs += 1
                       entity.getSib(gameBoard, entity.northWestSib)?.isAlive() ?: false -> aliveSibs+=1

                       // northeast sib
                       entity.getSib(gameBoard, entity.northEastSib)?.isDead() ?: false -> deadSibs += 1
                       entity.getSib(gameBoard, entity.northEastSib)?.isAlive() ?: false -> aliveSibs+=1

                       // west sib
                       entity.getSib(gameBoard, entity.westSib)?.isDead() ?: false -> deadSibs += 1
                       entity.getSib(gameBoard, entity.westSib)?.isAlive() ?: false -> aliveSibs+=1

                       // east sib
                       entity.getSib(gameBoard, entity.eastSib)?.isDead() ?: false -> deadSibs +=1
                       entity.getSib(gameBoard, entity.eastSib)?.isAlive() ?: false -> aliveSibs+=1

                       // south sib
                       entity.getSib(gameBoard, entity.southSib)?.isDead() ?: false -> deadSibs += 1
                       entity.getSib(gameBoard, entity.southSib)?.isAlive() ?: false -> aliveSibs+=1

                       // southwest sib
                       entity.getSib(gameBoard, entity.southWestSib)?.isDead() ?: false -> deadSibs += 1
                       entity.getSib(gameBoard, entity.southWestSib)?.isAlive() ?: false -> aliveSibs+=1

                       // southeast sib
                       entity.getSib(gameBoard, entity.southEastSib)?.isDead() ?: false -> deadSibs += 1
                       entity.getSib(gameBoard, entity.southEastSib)?.isAlive() ?: false -> aliveSibs+=1
                   }

                    */


                // Any dead cell with three live neighbors becomes a live cell.
                if(aliveSibs == 3 && entity.isDead()) {
                    entity.resurrect()
                    // Any live cell with fewer than two live neighbours dies, as if by underpopulation.
                }else if (aliveSibs < 2 && entity.isAlive()) {
                    entity.kill()
                    //Any live cell with two or three live neighbours lives on to the next generation.
                }else if (( aliveSibs == 2 || aliveSibs == 3 ) && entity.isAlive()) {
                    entity.resurrect()
                    //Any live cell with more than three live neighbours dies, as if by overpopulation.
                }else if(aliveSibs > 3 && entity.isAlive()){
                    entity.kill()
                    // All other live cells die in the next generation. Similarly, all other dead cells stay dead.
                }else if (entity.isAlive()){
                    entity.kill()
                }

                // reset sib count
                aliveSibs = 0
                deadSibs = 0

            }
        }

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


