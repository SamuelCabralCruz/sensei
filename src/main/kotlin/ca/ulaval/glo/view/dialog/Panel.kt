package ca.ulaval.glo.view.dialog

import com.intellij.uiDesigner.core.AbstractLayout
import com.intellij.util.ui.GridBag
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JPanel

class Panel(nbColumnsGrid: Int = 1) : JPanel(GridBagLayout()) {
    val gridBag: GridBag = GridBag()
        .setDefaultInsets(0, 1, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP)
        .setDefaultWeightX(1.0)
        .setDefaultFill(GridBagConstraints.HORIZONTAL)

    constructor(minWight: Int, minHeight: Int, nbColumnsGrid: Int) : this(nbColumnsGrid) {
        preferredSize = Dimension(minWight, minHeight)
        minimumSize = Dimension(minWight, minHeight)
    }
}
