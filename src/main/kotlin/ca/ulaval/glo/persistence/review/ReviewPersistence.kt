package ca.ulaval.glo.persistence.review

import ca.ulaval.glo.persistence.review.state.Review
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.Storage

@Storage("review.xml")
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
