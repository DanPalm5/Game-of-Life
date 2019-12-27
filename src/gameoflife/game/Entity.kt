package gameoflife.game

enum class EntityState(val state: Int)
{
    DEAD(0), ALIVE(1)
}

class Entity(val initState: EntityState = EntityState.DEAD, val position: Pair<Int, Int>)
{



    fun topRightSib(): Int?
    {
        return null
    }

}