package gameoflife.ui

import gameoflife.game.Game
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame
import javax.swing.JPanel
import kotlin.system.exitProcess

/**
 * Window for handling a game instance
 */
class Window(windowSize: Pair<Int, Int>, gameInstance: Game) : JFrame()
{

    private val panel: JPanel = JPanel()

    companion object
    {
        // Number of windows that are currently open
        private var instances: Int = 0
    }

    init
    {
        ++instances

        setTitle("Game of Life")
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setSize(windowSize.first, windowSize.second)

        // On window close, exit the entire program if the current window
        // being closed is the last one open
        addWindowListener(object: WindowAdapter()
        {
            override fun windowClosing(e: WindowEvent?)
            {
                dispose()
                if(--instances == 0)
                {
                    exitProcess(0)
                }
            }
        })

        pack()
    }

}