package days

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class Day5Test {


    @Test
    fun `Test part 1 row`() {
        val day1 = Day5()

//        val input = Resources.getResourceAsList("day4.txt")
        assertThat(day1.getRow("BFFFBBF")).isEqualTo(70);
    }

    @Test
    fun `Test part 1 column`() {
        val day1 = Day5()

//        val input = Resources.getResourceAsList("day4.txt")
        assertThat(day1.getColumn("RLR")).isEqualTo(5);
    }

    @Test
    fun `Test part 1 seat`() {
        val day1 = Day5()

        val input = Resources.getInputForDay(5)
        assertThat(day1.solvePart1(input)).isEqualTo(820);
    }

}
