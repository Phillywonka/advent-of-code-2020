package days

import java.math.BigInteger

class Day2 : Day(2) {

    override fun solvePart1(input: List<String>): BigInteger {

        val regex = "^([0-9]+).([0-9]+) (.): (.*)".toRegex()
        var validPasswordCount = 0

        input.forEach { entry ->
            regex.matchEntire(entry)?.let { matchResult ->

                val min = matchResult.groups[1]!!.value.toInt()
                val max = matchResult.groups[2]!!.value.toInt()
                val char = matchResult.groups[3]!!.value.first()
                val password = matchResult.groups[4]!!.value

                val count = password.count { it == char }

                if (count in min..max) {
                    validPasswordCount++
                }
            }
        }

        return validPasswordCount.toBigInteger()
    }

    override fun solvePart2(input: List<String>): BigInteger {
        val regex = "^([0-9]+).([0-9]+) (.): (.*)".toRegex()
        var validPasswordCount = 0

        input.forEach { entry ->
            regex.matchEntire(entry)?.let { matchResult ->

                val firstPosition = matchResult.groups[1]!!.value.toInt() - 1
                val secondPosition = matchResult.groups[2]!!.value.toInt() - 1
                val char = matchResult.groups[3]!!.value.first()
                val password = matchResult.groups[4]!!.value

                val matchesFirst = password[firstPosition] == char
                val matchesSecond = password[secondPosition] == char

                if (matchesFirst != matchesSecond) {
                    validPasswordCount++
                }
            }
        }

        return validPasswordCount.toBigInteger()
    }

}

