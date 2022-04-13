fun String.toArrayOfIntArray() = removeSurrounding("[", "]")
    .removeSurrounding("[", "]")
    .split("],[")
    .map { it.split(",").map { it.toInt() }.toIntArray() }
    .toTypedArray()

fun String.toArrayOfStringArray() = removeSurrounding("[", "]")
    .removeSurrounding("[", "]")
    .split("],[")
    .map { it.split(",").map { it.removeSurrounding("") } }
    .toTypedArray()