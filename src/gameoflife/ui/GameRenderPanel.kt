package gameoflife.ui

import gameoflife.game.Entity
import gameoflife.game.EntityState
import gameoflife.util.Position
import gameoflife.util.Size
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import javax.swing.JPanel

class GameRenderPanel(private val parentWindow: GameWindow) : JPanel()
{

    private fun calcCellSize(): Size<Int>
    {
        return Size(
            size.width / parentWindow.gameInstance.boardSize.width,
            size.height / parentWindow.gameInstance.boardSize.height
        )
    }


    // Dimension of entity cell in pixels
    private var cellSize: Size<Int> = calcCellSize()

    init
    {

        addComponentListener(object: ComponentAdapter()
        {
            override fun componentResized(e: ComponentEvent?)
            {

                cellSize = calcCellSize()

            }
        })

    }

    override fun paintComponent(g: Graphics?)
    {
        super.paintComponent(g)

        val g2d = g as Graphics2D
        g2d.background = Color.BLACK
        g2d.color = Color.WHITE

        for(row in parentWindow.gameInstance.gameBoard.indices)
        {
            for(col in parentWindow.gameInstance.gameBoard[row].indices)
            {
                // Skip over entity if its dead
                if(parentWindow.gameInstance.gameBoard[row][col].state == EntityState.DEAD)
                    continue

                // Draw a white rectangle for entity if its alive
                val topLeft = Position(col * cellSize.width, row * cellSize.height)
                val bottomRight = Position((col+1) * cellSize.width, (row+1) * cellSize.height)

                g2d.drawRect(topLeft.x, topLeft.y, bottomRight.x, bottomRight.y)
            }
        }

    }

}