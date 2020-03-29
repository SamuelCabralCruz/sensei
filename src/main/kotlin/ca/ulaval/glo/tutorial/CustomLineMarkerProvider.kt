package ca.ulaval.glo.tutorial

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.psi.PsiElement
import com.intellij.util.PlatformIcons.JAR_ICON

class CustomLineMarkerProvider : RelatedItemLineMarkerProvider() {
    override fun collectNavigationMarkers(
        element: PsiElement,
        result: MutableCollection<in RelatedItemLineMarkerInfo<PsiElement>>
    ) {
        if (element.textRange.startOffset == 118) {
            result.add(
                NavigationGutterIconBuilder.create(JAR_ICON).setTarget(element).setTooltipText("here is my tooltip")
                    .createLineMarkerInfo(element)
            )
        }
    }
}
