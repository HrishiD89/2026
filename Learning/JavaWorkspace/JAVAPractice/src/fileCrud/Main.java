package fileCrud;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		// FileWriter = Good for small or medium-sized text files
		// BufferedWriter = Better performance for larger text files
		// PrintWriter = Good for writing formatted text to files
		// FileOutoutputStream = Good for writing binary data to files (e.g images,
		// audio, etc.)

		try (FileWriter writer = new FileWriter("test.txt")) {
			String textContent = """
					A spark of code lights up the night,
					Logic flows with quiet might.
					Errors fade as patterns grow,
					From thought to form, the answers show.
					Creation hums where ideas take flight
					""";
			writer.write(textContent);
			System.out.println("File has been written");
		} catch (FileNotFoundException e) {
			System.out.println("Could not locate file location!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
