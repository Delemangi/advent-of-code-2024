package `5`

import java.io.BufferedReader
import java.io.File

fun getFixedUpdate(update: String, orderingRules: List<String>): String {
    val pageNumbers = update.split(",")
    var newUpdate = update

    for (i in 0..<pageNumbers.size - 1) {
        for (j in i + 1..<pageNumbers.size) {
            val firstPage = pageNumbers[i]
            val secondPage = pageNumbers[j]

            if ("$secondPage|$firstPage" in orderingRules) {
                val pageNumbersCopy = pageNumbers.toMutableList()
                pageNumbersCopy.removeAt(i)
                pageNumbersCopy.add(j, firstPage)

                newUpdate = pageNumbersCopy.joinToString(",")
            }
        }
    }

    if (newUpdate == update) {
        return newUpdate
    }

    return getFixedUpdate(newUpdate, orderingRules)
}

fun main() {
    val bufferedReader: BufferedReader = File("./src/5/input.txt").bufferedReader()
    val lines = bufferedReader.use { it.readText() }.trimIndent()
    val sections = lines.split("\\r?\\n\\r?\\n".toRegex())

    val pageOrderingRules = sections[0].split("\n")

    val updates = sections[1].split("\n")
    val middleNumbersPartOne = mutableListOf<String>()
    updates.forEach {
        val pageNumbers = it.split(",")

        for (i in 0..<pageNumbers.size - 1) {
            for (j in i + 1..<pageNumbers.size) {
                val firstPage = pageNumbers[i]
                val secondPage = pageNumbers[j]

                if ("$secondPage|$firstPage" in pageOrderingRules) {
                    return@forEach
                }
            }
        }

        val middleNumber = pageNumbers[pageNumbers.size / 2]
        middleNumbersPartOne.add(middleNumber)
    }

    val partOne = middleNumbersPartOne.sumOf { it.toInt() }
    println(partOne)

    val middleNumbersPartTwo = mutableListOf<String>()
    updates.forEach {
        val fixedUpdate = getFixedUpdate(it, pageOrderingRules)
        if (it == fixedUpdate) {
            return@forEach
        }

        val pageNumbers = fixedUpdate.split(",")
        val middleNumber = pageNumbers[pageNumbers.size / 2]
        middleNumbersPartTwo.add(middleNumber)
    }

    val partTwo = middleNumbersPartTwo.sumOf { it.toInt() }
    println(partTwo)
}