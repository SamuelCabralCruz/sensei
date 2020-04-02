package ca.ulaval.glo.model

class ReviewDetails() {
    var reviewer: String? = null
    var teamNumber: Int? = null
    var evaluationName: String? = null

    constructor(_reviewer: String?, _teamNumber: Int?, _evaluationName: String?) : this() {
        reviewer = _reviewer
        teamNumber = _teamNumber
        evaluationName = _evaluationName
    }

    fun reset() {
        reviewer = null
        teamNumber = null
        evaluationName = null
    }
}
