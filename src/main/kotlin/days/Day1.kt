package days

import java.math.BigInteger

class Day1 : Day(1) {


    override fun solvePart1(input: List<String>): BigInteger {
        val numbers = input.map { it.toInt() }
        numbers.forEachIndexed { index, entry ->
            val result = numbers.getSumOf2020(index, entry)
            if (result != null) {
                return result.toBigInteger()
            }
        }

        throw Exception("Numbers not found!")
    }

    override fun solvePart2(input: List<String>): BigInteger {
        val numbers = input.map { it.toInt() }
        numbers.forEachIndexed { index, entry ->
            val nested = numbers.filterIndexed { i, _ -> i != index }

            nested.forEachIndexed { nestedIndex, nestedInputEntry ->
                val result = nested.getSumOf2020(nestedIndex, entry, nestedInputEntry)
                if (result != null) {
                    return result.toBigInteger()
                }
            }

        }
        throw Exception("Numbers not found!")
    }

    private fun List<Int>.getSumOf2020(index: Int, vararg inputEntry: Int): Int? {
        this.filterIndexed { i, _ -> i != index }.forEach { subListEntry ->
            if (inputEntry.sum() + subListEntry == 2020) {
                return inputEntry.scan(1) { number, acc -> number * acc }.last() * subListEntry
            }
        }

        return null
    }

}

