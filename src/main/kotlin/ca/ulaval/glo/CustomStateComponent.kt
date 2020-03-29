package ca.ulaval.glo

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@State(name = "custom state component", storages = [Storage("customStateComponent.xml")])
class CustomStateComponent : PersistentStateComponent<CustomState> {
    private var stateValue = CustomState()

    override fun getState(): CustomState? {
        return stateValue
    }

    override fun loadState(state: CustomState) {
        stateValue = state
    }

    companion object {
        @JvmStatic
        fun getInstance(): PersistentStateComponent<CustomState> {
            return ServiceManager.getService(CustomStateComponent::class.java)
        }
    }
}
