package eWallet.Model.impl;

import eWallet.Model.Account;
import eWallet.Model.ApplicationService;

import java.util.Scanner;

public class ApplicationServiceImpl implements ApplicationService {

         private final Scanner scanner = new Scanner(System.in);

         private  AccountService accountService= new AccountServiceImp();

    @Override
    public void start() {
        System.out.println("Welcome to eWallet System");
        int attempts = 0; // Track the number of invalid input attempts

        while (true) {
            System.out.println("Hi, you have multiple options:\n"
                    + "1) Login\n"
                    + "2) Create a new account\n"
                    + "3) Exit and logout\n"
                    + "Please insert the number that represents your wanted action!");

            //  Issue: If the user enters a string instead of a number, the program used to run forever
            //  Fix: Check if input is an integer first
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); //  Consume the invalid input (to avoid infinite loop)
                attempts++; //  Count invalid attempts
                if (attempts >= 4) { // If 4 incorrect attempts, exit the program :)
                    System.out.println("Too many invalid attempts. Exiting...");
                    break;
                }
                continue; // Restart the loop and ask for input again
            }

            int choice = scanner.nextInt(); //  Read user input (integer)
            scanner.nextLine(); //  Consume leftover newline character (prevents skipping input in login/createAccount)

            switch (choice) {
                case 1:
                    login();  // Call login method
                    attempts = 0; //  Reset attempts after a successful action
                    break;
                case 2:
                    createAccount();  // Call createAccount method
                    attempts = 0; //  Reset attempts after a successful action
                    break;
                case 3:
                    System.out.println("Thank you for using eWallet System");
                    return; //  Exit the program properly
                           // why return? It exits the entire method immediately
                default:
                    System.out.println("Invalid choice! Please select a valid number.");
                    attempts++; //  Increase invalid attempt count due to invalid choice
                    break;
            }
            //  Issue: Previously, the program didn’t exit after too many invalid choices / only exits by choosing exit!
            //  Fixed: Added max attempts limit
            if (attempts >= 4) {
                System.out.println("Too many invalid attempts. Exiting...");
                break;
            }
        }

        scanner.close(); // ✅ Close scanner properly when exiting the program
    }
    private void login() {
        System.out.println("Please enter your username");
        String username = scanner.nextLine(); // Removed extra scanner.nextLine()

        System.out.println("Please enter your password");
        String password = scanner.nextLine();

        Account account = new Account(username, password);

        boolean existingAccount = accountService.findAccount(account);
        if (existingAccount) {
            mainPage(account);
        } else {
            System.out.println("Invalid username or password");
        }

    }

    private void createAccount() {
        System.out.println("Please enter your username");
        String username = scanner.nextLine(); // Removed extra scanner.nextLine()

        System.out.println("Please enter your password");
        String password = scanner.nextLine();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username and password cannot be empty.");
            return;
        }

        Account account = new Account(username, password);
        boolean result = accountService.createAccount(account);

        if (result) {
            System.out.println("Account created successfully");
        } else {
            System.out.println("Account creation failed. Username may already exist.");
        }
    }

    private void mainPage(Account account){
        System.out.println("Welcome "+account.getUsername());
        System.out.println("You have multiple options:\n"
                + "1) Deposit\n"
                + "2) Withdraw\n"
                + "3) Transfer\n"
                + "4) Logout\n"
                + "Please insert the number that represents your wanted action!");
    }
}
