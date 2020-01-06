package gameoflife.ui

import gameoflife.game.Entity
import gameoflife.game.EntityState
import gameoflife.util.GridPos
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

    fun updateCellSize()
    {
        cellSize = Size(
            size.width / parentWindow.gameInstance.boardSize.width,
            size.height / parentWindow.gameInstance.boardSize.height
        )
    }


    // Dimension of entity cell in pixels
    private var cellSize: Size<Int> = Size(0,0)

    init
    {
        background = Color.BLACK
        updateCellSize()

        // Update cell size function on window resize
        addComponentListener(object: ComponentAdapter()
        {
            override fun componentResized(e: ComponentEvent?) { updateCellSize() }
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

        for((rowIndex, rowArr) in parentWindow.gameInstance.gameBoard.withIndex())
        {
            for((colIndex, entity) in rowArr.withIndex())
            {
                if(entity.isDead())
                    g2d.color = Color.BLACK
                else
                    g2d.color = Color.WHITE

                val topLeft = Position(colIndex * cellSize.width, rowIndex * cellSize.height)
                val bottomRight = Position((colIndex+1) * cellSize.width, (rowIndex+1) * cellSize.height)

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
    private fun pixelsToGrid(pos: Position<Int>) : GridPos<Int> = GridPos(pos.x/cellSize.width, pos.y/cellSize.height)
    private fun getEntity(gridPos: GridPos<Int>): Entity? = parentWindow.gameInstance.gameBoard.getOrNull(gridPos.row)?.getOrNull(gridPos.col)

    /**
     * Functions for toggling entity states with user input
     */
    // The last entity that was sampled, to prevent rapid state changes on mouse drag within the same cell
    private var lastSelection: Entity? = null
    // The state of the entity that was initially clicked on. This is to allow "painting" with the mouse and not have
    // entities modified in the same stroke be affected more than once
    private var selectedState: EntityState? = null
    private fun handleMousePressed(e: MouseEvent?)
    {
        if(e != null)
        {
            val pos = Position(e.x, e.y)
            val gridPos = pixelsToGrid(pos)
            lastSelection = getEntity(gridPos)
            selectedState = lastSelection?.state
            lastSelection?.toggleState()

//            println("Mouse Press:")
//            println("\tPixels -> $pos\n\tGrid -> $gridPos\n\tEntity -> $lastSelection")
        }

        repaint()
    }

    private fun handleMouseReleased(e: MouseEvent?)
    {
        lastSelection = null
        selectedState = null

        repaint()
    }

    private fun handleMouseDragged(e: MouseEvent?)
    {
        if(e != null)
        {
            val pos = Position(e.x, e.y)
            val gridPos = pixelsToGrid(pos)
            val entity = getEntity(gridPos)

            if(entity != lastSelection && entity?.state == selectedState)
            {
                lastSelection = entity
                entity?.toggleState()

//                println("Mouse Drag:")
//                println("\tPixels -> $pos\n\tGrid -> $gridPos\n\tEntity -> $entity")
            }
        }

        repaint()
    }
}