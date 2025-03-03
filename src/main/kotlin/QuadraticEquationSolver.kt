package org.example

import kotlin.Double.Companion.NaN
import kotlin.math.sqrt

private val x1: (Double, Double, Double) -> Double = { a, b, D -> (-b + sqrt(D))/(2 * a) }
private val x2: (Double, Double, Double) -> Double = { a, b, D -> (-b - sqrt(D))/(2 * a) }

fun solveQuadraticEquation(a: Double, b: Double, c: Double) : Pair<Double, Double> {
    val discriminant = b*b - 4*a*c
    return when {
        discriminant > 0 -> x1(a, b, discriminant) to x2(a, b, discriminant)
        discriminant == 0.0 -> x1(a, b, discriminant) to NaN
        else -> NaN to NaN
    }
}

fun Pair<Double, Double>.hasNoRoots() = this.first.isNaN() && this.second.isNaN()
fun Pair<Double, Double>.hasOneRoot() = !this.first.isNaN() && this.second.isNaN()
fun Pair<Double, Double>.hasTwoRoots() = !(this.first.isNaN() || this.second.isNaN())