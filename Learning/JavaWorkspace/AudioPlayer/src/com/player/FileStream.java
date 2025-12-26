package com.player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class FileStream {
	public static void main(String[] args) {
		String filePath = "src\\By Myself - The Grey Room _ Clark Sims.wav";
		File file = new File(filePath);

		if (!file.exists()) {
			System.out.println("File doesn't exist!");
			return;
		}

		File copiedFile = new File("src\\copyMusic.wav");

		// Copy only if not already copied
		if (!copiedFile.exists()) {
			try (FileInputStream fis = new FileInputStream(file);
					FileOutputStream fos = new FileOutputStream(copiedFile)) {

				byte[] buffer = new byte[100];
				int byteRead;
				while ((byteRead = fis.read(buffer)) != -1) {
					System.out.println(buffer);
					fos.write(buffer, 0, byteRead);
				}
				System.out.println("Audio copied successfully!");

			} catch (IOException e) {
				System.out.println("Something went wrong while copying!");
				e.printStackTrace();
				return;
			}
		} else {
			System.out.println("File already copied, so just playing it!");
		}

		// Play after 3 seconds
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(copiedFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);

			clip.start();
			System.out.println("Playback started!");

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					clip.stop();
					System.out.println("Playback stoped!");
				}
			}, 3000);

		} catch (UnsupportedAudioFileException e) {
			System.out.println("Audio file not supported!");
		} catch (LineUnavailableException e) {
			System.out.println("Line not available!");
		} catch (IOException e) {
			System.out.println("Something went wrong while playing!");
		}
	}
}