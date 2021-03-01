package days

import java.math.BigInteger

class Day4 : Day(4) {

    override fun solvePart1(input: List<String>): BigInteger {
        val requiredFields = listOf("ecl", "pid", "eyr", "hcl", "byr", "iyr", "hgt")

        val passports = this.getPassports(input)

        return (passports.size - passports.filter { password ->
            requiredFields.any { it !in password }
        }.count()).toBigInteger()
    }

    private fun getPassports(input: List<String>): List<String> {
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
                input.subList(value.first, value.second).filter { it.isNotBlank() }.joinToString(" ")
            }
    }

    override fun solvePart2(input: List<String>): BigInteger {
        val passports = this.getPassports(input)

        return (passports.size - passports.filter { password ->
            !isValidPassport(password)
        }.count()).toBigInteger()
    }

    fun isValidPassport(passport: String): Boolean {
        val requiredFields = listOf("ecl", "pid", "eyr", "hcl", "byr", "iyr", "hgt")

        return !requiredFields.any { it !in passport }
                && !passport.split(' ', '\n')
            .any { entry ->
                val isValid = isValidEntry(entry)
                if (!isValid) println("$entry is invalid")
                !isValid
            }
    }

    fun isValidEntry(entry: String): Boolean {
        return entry.substringBefore(':').run {
            FieldMatcher.from(this)?.isValid(entry.substringAfter(':')) ?: true
        }

    }

    enum class FieldMatcher(val value: String) {
        BirthYear(value = "byr") {
            override fun isValid(value: String): Boolean {
                return value.length in 1..4 && value.toInt() in 1920..2002
            }
        },
        IssueYear(value = "iyr") {
            override fun isValid(value: String): Boolean {
                return value.length in 1..4 && value.toInt() in 2010..2020
            }
        },
        ExpirationYear(value = "eyr") {
            override fun isValid(value: String): Boolean {
                return value.length in 1..4 && value.toInt() in 2020..2030
            }
        },
        Height(value = "hgt") {
            override fun isValid(value: String): Boolean {
                if (value.length >= 4) {
                    val unit = value.takeLast(2)
                    val height = value.take(value.length - 2).toInt()
                    return when (unit) {
                        "cm" -> height in 150..193
                        "in" -> height in 59..76
                        else -> false
                    }
                }

                return false
            }
        },
        HairColor(value = "hcl") {
            override fun isValid(value: String): Boolean {
                val regex = "^#[a-f0-9]{6}$".toRegex()

                return regex.matches(value)

            }
        },
        EyeColor(value = "ecl") {
            override fun isValid(value: String): Boolean {
                val regex = "^(amb|blu|brn|gry|grn|hzl|oth)$".toRegex()
                return value.matches(regex)
            }
        },
        PassportId(value = "pid") {
            override fun isValid(value: String): Boolean {
                val regex = "^[0-9]{9}$".toRegex()
                return value.matches(regex)
            }
        };

        abstract fun isValid(value: String): Boolean

        companion object {
            fun from(name: String) = values().find { it.value == name }
        }

    }
}

