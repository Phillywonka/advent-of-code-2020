package days

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class Day7Test {


    @Test
    fun `Test part 1 row`() {
        val day7 = Day7()

        val input = Resources.getInputForDay(7)
        assertThat(day7.solvePart1(input)).isEqualTo(4);
    }

    @Test
    fun `Test part 2 row`() {
        val day7 = Day7()

        val input = Resources.getInputForDay(7)

        assertThat(day7.solvePart2(input)).isEqualTo(126);
    }


}
