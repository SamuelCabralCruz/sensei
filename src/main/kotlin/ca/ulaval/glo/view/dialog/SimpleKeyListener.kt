package ca.ulaval.glo.view.dialog

import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class SimpleKeyListener(private val keyReleasedCallback: (event: KeyEvent?) -> Unit) : KeyListener {
    override fun keyTyped(p0: KeyEvent?) {
    }

    override fun keyPressed(p0: KeyEvent?) {
    }

    override fun keyReleased(p0: KeyEvent?) {
        keyReleasedCallback(p0)
    }
}
