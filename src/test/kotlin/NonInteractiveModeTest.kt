import org.example.FileDoesNotExistException
import org.example.InvalidFileFormatException
import org.example.NotQuadraticEquationException
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

class NonInteractiveModeTest {

    private companion object {
        const val DEFAULT_PATH = "src/test/resources/test.txt"
    }
    private val originalOut = System.out
    private val outputStream = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStream))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(originalOut)
        outputStream.reset()
    }

    private fun runNonInteractiveMode(fileContent: String, path: String = DEFAULT_PATH): String {
        File(path).writeText(fileContent)
        nonInteractiveMode(arrayOf(path))
        return outputStream.toString()
    }

    @Test
    fun `valid input with 0 roots`() {
        val output = runNonInteractiveMode("1 0 9")
        assertContains(output, "Equation is: (1.0) x^2 + (0.0) x + (9.0) = 0")
        assertContains(output, "There are 0 roots")
    }

    @Test
    fun `non quadratic equation`() {
        File(DEFAULT_PATH).writeText("0 1 -7")
        val exception = assertThrows<NotQuadraticEquationException> {
            nonInteractiveMode(arrayOf(DEFAULT_PATH))
        }
        assertEquals("Error. 'a' cannot be 0", exception.message)
    }

    @Test
    fun `invalid file format`() {
        File(DEFAULT_PATH).writeText("a b c")
        val exception = assertThrows<InvalidFileFormatException> {
            nonInteractiveMode(arrayOf(DEFAULT_PATH))
        }
        assertEquals("Invalid file format", exception.message)
    }

    @Test
    fun `file does not exists`() {
        val nonExistentPath = "src/test/resources/doesnotexist.txt"
        val exception = assertThrows<FileDoesNotExistException> {
            nonInteractiveMode(arrayOf(nonExistentPath))
        }
        assertEquals("File $nonExistentPath does not exist", exception.message)
    }

}