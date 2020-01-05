package gameoflife.ui

import gameoflife.game.Game
import gameoflife.util.SaveData
import gameoflife.util.Size
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.event.*
import javax.swing.*
import javax.swing.filechooser.FileNameExtensionFilter
import kotlin.math.roundToInt
import kotlin.system.exitProcess

/**
 * Window for handling a game instance
 */
class GameWindow(windowSize: Size<Int>) : JFrame()
{

    companion object
    {
        private const val StartIconPath = "./StartIcon.png"
        private const val StopIconPath = "./StopIcon.png"
        private const val DefaultSavePath = "./resources/DefaultGameState.save"

        // Number of windows that are currently open
        private var instances: Int = 0

        private val startIcon: ImageIcon = ImageIcon(StartIconPath)
        private val stopIcon: ImageIcon = ImageIcon(StopIconPath)
    }

    var gameInstance = Game(SaveData.readSaveData(DefaultSavePath))
    private val renderPanel: GameRenderPanel = GameRenderPanel(this)
    private val delayField: JTextField = JTextField("1", 4)
    private val startStopButton: JButton = JButton("Start")
    private val saveButton: JButton = JButton("Save game state")
    private val loadButton: JButton = JButton("Load game state")

    private var tickDelay: Int = 1000
    private val gameTicker: Timer = Timer(tickDelay, ActionListener
    {
        gameInstance.update()
        renderPanel.repaint()
    })

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

        gameTicker.initialDelay = 0

        // Add all items to window

        layout = GridBagLayout()
        // Layout config for each object. Note that all fields get reset after add(..)
        val c = GridBagConstraints()

        var gridx = 0
        var gridy = 0
        var gridwith = 0

        // Start/Stop button
        c.fill = GridBagConstraints.HORIZONTAL
        c.gridx = ++gridx
        c.gridy = gridy
        ++gridwith

        //startStopButton.icon = startIcon
        startStopButton.horizontalAlignment = SwingConstants.LEFT
        startStopButton.addActionListener { toggleGame() }
        add(startStopButton, c)

        // Update delay field
        c.fill = GridBagConstraints.HORIZONTAL
        c.weightx = 1.0
        c.gridx = ++gridx
        c.gridy = gridy
        ++gridwith
        add(delayField, c)

        // Save game button
        c.fill = GridBagConstraints.HORIZONTAL
        c.weightx = 1.0
        c.gridx = ++gridx
        c.gridy = gridy
        ++gridwith
        saveButton.addActionListener { saveGameState() }
        add(saveButton, c)

        // Load game button
        c.fill = GridBagConstraints.HORIZONTAL
        c.weightx = 1.0
        c.gridx = ++gridx
        c.gridy = gridy
        ++gridwith
        loadButton.addActionListener { loadGameState() }
        add(loadButton, c)

        // Render panel
        c.fill = GridBagConstraints.HORIZONTAL
        c.gridx = 1
        c.gridy = ++gridy
        c.gridwidth = gridwith++

        renderPanel.preferredSize = Dimension(windowSize.width, windowSize.height)
        add(renderPanel, c)

        pack()
        setLocationRelativeTo(null)

        /// This needs to be attached to a start/stop button. Its here for debugging for now
//        gameTicker.delay = tickDelay
//        gameTicker.start()
    }

    private var gameRunning = false
    private fun toggleGame()
    {

        tickDelay = ((delayField.text?.toFloatOrNull() ?: 1.0f) * 1000.0).roundToInt()

        if(!gameRunning)
        {
            //startStopButton.icon = stopIcon
            startStopButton.text = "Stop"
            gameTicker.delay = tickDelay
            gameTicker.restart()
            gameRunning = true
        }
        else
        {
            //startStopButton.icon = startIcon
            startStopButton.text = "Start"
            gameTicker.stop()
            gameRunning = false
        }
    }

    private fun saveGameState()
    {
        val fileDialog = JFileChooser()
        if(fileDialog.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            val path = fileDialog.selectedFile?.path
            if(path != null)
            {
                SaveData.writeSaveData(path, gameInstance.gameBoard)
            }
        }
    }

    private fun loadGameState()
    {
        val fileDialog = JFileChooser()
        if(fileDialog.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            val path = fileDialog.selectedFile?.path
            if(path != null)
            {
                gameInstance = Game(SaveData.readSaveData(path))
                renderPanel.repaint()
            }
        }
    }

}