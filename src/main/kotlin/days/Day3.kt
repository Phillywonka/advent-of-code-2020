package days

import java.math.BigInteger

class Day3 : Day(3) {

    override fun solvePart1(input: List<String>): BigInteger {
        return this.getTreeCount(input, 3, 1).toBigInteger()
    }

    override fun solvePart2(input: List<String>): BigInteger {
        return this.getTreeCount(input, 1, 1)
            .times(this.getTreeCount(input, 3, 1))
            .times(this.getTreeCount(input, 5, 1))
            .times(this.getTreeCount(input, 7, 1))
            .times(this.getTreeCount(input, 7, 2)).toBigInteger()
    }


    private fun getTreeCount(input: List<String>, horizontalStepSize: Int, verticalStepSize: Int): Int {
        var treeCount = 0
        var position = 0
        var row = 0
        val width = input[0].length

        while (row < input.size - 1) {
            row += verticalStepSize
            position = (position + horizontalStepSize) % width

            val entry = input[row]

            if (entry[position] == '#') {
                treeCount++
            }
        }

        return treeCount
    }

}
