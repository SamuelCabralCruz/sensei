package ca.ulaval.glo.tutorial

import ca.ulaval.glo.tutorial.CustomStateComponent
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBLabel
import com.intellij.uiDesigner.core.AbstractLayout
import com.intellij.util.ui.GridBag
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.UIUtil
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JPasswordField
import javax.swing.JTextField

class CustomDialogWrapper : DialogWrapper(true) {

    val panel = JPanel(GridBagLayout())
    val txtMode = JTextField()
    val txtUserName = JTextField()
    val txtPassword = JPasswordField()

    init {
        init()
        title = "Custom Dialog Wrapper Title"
        val state = CustomStateComponent.getInstance().state
        if (state != null) {
            txtMode.text = state.mode
            txtUserName.text = state.username
        }
    }

    override fun createCenterPanel(): JComponent? {
        val gb = GridBag()
            .setDefaultInsets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP)
            .setDefaultWeightX(1.0)
            .setDefaultFill(GridBagConstraints.HORIZONTAL)

        panel.preferredSize = Dimension(400, 200)

        panel.add(label("mode"), gb.nextLine().next().weightx(0.2))
        panel.add(txtMode, gb.next().weightx(0.8))
        panel.add(label("username"), gb.nextLine().next().weightx(0.2))
        panel.add(txtUserName, gb.next().weightx(0.8))
        panel.add(label("password"), gb.nextLine().next().weightx(0.2))
        panel.add(txtPassword, gb.next().weightx(0.8))

        return panel
    }

    override fun doOKAction() {
        val mode = txtMode.text
        val username = txtUserName.text
        val password = txtPassword.password.toString()
        val state = CustomStateComponent.getInstance().state
        state?.mode = mode
        state?.username = username
        state?.password = password
        close(0)
    }

    private fun label(text: String): JComponent {
        val label = JBLabel(text)
        label.componentStyle = UIUtil.ComponentStyle.SMALL
        label.fontColor = UIUtil.FontColor.BRIGHTER
        label.border = JBUI.Borders.empty(0, 5, 2, 0)
        return label
    }
}
