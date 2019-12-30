package gameoflife.ui

import gameoflife.game.Game
import gameoflife.util.Size
import java.awt.Dimension
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame
import javax.swing.JPanel
import kotlin.system.exitProcess

/**
 * Window for handling a game instance
 */
class GameWindow(windowSize: Size<Int>, val gameInstance: Game) : JFrame()
{

    private val panel: GameRenderPanel = GameRenderPanel(this)

    companion object
    {
        // Number of windows that are currently open
        private var instances: Int = 0
    }

    init
    {
        ++instances

        title = "Game of Life"
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE

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

        panel.preferredSize = Dimension(windowSize.width, windowSize.height)
        add(panel)

        pack()
        setLocationRelativeTo(null)
    }

}