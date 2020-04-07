package ca.ulaval.glo.model.review.comment

import ca.ulaval.glo.model.ValueObject

abstract class ReviewComment : ValueObject() {
    var details = ReviewCommentDetails()
}
