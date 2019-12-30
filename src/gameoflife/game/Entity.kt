package gameoflife.game

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
class Entity(private val initState: EntityState = EntityState.DEAD, val position: Pair<Int, Int>)
{

    var state: EntityState = initState
    /**
     * Game board positions for entity siblings
     */

    val northSib: Pair<Int,Int> = Pair(position.first-1, position.second)

    val northEastSib: Pair<Int, Int> = Pair(position.first-1, position.second+1)

    val eastSib: Pair<Int,Int> = Pair(position.first, position.second+1)

    val southEastSib: Pair<Int,Int> = Pair(position.first+1, position.second+1)

    val southSib: Pair<Int, Int> = Pair(position.first+1, position.second)

    val southWestSib: Pair<Int, Int> = Pair(position.first+1, position.second-1)

    val westSib: Pair<Int,Int> = Pair(position.first, position.second-1)

    val northWestSib: Pair<Int, Int> = Pair(position.first-1, position.second-1)
}