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

class Entity(val initState: EntityState = EntityState.DEAD, val position: Pair<Int, Int>)
{


    // row, col

    val topRightSib: Pair<Int, Int> = Pair(position.first-1, position.second+1)

    val topSib: Pair<Int,Int> = Pair(position.first-1, position.second)

    val topLeftSib: Pair<Int, Int> = Pair(position.first-1, position.second-1)

    val rightSib: Pair<Int,Int> = Pair(position.first, position.second+1)

    val leftSib: Pair<Int,Int> = Pair(position.first, position.second-1)

    val lowerRightSib: Pair<Int,Int> = Pair(position.first+1, position.second+1)

    val lowerLeftSib: Pair<Int, Int> = Pair(position.first+1, position.second-1)



}