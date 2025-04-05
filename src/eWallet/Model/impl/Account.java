package eWallet.Model.impl;

public class Account {
    private String username;
    private String password;
    private boolean active;
    private double balance = 500; //set default balance to 500 for testing purposes

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.active = true;
    }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
        else {
            System.out.println("Deposit amount should be positive");

        }
    }
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}
