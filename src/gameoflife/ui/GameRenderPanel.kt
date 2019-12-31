package gameoflife.ui

import gameoflife.game.Entity
import gameoflife.util.Position
import gameoflife.util.Size
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
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
        background = Color.BLACK

        // Update cell size function on window resize
        addComponentListener(object: ComponentAdapter()
        {
            override fun componentResized(e: ComponentEvent?) { cellSize = calcCellSize() }
        })

        // Mouse handler
        val mouseListener: MouseAdapter = object: MouseAdapter()
        {
            override fun mousePressed(e: MouseEvent?) = handleMousePressed(e)
            override fun mouseReleased(e: MouseEvent?) = handleMouseReleased(e)
            override fun mouseDragged(e: MouseEvent?) = handleMouseDragged(e)
        }

        addMouseListener(mouseListener)
        addMouseMotionListener(mouseListener)
    }

    override fun paintComponent(g: Graphics?)
    {
        super.paintComponent(g)

        val g2d = g as Graphics2D

        // Draw rectangle for entity
        for(row in parentWindow.gameInstance.gameBoard.indices)
        {
            for(col in parentWindow.gameInstance.gameBoard[row].indices)
            {
                // Skip over entity if its dead
                if(parentWindow.gameInstance.gameBoard[row][col].isDead())
                    g2d.color = Color.BLACK
                else
                    g2d.color = Color.WHITE


                val topLeft = Position(col * cellSize.width, row * cellSize.height)
                val bottomRight = Position((col+1) * cellSize.width, (row+1) * cellSize.height)

                g2d.fillRect(topLeft.x, topLeft.y, bottomRight.x, bottomRight.y)
            }
        }

        // Draw grid
        g2d.color = Color.DARK_GRAY
        for(row in parentWindow.gameInstance.gameBoard.indices)
        {
            val y = row * cellSize.height
            g2d.drawLine(0, y, size.width, y)
        }

        for(col in parentWindow.gameInstance.gameBoard[0].indices)
        {
            val x = col * cellSize.width
            g2d.drawLine(x, 0, x, size.height)
        }
    }

    /**
     * Converts pixels
     */
    private fun pixelsToGrid(pos: Position<Int>) : Position<Int> = Position(pos.x/cellSize.width, pos.y/cellSize.height)
    private fun getEntity(gridPos: Position<Int>): Entity? = parentWindow.gameInstance.gameBoard.getOrNull(gridPos.y)?.getOrNull(gridPos.x)

    /**
     * Functions for toggling entity states with user input
     */
    private var lastSelection: Entity? = null
    private fun handleMousePressed(e: MouseEvent?)
    {
        if(e != null)
        {
            val pos = Position(e.x, e.y)
            val gridPos = pixelsToGrid(pos)
            lastSelection = getEntity(gridPos)
            lastSelection?.toggleState()

            println("Mouse Press:")
            println("\tPixels -> $pos\n\tGrid -> $gridPos\n\tEntity -> $lastSelection")
        }

        repaint()
    }

    private fun handleMouseReleased(e: MouseEvent?)
    {
        lastSelection = null

        repaint()
    }

    private fun handleMouseDragged(e: MouseEvent?)
    {
        if(e != null)
        {
            val pos = Position(e.x, e.y)
            val gridPos = pixelsToGrid(pos)
            val currEntity = getEntity(gridPos)

            if(currEntity != lastSelection)
            {
                lastSelection = currEntity
                lastSelection?.toggleState()

                println("Mouse Drag:")
                println("\tPixels -> $pos\n\tGrid -> $gridPos\n\tEntity -> $lastSelection")
            }
        }

        repaint()
    }
}