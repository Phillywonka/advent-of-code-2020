package days

import java.lang.Exception
import java.math.BigInteger
import kotlin.math.roundToInt

class Day5: Day(5) {

    override fun solvePart1(input: List<String>): BigInteger {
        return input.maxOf {
            getSeat(getRow(it.substring(0, 7)), getColumn(it.substring(7, 10)))
        }.toBigInteger()
    }

    override fun solvePart2(input: List<String>): BigInteger {
        val sorted = input.map { getSeat(getRow(it.substring(0, 7)), getColumn(it.substring(7, 10))) }.sorted()
        return sorted
            .find { seat ->
                sorted.indexOf(seat) != 0 && !sorted.contains(seat - 1)
            }?.let {(it - 1).toBigInteger()} ?: throw Exception("Seat not found")
    }

    fun getRow(input: String): Int {
        return partition(input, 'F', 'B', 127)
    }

    fun getColumn(input: String): Int {
        return partition(input, 'L', 'R', 7)
    }

    fun partition(input: String, lowChar: Char, highChar: Char, max: Int): Int {
        var rows = Pair(0, max)

        input.forEachIndexed { index, char ->
            if (char == lowChar) {
                rows = rows.run { Pair(first, second - ((second - first) / 2.0).roundToInt()) }
                if (index == input.lastIndex) {
                    return rows.first
                }
            } else if (char == highChar) {
                rows = rows.run { Pair(first + ((second - first) / 2.0).roundToInt(), second) }
                if (index == input.lastIndex) {
                    return rows.second
                }
            }
        }

        return -1
    }

    fun getSeat(row: Int, column: Int): Int {
        return row * 8 + column
    }

}

