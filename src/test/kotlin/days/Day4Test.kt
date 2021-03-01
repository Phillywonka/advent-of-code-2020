package days

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class Day4Test {


    @Test
    fun `Test part 1`() {
        val day1 = Day4()

        val input = Resources.getInputForDay(4)

        assertThat(day1.solvePart1(input)).isEqualTo(2);
    }

    @Test
    fun `Hair color is valid `() {
//        val input = Resources.getResourceAsList("day4.txt")

        val isValid = Day4.FieldMatcher.HairColor.isValid("#123abc")
        assertThat(isValid).isTrue()
    }

    @Test
    fun `Hair color is invalid `() {
        val isValid = Day4.FieldMatcher.HairColor.isValid("#123abg")
        assertThat(isValid).isFalse()
    }

    @Test
    fun `Hair color is invalid for longer input`() {
        val isValid = Day4.FieldMatcher.HairColor.isValid("#123abcd")
        assertThat(isValid).isFalse()
    }

    @Test
    fun `Eye color is valid for amb`() {
        val isValid = Day4.FieldMatcher.EyeColor.isValid("amb")
        assertThat(isValid).isTrue()
    }

    @Test
    fun `Eye color is invalid for ab`() {
        val isValid = Day4.FieldMatcher.EyeColor.isValid("ab")
        assertThat(isValid).isFalse()
    }

    @Test
    fun `Height is invalid for ab`() {
        val isValid = Day4.FieldMatcher.from("hgt")?.isValid("170")

        assertThat(isValid).isFalse()
    }

    @Test
    fun `Passport id is valid`() {
        val isValid = Day4.FieldMatcher.from("hcl")?.isValid("#cddc40")!!
        assertThat(isValid).isTrue()
    }

    @Test
    fun `Test day 4`() {
        val day1 = Day4()

        val input = Resources.getInputForDay(4)

//        assertThat(
//            day1.isValidPassport(
//        "iyr:2012 eyr:2030 byr:1980\n" +
//                "hcl:#623a2f"
//            )
//        ).isTrue()

        assertThat(
            day1.solvePart2(
                input
            )
        ).isEqualTo(4)
    }


}