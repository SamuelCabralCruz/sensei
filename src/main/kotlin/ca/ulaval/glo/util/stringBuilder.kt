package ca.ulaval.glo.util

fun wrap(text: String, maxLength: Int): String {
    var currentLength = 0
    var output = StringBuilder()
    text.split(" ").forEach(fun(word) {
        currentLength += word.length + 1
        if (currentLength > maxLength) {
            currentLength = if (currentLength - maxLength > word.length / 2) {
                output.append("\n").append(word)
                0
            } else {
                output.append(" ").append(word).append("\n")
                word.length + 1
            }
        } else {
            output.append(" ").append(word)
        }
    })
    return output.toString()
}

fun prepend(text: String, prefix: String): String {
    return text.split("\n").joinToString("\n" + prefix)
}
