package gameoflife.main

import gameoflife.game.Game

fun main(args: Array<String>)
{
    val game = Game(Pair(20,20))
    game.run()
}