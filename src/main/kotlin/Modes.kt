package org.example

fun interactiveMode() {
    val (a, b, c) = readCoefficientsFromConsole()
    val equation = QuadraticEquation(a, b, c)
    print(QuadraticEquationFormatter.formatInfo(equation))
}

fun nonInteractiveMode(args: Array<String>) {
    val (a, b, c) = readCoefficientsFromFile(args.first())
    val equation = QuadraticEquation(a, b, c)
    print(QuadraticEquationFormatter.formatInfo(equation))
}