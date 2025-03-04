import org.example.FileDoesNotExistException
import org.example.InvalidFileFormatException
import org.example.NotQuadraticEquationException
import org.example.interactiveMode
import org.example.nonInteractiveMode
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class NonInteractiveMode {

    private val DEFAULT_PATH = "src/test/resources/test.txt"
    private val standardOut = System.out
    private val testOut = ByteArrayOutputStream()

    @BeforeEach
    fun prepare() {
        System.setOut(PrintStream(testOut))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
        testOut.reset()
    }

    @Test
    fun `valid input with 0 roots`() {
        val input = "1 0 9\n"
        File(DEFAULT_PATH).writeText(input)

        nonInteractiveMode(arrayOf(DEFAULT_PATH))
        val output = testOut.toString()
        assertContains(output, "Equation is: (1.0) x^2 + (0.0) x + (9.0) = 0")
        assertContains(output, "There are 0 roots")
    }

    @Test
    fun `non quadratic equation`() {
        val input = "0 1 -7"
        File(DEFAULT_PATH).writeText(input)

        val exception = assertThrows<NotQuadraticEquationException> { nonInteractiveMode(arrayOf(DEFAULT_PATH)) }
        assertEquals("Error. 'a' cannot be 0", exception.message)
    }

    @Test
    fun `invalid file format`() {
        val input = "a b c"
        File(DEFAULT_PATH).writeText(input)

        val exception = assertThrows<InvalidFileFormatException> { nonInteractiveMode(arrayOf(DEFAULT_PATH)) }
        assertEquals("Invalid file format", exception.message)
    }

    @Test
    fun `file does not exists`() {
        val input = "2 5 -10"
        val nonExistentPath = "src/test/resources/doesnotexist.txt"
        File(nonExistentPath).writeText(input)

        val exception = assertThrows<FileDoesNotExistException> { nonInteractiveMode(arrayOf(nonExistentPath)) }
        assertEquals("File $nonExistentPath does not exist", exception.message)
    }

}