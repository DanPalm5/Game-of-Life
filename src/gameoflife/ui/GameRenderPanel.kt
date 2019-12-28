package gameoflife.ui

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import javax.swing.JPanel

class GameRenderPanel(parent: GameWindow) : JPanel()
{

    // Dimension of entity cell in pixels
    private var cellSize: Pair<Int, Int> = Pair(
        size.width / parent.gameInstance.boardSize.first,
        size.height / parent.gameInstance.boardSize.second
        )

    init
    {

        addComponentListener(object: ComponentAdapter()
        {
            override fun componentResized(e: ComponentEvent?)
            {

                cellSize = Pair(
                    size.width / parent.gameInstance.boardSize.first,
                    size.height / parent.gameInstance.boardSize.second
                )

            }
        })

    }

    override fun paintComponent(g: Graphics?)
    {
        super.paintComponent(g)

        val g2d = g as Graphics2D


    }

}