package org.example

import java.io.File

fun readCoefficientsFromConsole(): Triple<Double, Double, Double> = listOf("a", "b", "c")
    .asSequence()
    .map { readDouble(it) }
    .toList()
    .let { (a, b, c) -> Triple(a, b, c) }

fun readCoefficientsFromFile(path: String): Triple<Double, Double, Double> {
    val file = File(path)
                .takeIf { it.exists() } ?: throw FileDoesNotExistException(path)
    return file
            .readText()
            .split(" ")
            .map { it.toDoubleOrNull() ?: throw InvalidFileFormatException() }
            .let { (a, b, c) -> Triple(a, b, c) }
            .takeIf { (a) -> a != 0.0 } ?: throw NotQuadraticEquationException()
}

private fun readDouble(name: String): Double {
    print("$name = ")
    val input = readln()
    return input.toDoubleOrNull() ?: run {
        println("Error. Expected a valid real number, got $name instead")
        readDouble(name)
    }
}