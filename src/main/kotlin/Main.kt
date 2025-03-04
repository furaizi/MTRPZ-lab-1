package org.example

fun main(args: Array<String>) {
    try {
        when (args.size) {
            0 -> interactiveMode()
            1 -> nonInteractiveMode(args)
            else -> throw IllegalArgumentException("Program should be run with 0 or 1 arguments")
        }
    }
    catch (e: Exception) {
        println(e.message)
    }
}