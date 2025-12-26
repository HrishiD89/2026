package exception;

import java.util.InputMismatchException;
import java.util.Scanner;

public class tryException {
	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter a number");
			int number = sc.nextInt();
			System.out.println(number);
			System.out.println(1 / 1);
		} catch (Exception e) {
			System.out.println("Something went wrong!");
			e.printStackTrace();
		} finally {
			System.out.println("This always executes!");

		}
	}
}
