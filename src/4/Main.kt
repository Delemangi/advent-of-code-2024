package `4`

import java.io.BufferedReader
import java.io.File

fun matchesWord(grid: List<String>, startRow: Int, startCol: Int, dRow: Int, dCol: Int): Boolean {
    val word = "XMAS"
    val rows = grid.size
    val cols = grid[0].length

    for (i in word.indices) {
        val newRow = startRow + i * dRow
        val newCol = startCol + i * dCol

        if (newRow !in 0..<rows || newCol !in 0..<cols) return false
        if (grid[newRow][newCol] != word[i]) return false
    }

    return true
}

fun countWordOccurrences(grid: List<String>): Int {
    val rows = grid.size
    val cols = grid[0].length
    val directions = listOf(
        Pair(0, 1),  // right
        Pair(0, -1), // left
        Pair(1, 0),  // down
        Pair(1, 1),  // down right
        Pair(1, -1), // down left
        Pair(-1, 0), // up
        Pair(-1, 1), // up right
        Pair(-1, -1) // up left
    )

    var count = 0

    for (row in 0..<rows) {
        for (col in 0..<cols) {
            for ((dRow, dCol) in directions) {
                if (matchesWord(grid, row, col, dRow, dCol)) {
                    count++
                }
            }
        }
    }

    return count
}

val VALID_WORDS = listOf("MAS", "SAM")

fun matchesXShape(grid: List<String>, centerRow: Int, centerCol: Int): Boolean {
    val topLeft = Pair(centerRow - 1, centerCol - 1)
    val topRight = Pair(centerRow - 1, centerCol + 1)
    val bottomLeft = Pair(centerRow + 1, centerCol - 1)
    val bottomRight = Pair(centerRow + 1, centerCol + 1)
    val center = Pair(centerRow, centerCol)

    val wordOne = listOf(
        grid[topLeft.first][topLeft.second],
        grid[center.first][center.second],
        grid[bottomRight.first][bottomRight.second]
    ).joinToString("")

    val wordTwo = listOf(
        grid[topRight.first][topRight.second],
        grid[center.first][center.second],
        grid[bottomLeft.first][bottomLeft.second]
    ).joinToString("")

    return wordOne in VALID_WORDS && wordTwo in VALID_WORDS
}

fun countXShapeOccurrences(grid: List<String>): Int {
    val rows = grid.size
    val cols = grid[0].length
    var count = 0

    for (row in 1..<rows - 1) {
        for (col in 1..<cols - 1) {
            if (matchesXShape(grid, row, col)) {
                count++
            }
        }
    }
    return count
}

fun main() {
    val bufferedReader: BufferedReader = File("./src/4/input.txt").bufferedReader()
    val grid = bufferedReader.use { it.readLines() }

    val partOne = countWordOccurrences(grid)
    val partTwo = countXShapeOccurrences(grid)
    println(partOne)
    println(partTwo)
}