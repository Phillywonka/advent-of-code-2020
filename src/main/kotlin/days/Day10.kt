package days

import java.math.BigInteger


class Day10 : Day(10) {

    override fun solvePart1(input: List<String>): BigInteger {
        val numbers = input.map { it.toInt() }.sorted()
        val counter = JoltsCounter(0, 0, 0)
        numbers.scanIndexed(counter, { index, acc, number ->
            val diff = number - acc.jolts
            when (diff) {
                1 -> acc.oneDiffCount++
                3 -> acc.threeDiffCount++
            }

            if (diff in 1..3) acc.jolts = number

            if (index == numbers.lastIndex) {
                acc.threeDiffCount++
            }

            acc
        })

        return counter.oneDiffCount.toBigInteger() * counter.threeDiffCount.toBigInteger()
    }

    /**
     * What is the total number of distinct ways you can arrange the adapters to connect the charging outlet to
     * your device?
     *
     * Didn't manage to solve this one all by myself. Used this link
     * https://dev.to/sleeplessbyte/comment/194fe
     * For each adapter the following is true:

     * The number of paths to get to this adapter from the start is equal to the sum of the number
     * of paths to get from the previous adapter to this one.

     * That means that this problem can be reduced. You can walk the list from start to end or end to start and
     * only answer the question: paths to / paths from this adapter.

     * target:  options                   => total options
     * 1:  only 1 option (0 + 1)     => 1
     * 4:  only 1 option (1 + 3)     => 1
     * 5:  only 1 option (4 + 1)     => 1
     * 6:  either 4 + 2 or 5 + 1     => 2
     * 7:  6 + 1 or 5 + 2 or 4 + 3   => 2 (6) + 1 (5) + 1 (4)
     * 10:  only 1 option (7 + 3)     => 4
     * 11:  only 1 option (10 + 1)    => 4
     * 12:  either 10 + 2 or 11 + 1   => 4 (10) + 4 (11)
     * 15:  only 1 option 12 + 3      => 8
     * 16:  only 1 option 15 + 1      => 8
     * 19:  only 1 option 16 + 3      => 8
     * 22:  only 1 option 19 + 3      => 8
     * =end
     */
    override fun solvePart2(input: List<String>): BigInteger {
        val numbers = input.map { it.toInt() }.toMutableList().apply {
            add(0)
            add(maxOf { it } + 3)
        }.sorted()

        val options = mutableMapOf<Int, BigInteger>()
        val additions = listOf(1, 2, 3)

        numbers.subList(1, numbers.size).forEach { number ->
            options[number] =
                numbers.filter { combiNumber -> additions.any { combiNumber + it == number } }.sumOf {
                    options[it] ?: BigInteger.ONE
                }
        }

        return options[numbers.last()] ?: BigInteger.ONE
    }

    data class JoltsCounter(var oneDiffCount: Int, var threeDiffCount: Int, var jolts: Int)
}


