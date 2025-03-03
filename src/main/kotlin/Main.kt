package org.example
import java.io.File
import java.util.Scanner

fun main(args: Array<String>) {
    when (args.size) {
        0 -> interactiveMode()
        1 -> nonInteractiveMode(args)
        else -> throw IllegalArgumentException()
    }
}