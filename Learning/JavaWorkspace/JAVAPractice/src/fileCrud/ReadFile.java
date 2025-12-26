package fileCrud;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	public static void main(String[] args) {
		// How to read a file using Java (3 popular options)

		// BufferedReader + FFileReader: Best for reading text files line-by-line
		// FileInputStream: Best for binary files (e.g., images, audio files)
		// RandomAccessFile: Best for read/write specific portions of a large file

		String filePath = "C:\\Users\\hrishi\\Desktop\\test.txt";
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			System.out.println("That file exits");
			String line;
			while ((line = reader.readLine())!= null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
