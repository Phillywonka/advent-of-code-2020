package days

import java.math.BigInteger


class Day6 : Day(6) {

    override fun solvePart1(input: List<String>): BigInteger {
        return getAnswers(input).map {
            val distinct = it.trim().toCharArray().distinct()
            distinct.size
        }.sum().toBigInteger()
    }

    override fun solvePart2(input: List<String>): BigInteger {
        return getGroupedAnswers(input).map {
            val entry = it.second
            val count = it.first
            // for each distinct char in the entry check if the char occurs as many times as the count
            entry.toCharArray().distinct().count { char -> entry.count { innerChar -> innerChar == char } == count }
        }.sum().toBigInteger()
    }

    private fun getAnswers(input: List<String>): List<String> {
        return input
            .mapIndexedNotNull { lineIndex, line ->
                if (line.isBlank() || lineIndex == input.lastIndex) {
                    val sublist = input.take(lineIndex)
                    val previousBlankLine = sublist.lastIndexOf("")
                        .takeIf { it != -1 } ?: 0
                    Pair(previousBlankLine, lineIndex)
                } else null
            }
            .map { value ->
                input.subList(value.first, value.second + 1).filter { it.isNotBlank() }.joinToString("")
            }
    }

    private fun getGroupedAnswers(input: List<String>): List<Pair<Int, String>> {
        return input
            .mapIndexedNotNull { lineIndex, line ->
                if (line.isBlank() || lineIndex == input.lastIndex) {
                    val sublist = input.take(lineIndex)
                    val previousBlankLine = sublist.lastIndexOf("")
                        .takeIf { it != -1 } ?: 0
                    Pair(previousBlankLine, lineIndex)
                } else null
            }
            .map { value ->
                val allAnswers = input.subList(value.first, value.second + 1).filter { it.isNotBlank() }
                allAnswers.size to allAnswers.joinToString("")
            }
    }
}
