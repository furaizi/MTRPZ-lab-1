package org.example

import java.io.File

fun interactiveMode() {
    val (a, b, c) = readCoefficients()
    val equation = QuadraticEquation(a, b, c)
    println(equation.formatInfo())
}

fun nonInteractiveMode(args: Array<String>) {
    val text = File(args.first()).readText()
    val (a, b, c) = text.split(" ")
        .map { it.toDouble() }
        .toList()
    val equation = QuadraticEquation(a, b, c)
    println(equation.formatInfo())
}

private fun readCoefficients(): Triple<Double, Double, Double> {
    print("a = ")
    val a = readln().toDouble()
    print("b = ")
    val b = readln().toDouble()
    print("c = ")
    val c = readln().toDouble()

    return Triple(a, b, c)
}