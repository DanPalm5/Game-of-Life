package gameoflife.game

import gameoflife.util.Position

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
 * Position is (row,column)
 */
class Entity(initState: EntityState = EntityState.DEAD, val position: Position<Int>)
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

    val northSib: Pair<Int,Int> = Pair(position.x-1, position.y)

    val northEastSib: Pair<Int, Int> = Pair(position.x-1, position.y+1)

    val eastSib: Pair<Int,Int> = Pair(position.x, position.y+1)

    val southEastSib: Pair<Int,Int> = Pair(position.x+1, position.y+1)

    val southSib: Pair<Int, Int> = Pair(position.x+1, position.y)

    val southWestSib: Pair<Int, Int> = Pair(position.x+1, position.y-1)

    val westSib: Pair<Int,Int> = Pair(position.x, position.y-1)

    val northWestSib: Pair<Int, Int> = Pair(position.x-1, position.y-1)

    override fun toString(): String = "Entity(state = $state, pos = $position)"
}