import org.example.NotQuadraticEquationException
import org.example.main
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertContains
import kotlin.test.assertEquals

class InteractiveModeTest {

    private val standardIn = System.`in`
    private val standardOut = System.out
    private val testOut = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(testOut))
    }

    @AfterEach
    fun tearDown() {
        System.setIn(standardIn)
        System.setOut(standardOut)
        testOut.reset()
    }

    private fun runInteractiveMode(input: String): String {
        provideInput(input)
        main(arrayOf())
        return testOut.toString()
    }
    private fun provideInput(input: String) = System.setIn(ByteArrayInputStream(input.toByteArray()))


    @Test
    fun `valid input with two roots`() {
        // 2x^2 + 3x + 1
        val input = """
            2
            3
            1
        """.trimIndent()

        val output = runInteractiveMode(input)
        assertContains(output, "Equation is: (2.0) x^2 + (3.0) x + (1.0) = 0")
        assertContains(output, "There are 2 roots")
        assertContains(output, "x1 = -1.0")
        assertContains(output, "x2 = -0.5")
    }

    @Test
    fun `invalid input with 1 root`() {
        // x^2 - 10x + 25
        val input = """
            abc
            1
            -10
            25
        """.trimIndent()

        val output = runInteractiveMode(input)
        assertContains(output, "Error. Expected a valid real number, got abc instead")
        assertContains(output, "Equation is: (1.0) x^2 + (-10.0) x + (25.0) = 0")
        assertContains(output, "There is 1 root")
        assertContains(output, "x1 = 5")
    }

    @Test
    fun `non quadratic equation`() {
        val input = """
            0
            5
            30
        """.trimIndent()
        provideInput(input)

        val exception = assertThrows<NotQuadraticEquationException> { main(arrayOf()) }
        assertEquals("Error. 'a' cannot be 0", exception.message)
    }
}