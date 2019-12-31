package gameoflife.game
import gameoflife.util.*

/**
 * Instance of the game
 * Game board is in row-major form (row, column)
 */
class Game(
    val boardSize: Size<Int>,

    // Initial states for each entity (dead or alive)
    initStates: Array<Array<EntityState>> = Array(boardSize.height) { Array(boardSize.width) { EntityState.DEAD } })
{

    // initialize window and 2D array for game board
    val gameBoard: Array<Array<Entity>> = Array(boardSize.height)
    { row ->
        Array(boardSize.width)
        { cell ->
            Entity(initStates[row][cell], Position(row, cell))
        }
    }

    /**
     * Update entity states based on their siblings
     */
    fun update()
    {
        var aliveSibs = 0
        var deadSibs =  0
        for(row in gameBoard){

            for(cell in row){
                // north sib -> The top row will not have a north sib
                if (gameBoard[cell.northSib.x][cell.northSib.y].isDead()) {deadSibs+=1}
                    else if (gameBoard[cell.northSib.x][cell.northSib.y].isAlive()) {aliveSibs+=1}
                // north east sib -> top row, and rightmost column will not have a north sib
                if (gameBoard[cell.northEastSib.x][cell.northEastSib.y].isDead()) {deadSibs+=1}
                    else if (gameBoard[cell.northEastSib.x][cell.northEastSib.y].isAlive()) {aliveSibs+=1}
                // north west sib -> top row, and leftmost column will not have a north west sib
                if (gameBoard[cell.northWestSib.x][cell.northWestSib.y].isDead()) {deadSibs+=1}
                    else if (gameBoard[cell.northWestSib.x][cell.northWestSib.y].isAlive()) {aliveSibs+=1}
                // east sib -> the rightmost column will not have a east sib
                if (gameBoard[cell.eastSib.x][cell.eastSib.y].isDead()) {deadSibs+=1}
                    else if (gameBoard[cell.eastSib.x][cell.eastSib.y].isAlive()) {aliveSibs+=1}
                // west sib -> the leftmost column will not have a east sib
                if (gameBoard[cell.westSib.x][cell.westSib.y].isDead()) {deadSibs+=1}
                    else if (gameBoard[cell.westSib.x][cell.westSib.y].isAlive()) {aliveSibs+=1}
                // south sib -> bottom row will not have a south sib
                if (gameBoard[cell.southSib.x][cell.southSib.y].isDead()) {deadSibs+=1}
                    else if (gameBoard[cell.southSib.x][cell.southSib.y].isAlive()) {aliveSibs+=1}
                // south east sib -> bottom row and rightmost column wil not have a south east sib
                if (gameBoard[cell.southEastSib.x][cell.southEastSib.y].isDead()) {deadSibs+=1}
                    else if (gameBoard[cell.southEastSib.x][cell.southEastSib.y].isAlive()) {aliveSibs+=1}
                // south west sib -> bottom row and leftmost column wil not have a south east sib
                if (gameBoard[cell.southWestSib.x][cell.southWestSib.y].isDead()) {deadSibs+=1}
                    else if (gameBoard[cell.southWestSib.x][cell.southWestSib.y].isAlive()) {aliveSibs+=1}

                // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
                if(deadSibs == 3 && gameBoard[cell.position.x][cell.position.y].isDead()) {
                    gameBoard[cell.position.x][cell.position.y].resurrect()
                    // Any live cell with two or three neighbors survives.
                }else if ((aliveSibs == 2 || aliveSibs == 3) && gameBoard[cell.position.x][cell.position.y].isAlive()){
                    gameBoard[cell.position.x][cell.position.y].resurrect()
                    // All other live cells die in the next generation. Similarly, all other dead cells stay dead.
                }else{
                    gameBoard[cell.position.x][cell.position.y].kill()
                }



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


// Logic here with updating 2d array