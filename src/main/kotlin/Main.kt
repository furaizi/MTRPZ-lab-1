package org.example
import java.util.Scanner

fun main(args: Array<String>) {
    when (args.size) {
        0 -> interactiveMode()
        1 -> nonInteractiveMode()
        else -> throw IllegalArgumentException()
    }
}



fun interactiveMode() {
    val (a, b, c) = readCoefficients()
    printEquation(a, b, c)
    val solution = solveQuadraticEquation(a, b, c)
    printSolutionInfo(solution)
    printRoots(solution)
}

private fun readCoefficients(): Triple<Double, Double, Double> {
    val scanner = Scanner(System.`in`)
    print("a = ")
    val a = scanner.nextDouble()
    print("b = ")
    val b = scanner.nextDouble()
    print("c = ")
    val c = scanner.nextDouble()

    return Triple(a, b, c)
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

fun nonInteractiveMode() {

}