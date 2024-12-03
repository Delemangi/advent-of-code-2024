package `3`

import java.io.BufferedReader
import java.io.File

fun main() {
    val bufferedReader: BufferedReader = File("./src/3/input.txt").bufferedReader()
    val lines = bufferedReader.use { it.readText() }

    val regexPartOne = "mul\\((\\d+),(\\d+)\\)".toRegex()
    val matchesPartOne = regexPartOne.findAll(lines)
    val partOne = matchesPartOne.sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
    println(partOne)

    val regexPartTwo = "(mul\\((\\d+),(\\d+)\\)|(do\\(\\))|(don't\\(\\)))".toRegex()
    val matchesPartTwo = regexPartTwo.findAll(lines)
    var partTwo = 0

    var enabled = true

    matchesPartTwo.forEach {
        val firstGroupValue = it.groupValues[0]

        when {
            firstGroupValue.startsWith("mul(") -> {
                val a = it.groupValues[2].toInt()
                val b = it.groupValues[3].toInt()

                if (enabled) {
                    partTwo += a * b
                }
            }

            firstGroupValue.startsWith("don't(") -> {
                enabled = false
            }

            firstGroupValue.startsWith("do(") -> {
                enabled = true
            }
        }
    }
    println(partTwo)
}