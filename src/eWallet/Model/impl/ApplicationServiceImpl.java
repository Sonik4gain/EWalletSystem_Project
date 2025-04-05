package eWallet.Model.impl;

import eWallet.Model.impl.Account;
import eWallet.Model.Interfaces.ApplicationService;
import eWallet.Model.Interfaces.AccountService;
import eWallet.Model.Interfaces.DataValidation;

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
        System.out.println("Welcome " + account.getUsername() + " your current balance is: $" + account.getBalance());

        while (true) {
            System.out.println("\nWhich action do you prefer?");
            System.out.println("1) Deposit");
            System.out.println("2) Withdraw");
            System.out.println("3) Transfer (Coming Soon)");
            System.out.println("4) Logout");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.next(); // Consume the invalid input
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    handleDeposit(account); // Deposit method
                    break;
                case 2:
                    handleWithdraw(account); // Withdraw method
                    break;
                case 3:
                    System.out.println("Transfer feature not yet implemented.");
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return; // Exit the mainPage
                default:
                    System.out.println("Invalid option. Please choose a number between 1 and 4.");
            }
        }
    }
    // it is better if functions have separate methods.
    private void handleDeposit(Account account) {
        while (true) {
            System.out.print("Enter amount to deposit: ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next(); // Clear the invalid input
                continue;
            }

            double deposit = scanner.nextDouble();
            scanner.nextLine(); // Clear the newline

            if (deposit <= 0 || deposit < 5) {
                System.out.println("The minimum amount to deposit is $5.");
            } else {
                account.deposit(deposit);
                System.out.println("Deposit successful. Current balance is: $" + account.getBalance());
                break;
            }
        }
    }

    private void handleWithdraw(Account account) {
        while (true) {
            System.out.print("Enter amount to withdraw: ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next(); // Clear invalid input
                continue;
            }

            double amount = scanner.nextDouble();
            scanner.nextLine(); // Clear the newline

            if (amount <= 0) {
                System.out.println("Withdrawal amount must be positive.");
            } else if (account.withdraw(amount)) {
                System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
                break;
            } else {
                System.out.println("Insufficient funds.");
            }
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


