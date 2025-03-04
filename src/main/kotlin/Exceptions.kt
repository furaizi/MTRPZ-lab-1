package org.example

import java.io.FileNotFoundException
import java.io.IOException

class FileDoesNotExistException(path: String) : FileNotFoundException("File $path does not exist")
class InvalidFileFormatException : IOException("Invalid file format")
class NotQuadraticEquationException : IllegalArgumentException("Error. 'a' cannot be 0")