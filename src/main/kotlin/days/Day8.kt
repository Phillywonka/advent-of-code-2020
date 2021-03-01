package days

import java.math.BigInteger


class Day8 : Day(8) {

    override fun solvePart1(input: List<String>): BigInteger {
        val passedInstructionIndexes = arrayListOf<Int>()
        val instructions = input.mapToInstructions()

        var hasReachedInfiniteLoop = false
        var acc = 0
        var index = 0

        while (!hasReachedInfiniteLoop) {
            val instruction = instructions[index]
            val name = instruction.first
            val number = instruction.second
            passedInstructionIndexes.add(index)

            when (name) {
                "acc" -> {
                    acc += number
                    index++
                }
                "jmp" -> {
                    if (number < 0 && index + number in passedInstructionIndexes) {
                        hasReachedInfiniteLoop = true
                    }
                    index += number
                }
                else -> index++
            }
        }

        return acc.toBigInteger()
    }

    private fun List<String>.mapToInstructions() = this.map {
        val name = it.substringBefore(" ")
        val number = it.substringAfter(" ").toInt()
        Instruction(name, number)
    }

    override fun solvePart2(input: List<String>): BigInteger {
        val instructions = input.mapToInstructions()

        // Do the same as in part 1
        // If the infinite loop index is found save the index retry with less high jmp value
        // If the last index is reached return acc
        return getAcc(instructions).toBigInteger()
    }

    private fun getAcc(instructions: List<Instruction>): Int {
        val passedInstructionIndexes = arrayListOf<Int>()

        var acc = 0
        var index = 0

        // Do the same as in part 1
        // If the infinite loop index is found save the index retry with less high jmp value
        // If the last index is reached return acc
        while (true) {
            if (index > instructions.lastIndex) {
                index = instructions.lastIndex
                break
            }
            val instruction = instructions[index]
            val name = instruction.first
            val number = instruction.second
            passedInstructionIndexes.add(index)

            when (name) {
                "acc" -> {
                    acc += number
                    index++
                }
                "jmp" -> {
                    if (number < 0 && index + number in passedInstructionIndexes) {
                        break
                    }
                    index += number
                }
                else -> index++
            }
        }

        return if (index != instructions.lastIndex) {
            getAcc(instructions.mapIndexed { i, pair ->
                if (i == index) {
                    val name = if (pair.first == "nop") "jmp" else "nop"
                    Instruction(name, pair.second)
                } else pair
            })
        } else acc

    }
}

typealias Instruction = Pair<String, Int>
