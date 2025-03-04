package org.example

fun main(args: Array<String>) {
    try {
        val (a, b, c) = when (args.size) {
            0 -> readCoefficientsFromConsole()
            1 -> readCoefficientsFromFile(args.first())
            else -> throw IllegalArgumentException("Program should be run with 0 or 1 arguments")
        }
        val equation = QuadraticEquation(a, b, c)
        print(QuadraticEquationFormatter.formatInfo(equation))
    }
    catch (e: Exception) {
        println(e.message)
    }
}