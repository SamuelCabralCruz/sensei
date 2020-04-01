package ca.ulaval.glo.persistence

import ca.ulaval.glo.model.Review
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project
import org.apache.http.annotation.Obsolete

@State(
    name = "ReviewPersistence",
    storages = [Storage(value = "review.ipr")]
)
class ReviewPersistence : PersistentStateComponent<Review> {
    private var stateValue = Review()

    override fun getState(): Review? {
        return stateValue
    }

    override fun loadState(state: Review) {
        stateValue = state
    }

    companion object {
        @Obsolete
        fun getInstance(project: Project): PersistentStateComponent<Review> {
            return project.getService(ReviewPersistence::class.java)
        }
    }
}
