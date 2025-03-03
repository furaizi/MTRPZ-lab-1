package org.example

import kotlin.Double.Companion.NaN
import kotlin.math.sqrt

class QuadraticEquation(val a: Double, val b: Double, val c: Double) {
    val roots: Pair<Double, Double> by lazy {
        solveQuadraticEquation(a, b, c).sorted()
    }

    fun formatInfo(): String {
        return buildString {
            appendLine(toString())
            appendLine(formatRootsCount())
            appendLine(formatRoots())
        }
    }

    override fun toString(): String {
        // TODO: normalize equation
        return "Equation is: (${a}) x^2 + (${b}) x + (${c}) = 0"
    }

    fun hasNoRoots() = roots.first.isNaN() && roots.second.isNaN()
    fun hasFirstRoot() = roots.first.isFinite()
    fun hasOnlyOneRoot() = roots.first.isFinite() && roots.second.isNaN()
    fun hasTwoRoots() = roots.first.isFinite() && roots.second.isFinite()

    fun formatRoots(): String {
        val (x1, x2) = roots
        return buildString {
            if (hasFirstRoot()) appendLine("x1 = $x1")
            if (hasTwoRoots()) appendLine("x2 = $x2")
        }
    }

    fun formatRootsCount(): String {
        return when {
            hasNoRoots() -> "There are 0 roots"
            hasOnlyOneRoot() -> "There is 1 root"
            hasTwoRoots() -> "There are 2 roots"
            else -> ""
        }
    }

    private fun Pair<Double, Double>.sorted() = if (first <= second) this else second to first

    companion object {
        private fun solveQuadraticEquation(a: Double, b: Double, c: Double) : Pair<Double, Double> {
            val discriminant = b*b - 4*a*c
            return when {
                discriminant > 0 -> calculateX1(a, b, discriminant) to calculateX2(a, b, discriminant)
                discriminant == 0.0 -> calculateX1(a, b, discriminant) to NaN
                else -> NaN to NaN
            }
        }

        private fun calculateX1(a: Double, b: Double, D: Double) = (-b + sqrt(D))/(2 * a)
        private fun calculateX2(a: Double, b: Double, D: Double) = (-b - sqrt(D))/(2 * a)
    }


}