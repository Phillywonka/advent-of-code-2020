package days

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class Day10Test {


    @Test
    fun `Test part 1`() {
        val day = Day10()

        val input = Resources.getInputForDay(10)
        assertThat(day.solvePart1(input)).isEqualTo(220);
    }

    @Test
    fun `Test part 2`() {
//        val day = Day9()
//
//        val input = Resources.getInputForDay(9)
//        assertThat(day.solvePart2(input)).isEqualTo(62);
    }

}
