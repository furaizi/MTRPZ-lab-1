package org.example

import java.io.File

fun interactiveMode() {
    val (a, b, c) = readCoefficients()
    printAllInfo(a, b, c)
}

fun nonInteractiveMode(args: Array<String>) {
    val text = File(args.first()).readText()
    val (a, b, c) = text.split(" ")
        .map { it.toDouble() }
        .toList()
    printAllInfo(a, b, c)
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

private fun printAllInfo(a: Double, b: Double, c: Double) {
    printEquation(a, b, c)
    val solution = solveQuadraticEquation(a, b, c)
    printSolutionInfo(solution)
    printRoots(solution)
}

private fun printEquation(a: Double, b: Double, c: Double) {
    println("Equation is: (${a}) x^2 + (${b}) x + (${c}) = 0")
}

private fun printSolutionInfo(solution: Pair<Double, Double>) {
    val rootsInfo = when {
        solution.hasNoRoots() -> "There are 0 roots"
        solution.hasOneRoot() -> "There is 1 root"
        solution.hasTwoRoots() -> "There are 2 roots"
        else -> ""
    }
    println(rootsInfo)
}

private fun printRoots(roots: Pair<Double, Double>) {
    if (roots.first.isFinite())
        println("x1 = ${roots.first}")

    if (roots.second.isFinite()) {
        println("x2 = ${roots.second}")
    }
}