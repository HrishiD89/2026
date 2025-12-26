package useregisration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserRegistration {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Please enter your name : ");
            String name = sc.nextLine();
            System.out.println("Enter your birthDate (yyyy-MM-dd) : ");
            String birthDateInput = sc.nextLine();

            LocalDate birthDate = LocalDate.parse(birthDateInput);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM dd,yyyy");

            String formattedBirthdate = birthDate.format(formatter);

            System.out.printf("%s birthdate is on %s", name, formattedBirthdate);
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }
}
