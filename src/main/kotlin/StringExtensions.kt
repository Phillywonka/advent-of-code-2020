/** Returns the string where line separators are replaced by `sep` (default empty string). */
fun String.oneLine(sep: String = "") = replace(System.lineSeparator(), sep)

/** Returns the set of characters in this string. */
fun String.charSet() = split("").toSet() - ""

/** Replace multiple characters in a [String].*/
fun String.replace(vararg replacements: Pair<String, String>) =
    replacements.fold(this) { acc, (old, new) -> acc.replace(old, new, ignoreCase = true) }

fun String.matches(regex: Regex) = regex.matches(this)
