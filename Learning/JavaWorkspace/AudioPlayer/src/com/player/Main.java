package com.player;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Main {

	public static void main(String[] args) {
		String fileName = "src\\copyMusic.wav";
		File file = new File(fileName);

		if (!file.exists()) {
			System.out.println("Audio file not found!");
			return;
		}

		try (Scanner sc = new Scanner(System.in);
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)) {

			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			String response = "";
			while (!response.equals("Q")) {
				System.out.println("""
						P = Play
						S = Stop
						R = Reset
						Q = Quit
						""");
				System.out.print("Enter your choice : ");
				response = sc.nextLine().toUpperCase();

				switch (response) {
				case "P" -> clip.start();
				case "S" -> clip.stop();
				case "R" -> clip.setMicrosecondPosition(0);
				case "Q" -> clip.close();
				default -> System.out.println("Invalid choice!");
				}
			}

		} catch (FileSystemNotFoundException e) {
			System.out.println("Could not locate File");
		} catch (LineUnavailableException e) {
			System.out.println("Unable to access audio resource");
		} catch (UnsupportedAudioFileException e) {
			System.out.println("Audio file not supported!");
		} catch (IOException e) {
			System.out.println("Something went wrong!");
		} finally {
			System.out.println("Bye!");
		}
	}

}
