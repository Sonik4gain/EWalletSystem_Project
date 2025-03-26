package eWallet.Model.impl;

import eWallet.Model.Account;
import eWallet.Model.ApplicationService;

import java.util.Scanner;

public class ApplicationServiceImpl implements ApplicationService {

    private final Scanner scanner = new Scanner(System.in);
    private AccountService accountService = new AccountServiceImp();

    @Override
    public void start() {
        System.out.println("Welcome to eWallet System");
        int attempts = 0; // Track the number of invalid menu selections

        while (true) {
            System.out.println("Hi, you have multiple options:\n"
                    + "1) Login\n"
                    + "2) Create a new account\n"
                    + "3) Exit and logout\n"
                    + "Please insert the number that represents your wanted action!");

            // Ensure user enters a valid integer
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Consume invalid input
                attempts++; // Increase failed attempt count
                if (attempts >= 4) {
                    System.out.println("Too many invalid attempts. Exiting...");
                    return; // Exit the program completely
                }
                continue; // Restart loop
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline

            switch (choice) {
                case 1:
                    if (login()) { // If login is successful, exit start() and go to mainPage()
                        return;
                    }
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    System.out.println("Thank you for using eWallet System");
                    return; // Exit the program properly
                default:
                    System.out.println("Invalid choice! Please select a valid number.");
                    attempts++; // Track invalid menu attempts
                    break;
            }

            if (attempts >= 4) {
                System.out.println("Too many invalid attempts. Exiting...");
                return; // Exit the program completely
            }
        }
    }

    private boolean login() {
        int loginAttempts = 0; // Track failed login attempts

        while (loginAttempts < 4) {
            System.out.println("Please enter your username:");
            String username = scanner.nextLine();

            System.out.println("Please enter your password:");
            String password = scanner.nextLine();

            Account account = new Account(username, password);

            if (accountService.findAccount(account)) {
                System.out.println("Login successful! Redirecting to main page...");
                mainPage(account);
                return true; // Exit start() and prevent looping back
            } else {
                System.out.println("Invalid username or password. Please try again.");
                loginAttempts++; // Increase login attempt count
            }
        }

        System.out.println("Too many failed login attempts. Exiting...");
        return false; // Failed login, so return false to keep running start()
    }

    private void createAccount() {
        System.out.println("Please enter your username:");
        String username = scanner.nextLine();

        System.out.println("Please enter your password:");
        String password = scanner.nextLine();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username and password cannot be empty.");
            return;
        }

        Account account = new Account(username, password);
        boolean result = accountService.createAccount(account);

        if (result) {
            System.out.println("Account created successfully. You can now log in.");
        } else {
            System.out.println("Account creation failed. Username may already exist.");
        }
    }

    private void mainPage(Account account) {
        System.out.println("Welcome " + account.getUsername());
        System.out.println("You have multiple options:\n"
                + "1) Deposit\n"
                + "2) Withdraw\n"
                + "3) Transfer\n"
                + "4) Logout\n"
                + "Please insert the number that represents your wanted action!");
    }
}
