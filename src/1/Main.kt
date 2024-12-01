package `1`

import java.io.BufferedReader
import java.io.File
import kotlin.math.absoluteValue

fun main() {
    val bufferedReader: BufferedReader = File("./src/1/input.txt").bufferedReader()
    val lines = bufferedReader.use { it.readText() }.split("\n")

    val firstColumn = mutableListOf<Int>()
    val secondColumn = mutableListOf<Int>()

    lines.forEach {
        val values = it.split("\\s+".toRegex())
        val numbers = values.mapNotNull { it.toIntOrNull() }

        firstColumn.add(numbers[0])
        secondColumn.add(numbers[1])
    }

    firstColumn.sortWith { a, b -> a - b }
    secondColumn.sortWith { a, b -> a - b }

    val partOne = firstColumn.zip(secondColumn).sumOf { (it.second - it.first).absoluteValue }
    println(partOne)

    val partTwo = firstColumn.stream().mapToInt {
        val occurrences = secondColumn.count { second -> second == it }
        it * occurrences
    }.sum()

    println(partTwo)
}