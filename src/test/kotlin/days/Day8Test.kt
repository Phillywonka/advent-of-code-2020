package days

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class Day8Test {


    @Test
    fun `Test part 1`() {
        val day = Day8()

        val input = Resources.getInputForDay(8)
        assertThat(day.solvePart1(input)).isEqualTo(5);
    }

    @Test
    fun `Test part 2`() {
        val day = Day8()

        val input = Resources.getInputForDay(8)
        assertThat(day.solvePart2(input)).isEqualTo(8);
    }


}
