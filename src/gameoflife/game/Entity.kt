package gameoflife.game

import gameoflife.util.GridPos

enum class EntityState(private val state: Int)
{
    DEAD(0)
        {
            override fun toString(): String = "DEAD"
        },
    ALIVE(1)
        {
            override fun toString(): String = "ALIVE"
        };

    abstract override fun toString(): String
}

/**
 * Entity populating a cell of the game board
 * Position is (col, row)
 */
class Entity(initState: EntityState = EntityState.DEAD, val position: GridPos<Int>)
{

    private var state: EntityState = initState
    fun isAlive()   = state == EntityState.ALIVE
    fun isDead()    = state == EntityState.DEAD
    fun kill()      { state = EntityState.DEAD }
    fun resurrect() { state = EntityState.ALIVE }
    fun toggleState() { state = if(isAlive()) EntityState.DEAD else EntityState.ALIVE }

    /**
     * Game board positions for entity siblings
     */

    val northSib: GridPos<Int> by lazy { GridPos(position.col, position.row-1) }

    val northEastSib: GridPos<Int> by lazy { GridPos(position.col+1, position.row-1) }

    val eastSib: GridPos<Int> by lazy { GridPos(position.col+1, position.row) }

    val southEastSib: GridPos<Int> by lazy { GridPos(position.col+1, position.row+1) }

    val southSib: GridPos<Int> by lazy { GridPos(position.col, position.row+1) }

    val southWestSib: GridPos<Int> by lazy { GridPos(position.col-1, position.row+1) }

    val westSib: GridPos<Int> by lazy { GridPos(position.col-1, position.row) }

    val northWestSib: GridPos<Int> by lazy { GridPos(position.col-1, position.row-1) }

    fun getSib(array: Array<Array<Entity>>, pos: GridPos<Int>): Entity?
    {
        return array.getOrNull(pos.row)?.getOrNull(pos.col)
    }

    override fun toString(): String = "Entity(state = $state, pos = $position)"
}