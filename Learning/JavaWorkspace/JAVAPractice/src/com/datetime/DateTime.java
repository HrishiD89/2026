package com.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTime {

	public static void main(String[] args) {

		LocalDate currentDate = LocalDate.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		String newDate = currentDate.format(formatter);
		
		System.out.printf("NewDate : %s%n",newDate);
	}

}
