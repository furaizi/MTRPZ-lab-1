package org.example

import java.io.File

fun readCoefficientsFromConsole(): Triple<Double, Double, Double> = listOf("a", "b", "c")
    .asSequence()
    .onEach { print("$it = ") }
    .map { readln() }
    .map { it.toDouble() }
    .toList()
    .let { (a, b, c) -> Triple(a, b, c) }

fun readCoefficientsFromFile(path: String): Triple<Double, Double, Double> = File(path)
    .readText()
    .split(" ")
    .map { it.toDouble() }
    .let { (a, b, c) -> Triple(a, b, c) }