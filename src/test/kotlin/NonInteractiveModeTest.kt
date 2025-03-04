import org.example.main
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.Test
import kotlin.test.assertContains

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
        main(arrayOf(path))
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
        val output = runNonInteractiveMode("0 1 -7")
        assertContains(output, "Error. 'a' cannot be 0")
    }

    @Test
    fun `invalid file format`() {
        val output = runNonInteractiveMode("a b c")
        assertContains(output, "Invalid file format")
    }

    @Test
    fun `file does not exists`() {
        val nonExistentPath = "src/test/resources/doesnotexist.txt"
        main(arrayOf(nonExistentPath))
        val output = outputStream.toString()
        assertContains(output, "File $nonExistentPath does not exist")

    }

}