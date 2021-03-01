import java.io.File

object Resources {

    fun getInputForDay(day: Int): List<String> {
        val filePath = javaClass.classLoader?.getResource("day$day.txt")?.file

        return filePath?.let { File(it).readLines() } ?: emptyList()
    }

}