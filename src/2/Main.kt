package `2`

import java.io.BufferedReader
import java.io.File

fun isValidSequence(list: List<Int>): Boolean {
    val isIncreasing = list.zipWithNext().all { (a, b) -> b - a in 1..3 }
    val isDecreasing = list.zipWithNext().all { (a, b) -> a - b in 1..3 }
    return isIncreasing || isDecreasing
}

fun main() {
    val bufferedReader: BufferedReader = File("./src/2/input.txt").bufferedReader()
    val lines = bufferedReader.use { it.readText() }.split("\n")

    val reportsOne = lines.map { s ->
        val values = s.split("\\s+".toRegex()).mapNotNull { it.toIntOrNull() }
        isValidSequence(values)
    }

    val partOne = reportsOne.count { it }
    println(partOne)

    val reportsTwo = lines.map { s ->
        val values = s.split("\\s+".toRegex()).mapNotNull { it.toIntOrNull() }

        if (isValidSequence(values)) {
            true
        } else {
            values.indices.any { i ->
                val modifiedList = values.filterIndexed { index, _ -> index != i }
                isValidSequence(modifiedList)
            }
        }
    }

    val partTwo = reportsTwo.count { it }
    println(partTwo)
}