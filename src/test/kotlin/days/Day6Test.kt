package days

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class Day6Test {


    @Test
    fun `Test part 1 row`() {
        val day6 = Day6()

        val input = Resources.getInputForDay(6)
        assertThat(day6.solvePart1(input)).isEqualTo(11);
    }

    @Test
    fun `Test part 2 row`() {
        val day6 = Day6()

        val input = Resources.getInputForDay(6)
        assertThat(day6.solvePart2(input)).isEqualTo(6);
    }


}
