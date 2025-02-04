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
        //Ref: https://stackoverflow.com/questions/4067809/how-to-allow-only-letters-in-a-string-using-java
        String namePattern = "^[A-Za-z]{2,25}(?:\\s[A-Za-z]{2,25})?$";

        while (true) {
            System.out.println("Enter " + fieldName + ": ");

            // Use nextLine() to capture the entire line of input
            String userInput = scanner.next();
            scanner.nextLine(); // Consume the newline character

            // Trim the input
            String name = userInput.trim();

            // Check if input is empty AFTER trimming
            if (name.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
                continue;
            }

            if (!name.matches(namePattern)) {
                System.out.println("Invalid format. Use 2-25 letters, optional second word.");
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
        //Ref: https://www.geeksforgeeks.org/check-email-address-valid-not-java/
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
    public static int validateMaxCapacity() {
        while (true) {
            try {
                int maxCapacity = Integer.parseInt(scanner.nextLine().trim());
                if (maxCapacity <= 0) {
                    System.out.println("Max capacity must be greater than 0. Please enter a valid number: ");
                    continue;
                }
                return maxCapacity;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format for max capacity. Please enter a valid number: ");
            }
        }
    }

    // validate password
    public static String validatePassword() {
        //Ref: https://www.geeksforgeeks.org/how-to-validate-a-password-using-regular-expressions-in-java/
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
        while (true) {
            String password = scanner.nextLine().trim();
            if (!password.matches(passwordPattern)) {
                System.out.println("Invalid password format. Please enter a valid password: ");
                continue;
            }
            return password;
        }
    }

    //validate MongoDB ID
    public static String validateMongoDBId() {
        //Ref: https://docs.mongodb.com/manual/reference/method/ObjectId/
        String idPattern = "^[0-9a-fA-F]{24}$";
        while (true) {
            String id = scanner.nextLine().trim();
            if (!id.matches(idPattern)) {
                System.out.println("Invalid ID format. Please enter a valid ID: ");
                continue;
            }
            return id;
        }
    }

    //close scanner
    public static void closeScanner() {
        scanner.close();
    }
}
