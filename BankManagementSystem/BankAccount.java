package BankManagementSystem;
import java.util.ArrayList;

public class BankAccount {

    private long accountNumber;
    private String accountHolder;
    private String accountType;
    private int pin;
    private double balance;

    private ArrayList<String> transactions =
            new ArrayList<>();

    public BankAccount(long accountNumber,
                       String accountHolder,
                       String accountType,
                       int pin,
                       double balance) {

        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.pin = pin;
        this.balance = balance;

        transactions.add(
                "Account Created with ₹" + balance);
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public boolean verifyPin(int enteredPin) {
        return pin == enteredPin;
    }

    public void changePin(int oldPin,
                          int newPin) {

        if (pin == oldPin) {

            pin = newPin;

            System.out.println(
                    "PIN Changed Successfully.");
        }
        else {

            System.out.println(
                    "Incorrect Old PIN.");
        }
    }

    public void deposit(double amount) {

        if (amount <= 0) {

            System.out.println(
                    "Invalid Amount!");

            return;
        }

        balance += amount;

        transactions.add(
                "Deposited ₹" + amount);

        System.out.println(
                "Deposit Successful.");
    }

    public void withdraw(double amount) {

        try {

            if (amount > balance) {

                throw new InsufficientBalanceException(
                        "Insufficient Balance!");
            }

            balance -= amount;

            transactions.add(
                    "Withdraw ₹" + amount);

            System.out.println(
                    "Withdrawal Successful.");

        } catch (InsufficientBalanceException e) {

            System.out.println(
                    e.getMessage());
        }
    }

    public void transfer(BankAccount receiver,
                         double amount) {

        try {

            if (amount > balance) {

                throw new InsufficientBalanceException(
                        "Insufficient Balance!");
            }

            balance -= amount;
            receiver.balance += amount;

            transactions.add(
                    "Transferred ₹" + amount +
                            " to " +
                            receiver.accountNumber);

            receiver.transactions.add(
                    "Received ₹" + amount +
                            " from " +
                            accountNumber);

            System.out.println(
                    "Transfer Successful.");

        } catch (InsufficientBalanceException e) {

            System.out.println(
                    e.getMessage());
        }
    }

    public void checkBalance() {

        System.out.println(
                "Current Balance : ₹" + balance);
    }

    public void displayDetails() {

        System.out.println(
                "\nAccount Number : " +
                        accountNumber);

        System.out.println(
                "Account Holder : " +
                        accountHolder);

        System.out.println(
                "Account Type : " +
                        accountType);

        System.out.println(
                "Balance : ₹" +
                        balance);
    }

    public void showTransactions() {

        System.out.println(
                "\nTransaction History");

        for (String transaction :
                transactions) {

            System.out.println(
                    transaction);
        }
    }
}