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

    var state: EntityState = initState
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
}