package days

import java.math.BigInteger

abstract class Day(private val day: Int) {

    abstract fun solvePart1(input: List<String>): BigInteger
    abstract fun solvePart2(input: List<String>): BigInteger

    fun solve() {
        val input = Resources.getInputForDay(day)

        println(
            """
            Day $day answers
            ----------------
            Part 1: ${solvePart1(input)}
            Part 2: ${solvePart2(input)} 
        """.trimIndent()
        )
    }
}