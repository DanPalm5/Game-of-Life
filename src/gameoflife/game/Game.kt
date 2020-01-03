package gameoflife.game
import gameoflife.main.INIT_GRID_SIZE
import gameoflife.util.*

/**
 * Instance of the game
 * Game board is in row-major form (row, column)
 */
class Game(
    val boardSize: Size<Int>,
    // Initial states for each entity (dead or alive)
    initStates: Array<Array<EntityState>> = Array(INIT_GRID_SIZE) { Array(INIT_GRID_SIZE) { EntityState.DEAD } })
{

    // initialize window and 2D array for game board
    val gameBoard: Array<Array<Entity>> = Array(INIT_GRID_SIZE)
    { row ->
        Array(INIT_GRID_SIZE)
        { cell ->
            Entity(initStates[row][cell], GridPos(row, cell))
        }
    }

    /**
     * Update entity states based on their siblings
     */
    fun update()
    {
        var aliveSibs = 0
        var deadSibs =  0
        for((rowIndex,rowArray) in gameBoard.withIndex()){ //(row index, array of rows)
            for((cellIndex,cellState) in rowArray.withIndex()){ // (cell index in row array, state of cell)
                when (rowIndex != 0){ // top row does not have any north siblings
                    //north sib
                    gameBoard[rowArray[cellIndex].northSib.col][rowArray[cellIndex].northSib.row].isDead() -> deadSibs+=1
                    gameBoard[rowArray[cellIndex].northSib.col][rowArray[cellIndex].northSib.row].isAlive() -> aliveSibs+=1

                    //north west sib -> first column doesn't have any northwest sibs,
                    cellIndex!=0 &&
                            gameBoard[rowArray[cellIndex].northWestSib.col][rowArray[cellIndex].northWestSib.row].isDead() -> deadSibs+=1
                    cellIndex!=0 &&
                            gameBoard[rowArray[cellIndex].northWestSib.col][rowArray[cellIndex].northWestSib.row].isAlive() -> aliveSibs+=1

                    // north east sib -> last column doesn't have any north east sibs.
                    cellIndex!= rowArray.size &&
                            gameBoard[rowArray[cellIndex].northEastSib.col][rowArray[cellIndex].northEastSib.row].isDead() -> deadSibs+=1
                    cellIndex!= rowArray.size &&
                            gameBoard[rowArray[cellIndex].northEastSib.col][rowArray[cellIndex].northEastSib.row].isAlive() -> aliveSibs+=1
                }

                when(rowIndex == rowArray.size){ // bottom row will not have any south siblings
                    // south sib
                    gameBoard[rowArray[cellIndex].southSib.col][rowArray[cellIndex].southSib.row].isDead() -> deadSibs+=1
                    gameBoard[rowArray[cellIndex].southSib.col][rowArray[cellIndex].southSib.row].isAlive() -> aliveSibs+=1

                    // south west sib ->
                }

            /*for(cell in row){
                // north sib -> The top row will not have a north sib
                if (gameBoard[cell.northSib.y][cell.northSib.x].isDead()) {deadSibs+=1}
                    else if (gameBoard[cell.northSib.x][cell.northSib.y].isAlive()) {aliveSibs+=1}
                // north east sib -> top row, and rightmost column will not have a north sib
                if (gameBoard[cell.northEastSib.y][cell.northEastSib.x].isDead()) {deadSibs+=1}
                    else if (gameBoard[cell.northEastSib.y][cell.northEastSib.x].isAlive()) {aliveSibs+=1}
                // north west sib -> top row, and leftmost column will not have a north west sib
                if (gameBoard[cell.northWestSib.y][cell.northWestSib.x].isDead()) {deadSibs+=1}
                    else if (gameBoard[cell.northWestSib.y][cell.northWestSib.x].isAlive()) {aliveSibs+=1}
                // east sib -> the rightmost column will not have a east sib
                if (gameBoard[cell.eastSib.y][cell.eastSib.y].isDead()) {deadSibs+=1}
                    else if (gameBoard[cell.eastSib.x][cell.eastSib.y].isAlive()) {aliveSibs+=1}
                // west sib -> the leftmost column will not have a east sib
                if (gameBoard[cell.westSib.y][cell.westSib.y].isDead()) {deadSibs+=1}
                    else if (gameBoard[cell.westSib.y][cell.westSib.x].isAlive()) {aliveSibs+=1}
                // south sib -> bottom row will not have a south sib
                if (gameBoard[cell.southSib.y][cell.southSib.y].isDead()) {deadSibs+=1}
                    else if (gameBoard[cell.southSib.y][cell.southSib.x].isAlive()) {aliveSibs+=1}
                // south east sib -> bottom row and rightmost column wil not have a south east sib
                if (gameBoard[cell.southEastSib.y][cell.southEastSib.x].isDead()) {deadSibs+=1}
                    else if (gameBoard[cell.southEastSib.y][cell.southEastSib.x].isAlive()) {aliveSibs+=1}
                // south west sib -> bottom row and leftmost column wil not have a south east sib
                if (gameBoard[cell.southWestSib.y][cell.southWestSib.y].isDead()) {deadSibs+=1}
                    else if (gameBoard[cell.southWestSib.x][cell.southWestSib.y].isAlive()) {aliveSibs+=1}

                // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
                if(deadSibs == 3 && gameBoard[cell.gridPos.x][cell.gridPos.y].isDead()) {
                    gameBoard[cell.gridPos.x][cell.gridPos.y].resurrect()
                    // Any live cell with two or three neighbors survives.
                }else if ((aliveSibs == 2 || aliveSibs == 3) && gameBoard[cell.gridPos.x][cell.gridPos.y].isAlive()){
                    gameBoard[cell.gridPos.x][cell.gridPos.y].resurrect()
                    // All other live cells die in the next generation. Similarly, all other dead cells stay dead.
                }else{
                    gameBoard[cell.gridPos.x][cell.gridPos.y].kill()
                }



                aliveSibs = 0
                deadSibs = 0
                */
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