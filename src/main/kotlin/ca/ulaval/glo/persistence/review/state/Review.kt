package ca.ulaval.glo.persistence.review.state

class Review {
    var comments = mutableMapOf<String, MutableList<ReviewComment>>()
}
