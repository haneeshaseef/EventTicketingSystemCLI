package org.coursework.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public abstract class InputValidator {
    private final static Scanner scanner = new Scanner(System.in);

    //validate option number input
    public static int validateOptionNumberInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.print("Invalid input. Please check the menu and enter a valid number:");
                scanner.next();
            }
        }
    }

    //validate option alphabet input
    public static String validateOptionAlphabetInput(String options) {
        while (true) {
            String option = scanner.next().toLowerCase();
            if (options.contains(option)) {
                return option;
            } else {
                System.out.print("Invalid input. Please check the menu and enter a valid option:");
            }
        }
    }

    //Validate String input
    public static String validateTextField(String fieldName) {
        //String regex pattern for name validation
        String namePattern = "^[A-Za-z]{2,25}\\\\s[A-Za-z]{2,25}$";
        while (true) {
            System.out.println("Enter " + fieldName + ": ");
            String name = scanner.nextLine().trim();
            if (!name.matches(namePattern)) {
                System.out.printf("Invalid %s. Please enter a valid %s: ", fieldName, fieldName);
                continue;
            }
            return name;
        }
    }

    //validate Number input
    public static int validateNumberField(String fieldName) {
        while (true) {
            try {
                int rate = Integer.parseInt(scanner.nextLine().trim());
                if (rate <= 0) {
                    System.out.printf("%s must be greater than 0. Please enter a valid number: ", fieldName);
                    continue;
                }
                return rate;
            } catch (NumberFormatException e) {
                System.out.printf("Invalid number format for %s. Please enter a valid number: ", fieldName);
            }
        }
    }

    //validate email
    public static String validateEmail() {
        //Regex pattern for email validation
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        while (true) {
            String email = scanner.nextLine().trim();
            if (!email.matches(emailPattern)) {
                System.out.println("Invalid email format. Please enter a valid email: ");
                continue;
            }
            return email;
        }
    }

    //validate local time
    public static LocalDateTime validateLocalDate() {
         final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                LocalDate date = LocalDate.parse(input, INPUT_DATE_FORMATTER);
                LocalDateTime dateTime = date.atTime(12, 0);

                if (dateTime.isBefore(LocalDateTime.now())) {
                    System.out.println("Event date must be in the future. Please enter a valid date (format: yyyy/MM/dd): ");
                    continue;
                }

                return dateTime;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter date in format yyyy/MM/dd (e.g., 2024/12/31): ");
            }
        }
    }

    //validate max capacity
    public static int validateMaxCapacity(int totalTickets) {
        while (true) {
            try {
                int maxCapacity = Integer.parseInt(scanner.nextLine().trim());
                if (maxCapacity <= 0) {
                    System.out.println("Max capacity must be greater than 0. Please enter a valid number: ");
                    continue;
                }
                if (maxCapacity < totalTickets) {
                    System.out.println("Max capacity cannot be less than total tickets. Please enter a valid number: ");
                    continue;
                }
                return maxCapacity;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format for max capacity. Please enter a valid number: ");
            }
        }
    }

    public static void closeScanner() {
        scanner.close();
    }
}
