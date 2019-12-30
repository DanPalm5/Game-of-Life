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
        var aliveSibs = 0
        var deadSibs =  0
        for(row in gameBoard){

            for(cell in row){
                // north sib
                if (gameBoard[cell.northSib.first][cell.northSib.second].state == EntityState.DEAD) {deadSibs+=1}
                    else if (gameBoard[cell.northSib.first][cell.northSib.second].state == EntityState.ALIVE) {aliveSibs+=1}
                // north east sib
                if (gameBoard[cell.northEastSib.first][cell.northEastSib.second].state == EntityState.DEAD) {deadSibs+=1}
                    else if (gameBoard[cell.northEastSib.first][cell.northEastSib.second].state == EntityState.ALIVE) {aliveSibs+=1}
                // north west sib
                if (gameBoard[cell.northWestSib.first][cell.northWestSib.second].state == EntityState.DEAD) {deadSibs+=1}
                    else if (gameBoard[cell.northWestSib.first][cell.northWestSib.second].state == EntityState.ALIVE) {aliveSibs+=1}
                // east sib
                if (gameBoard[cell.eastSib.first][cell.eastSib.second].state == EntityState.DEAD) {deadSibs+=1}
                    else if (gameBoard[cell.eastSib.first][cell.eastSib.second].state == EntityState.ALIVE) {aliveSibs+=1}
                // west sib
                if (gameBoard[cell.westSib.first][cell.westSib.second].state == EntityState.DEAD) {deadSibs+=1}
                    else if (gameBoard[cell.westSib.first][cell.westSib.second].state == EntityState.ALIVE) {aliveSibs+=1}
                // south sib
                if (gameBoard[cell.southSib.first][cell.southSib.second].state == EntityState.DEAD) {deadSibs+=1}
                    else if (gameBoard[cell.southSib.first][cell.southSib.second].state == EntityState.ALIVE) {aliveSibs+=1}
                // south east sib
                if (gameBoard[cell.southEastSib.first][cell.southEastSib.second].state == EntityState.DEAD) {deadSibs+=1}
                    else if (gameBoard[cell.southEastSib.first][cell.southEastSib.second].state == EntityState.ALIVE) {aliveSibs+=1}
                // south west sib
                if (gameBoard[cell.southWestSib.first][cell.southWestSib.second].state == EntityState.DEAD) {deadSibs+=1}
                    else if (gameBoard[cell.southWestSib.first][cell.southWestSib.second].state == EntityState.ALIVE) {aliveSibs+=1}

                //Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
                if(deadSibs == 3 && gameBoard[cell.position.first][cell.position.second].state == EntityState.DEAD) {
                    gameBoard[cell.position.first][cell.position.second].state = EntityState.ALIVE
                // Any live cell with two or three neighbors survives.
                }else if ((aliveSibs == 2 || aliveSibs == 3) && gameBoard[cell.position.first][cell.position.second].state == EntityState.ALIVE){
                    gameBoard[cell.position.first][cell.position.second].state = EntityState.ALIVE
                // All other live cells die in the next generation. Similarly, all other dead cells stay dead.
                }else{
                    gameBoard[cell.position.first][cell.position.second].state = EntityState.DEAD
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