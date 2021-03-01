package days

import replace
import java.math.BigInteger


class Day7 : Day(7) {

    override fun solvePart1(input: List<String>): BigInteger {
        return getBagMap(input).run {
            count { bag ->
                hasAnyGoldBag(this, bag.key, bag.key)
            }
        }.toBigInteger()
    }

    override fun solvePart2(input: List<String>): BigInteger {
        val goldBag = getBagAmountMap(input).toSortedMap()

        val nodes = mutableListOf<Pair<String, Int>>()
        getChildren(goldBag, "shiny gold", nodes = nodes, amount = 1, root = "shiny gold")
        return nodes.toList().sumBy { it.second }.toBigInteger()

    }

    private fun getBagMap(input: List<String>): Map<String, Set<String>> {
        return input.map { line ->
            val formatted = line.replace("," to " ", "." to " ", "  " to " ")
            val bag = formatted.substringBefore("bag").trim()
            val children = formatted.substringAfter("contain").trim().split("bags", "bag").filter {
                it.isNotBlank()
            }.map {
                it.trim().substring(1, it.trim().length).trim()
            }.toSet()

            bag to children
        }.toMap()
    }

    private fun getBagAmountMap(input: List<String>): Map<String, Map<String, Int>> {
        return input.map { line ->
            val formatted = line.replace("," to " ", "." to " ", "  " to " ")
            val bag = formatted.substringBefore("bag").trim()
            val unformattedChildren = formatted.substringAfter("contain").trim()
            val children =
                if (unformattedChildren.contains("no other")) {
                    emptyMap()
                } else {
                    formatted.substringAfter("contain").trim().split("bags", "bag").filter {
                        it.isNotBlank()
                    }.map {
                        val num = it.trim().first().toString().toInt()
                        it.trim().substring(1, it.trim().length).trim() to num
                    }.toMap()
                }

            bag to children
        }.toMap()
    }

    private fun hasAnyGoldBag(
        tree: Map<String, Set<String>>,
        key: String,
        root: String,
        nodes: MutableList<String> = mutableListOf()
    ): Boolean {
        if ("shiny gold" in nodes) {
            return true
        } else if (key in nodes) {
            // skip node which we already processed
            return false
        }

        if (key != root) nodes.add(key)
        val children = tree[key]

        return children?.any { child ->
            hasAnyGoldBag(tree, child, root, nodes)
        } ?: false
    }

    private fun getChildren(
        tree: Map<String, Map<String, Int>>,
        key: String,
        amount: Int,
        root: String,
        nodes: MutableList<Pair<String, Int>> = mutableListOf(),
        total: Int = 0
    ) {
        if (key != root) {
            nodes.add(key to amount)
        }
        // children of the children
        val children = tree[key]?.toMutableMap()

        children?.forEach { child ->
            val childAmount = (child.value.takeIf { it > 1 } ?: 1) * amount
            println("|\n|\n${child.key} $childAmount")
            getChildren(tree, child.key, childAmount, root, nodes, total + childAmount)
        }

    }
}