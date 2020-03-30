package ca.ulaval.glo.persistence.review

import ca.ulaval.glo.persistence.review.state.Review
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@State(name = "ReviewPersistence", storages = [Storage("review.xml")])
class ReviewPersistence : PersistentStateComponent<Review> {
    private var stateValue = Review()

    override fun getState(): Review? {
        return stateValue
    }

    override fun loadState(state: Review) {
        stateValue = state
    }

    companion object {
        @JvmStatic
        fun getInstance(): PersistentStateComponent<Review> {
            return ServiceManager.getService(ReviewPersistence::class.java)
        }
    }
}
