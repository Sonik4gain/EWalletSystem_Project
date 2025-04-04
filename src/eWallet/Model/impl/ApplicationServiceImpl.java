package eWallet.Model.impl;

import eWallet.Model.Account;
import eWallet.Model.ApplicationService;

import java.util.Scanner;

public class ApplicationServiceImpl implements ApplicationService {

    private final Scanner scanner = new Scanner(System.in);

    private AccountService accountService = new AccountServiceImp();
    private DataValidation dataValidation= new DataValidationImpl(); // data validation object

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

        if (username.isEmpty() || password.isEmpty()) { // this method is to check if the username and password are empty
            System.out.println("Username and password cannot be empty."); // also this method should be first to run, learned this from the last time.
            return;
        }

        if (!dataValidation.validateUsername(username)) { // this method is to check if the username is valid
            System.out.println("Username must start with uppercase and be at least 3 characters.");
            return;
        }
        if (!dataValidation.validatePassword(password)) { // this method is to check if the password is valid
            return; // no need to print anything here, the validation method already prints the error
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
//        System.out.println("Which action do you prefer?\n"
//                + "1) Deposit\n"
//                + "2) Withdraw\n"
//                + "3) Transfer\n"
//                + "4) Logout\n"
//                + "Please insert the number that represents your wanted action!");

        //TODO : Replace with loop + validation logic
        System.out.println("1) Deposit\n2) Withdraw\n3) Transfer\n4) Logout"); // not sure to leave this like this or not
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                // TODO: Replace with loop + validation logic
                System.out.print("Enter amount to deposit: ");
                double deposit = scanner.nextDouble();
                account.deposit(deposit);
                System.out.println("New balance: $" + account.getBalance());
                break;

            case 2:
                // TODO: Replace with loop + validation logic
                System.out.print("Enter amount to withdraw: ");
                double withdraw = scanner.nextDouble();
                if (withdraw < 0) {
                    System.out.println("Withdraw amount should be positive");
                    return;
                }
                if (account.withdraw(withdraw)) {
                    System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
                } else {
                    System.out.println("Insufficient funds.");
                }
                break;

            case 3:
                System.out.println("Transfer feature not yet implemented.");
                break;

            case 4:
                return;
            default:
                System.out.println("Invalid option.");
        }
    }


    private Account extractAccount(){
        System.out.println("please enter your name");
        String userName= scanner.nextLine();
        // want to create another layer of username validation
        System.out.println("please enter your password");
        String password= scanner.nextLine();
        //want to create another layer of validation

        return new Account(userName,password);

    }
}


