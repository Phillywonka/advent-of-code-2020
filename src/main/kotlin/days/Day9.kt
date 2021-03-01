package days

import java.math.BigInteger


class Day9 : Day(9) {

    override fun solvePart1(input: List<String>): BigInteger {
        val numbers = input.map { it.toInt() }
        return getInvalidNumber(numbers, 5).toBigInteger()
    }

    private fun getInvalidNumber(numbers: List<Int>, preambleLength: Int): Int {
        numbers.forEachIndexed { index, number ->
            if (index > preambleLength) {
                val checkSumNumbers = numbers.subList(index - preambleLength, index)

                val isValid = checkSumNumbers.any { checkSumNumber ->
                    checkSumNumbers.filterNot { checkSumNumber == it }.any {
                        it.plus(checkSumNumber) == number
                    }
                }

                if (!isValid) {
                    return number
                }
            }
        }

        throw Exception("Number not found")
    }

    override fun solvePart2(input: List<String>): BigInteger {
        // Get the first list of contiguous numbers that are the sum of the invalid number
        val numbers = input.map { it.toInt() }
        val invalidNumber = getInvalidNumber(numbers, 25)

        // Iterate through numbers while we haven't found the contiguous numbers
        // for each item sum it until it is bigger then the invalid number
        var endIndex = 1

        for (i in 0..numbers.lastIndex) {
            while (true) {
                val sublist = numbers.subList(i, i + endIndex)
                val sum = sublist.sumOf { it }

                if (sum == invalidNumber) {
                    val first = numbers[i]
                    val last = sublist.maxOf { it }

                    return (first + last).toBigInteger()
                } else if (sum > invalidNumber) {
                    endIndex = 0
                    break
                } else {
                    endIndex++
                }
            }
        }

        throw Exception("Sum not found")
    }

}


