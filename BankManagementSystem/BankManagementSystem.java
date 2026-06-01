package BankManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class BankManagementSystem {

    static ArrayList<BankAccount> accounts =
            new ArrayList<>();

    static Scanner sc =
            new Scanner(System.in);

    public static BankAccount findAccount(
            long accNo) {

        for (BankAccount account :
                accounts) {

            if (account.getAccountNumber()
                    == accNo) {

                return account;
            }
        }

        return null;
    }

    public static void createAccount() {

        System.out.print(
                "Enter Account Number : ");

        long accNo =
                sc.nextLong();

        sc.nextLine();

        if (findAccount(accNo)
                != null) {

            System.out.println(
                    "Account Already Exists!");

            return;
        }

        System.out.print(
                "Enter Account Holder Name : ");

        String name =
                sc.nextLine();

        System.out.print(
                "Enter Account Type (Savings/Current) : ");

        String type =
                sc.nextLine();

        System.out.print(
                "Set 4 Digit PIN : ");

        int pin =
                sc.nextInt();

        if (pin < 1000 ||
                pin > 9999) {

            System.out.println(
                    "PIN must be 4 digits.");

            return;
        }

        System.out.print(
                "Enter Initial Balance : ");

        double balance =
                sc.nextDouble();

        accounts.add(
                new BankAccount(
                        accNo,
                        name,
                        type,
                        pin,
                        balance));

        System.out.println(
                "Account Created Successfully.");
    }

    public static void login() {

        System.out.print(
                "Enter Account Number : ");

        long accNo =
                sc.nextLong();

        BankAccount account =
                findAccount(accNo);

        if (account == null) {

            System.out.println(
                    "Account Not Found!");

            return;
        }

        System.out.print(
                "Enter PIN : ");

        int pin =
                sc.nextInt();

        if (!account.verifyPin(pin)) {

            System.out.println(
                    "Incorrect PIN!");

            return;
        }

        int choice;

        do {

            System.out.println(
                    "\n===== ACCOUNT MENU =====");

            System.out.println(
                    "1. Deposit");

            System.out.println(
                    "2. Withdraw");

            System.out.println(
                    "3. Check Balance");

            System.out.println(
                    "4. Transfer Money");

            System.out.println(
                    "5. Transaction History");

            System.out.println(
                    "6. Change PIN");

            System.out.println(
                    "7. Account Details");

            System.out.println(
                    "8. Logout");

            System.out.print(
                    "Enter Choice : ");

            choice =
                    sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print(
                            "Enter Amount : ");

                    account.deposit(
                            sc.nextDouble());

                    break;

                case 2:

                    System.out.print(
                            "Enter Amount : ");

                    account.withdraw(
                            sc.nextDouble());

                    break;

                case 3:

                    account.checkBalance();

                    break;

                case 4:

                    System.out.print(
                            "Enter Receiver Account Number : ");

                    long receiverNo =
                            sc.nextLong();

                    BankAccount receiver =
                            findAccount(receiverNo);

                    if (receiver == null) {

                        System.out.println(
                                "Receiver Account Not Found.");
                    }
                    else {

                        System.out.print(
                                "Enter Amount : ");

                        double amount =
                                sc.nextDouble();

                        account.transfer(
                                receiver,
                                amount);
                    }

                    break;

                case 5:

                    account.showTransactions();

                    break;

                case 6:

                    System.out.print(
                            "Enter Old PIN : ");

                    int oldPin =
                            sc.nextInt();

                    System.out.print(
                            "Enter New PIN : ");

                    int newPin =
                            sc.nextInt();

                    account.changePin(
                            oldPin,
                            newPin);

                    break;

                case 7:

                    account.displayDetails();

                    break;

                case 8:

                    System.out.println(
                            "Logged Out.");

                    break;

                default:

                    System.out.println(
                            "Invalid Choice.");
            }

        } while (choice != 8);
    }

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println(
                    "\n===== BANK MANAGEMENT SYSTEM =====");

            System.out.println(
                    "1. Create Account");

            System.out.println(
                    "2. Login");

            System.out.println(
                    "3. Exit");

            System.out.print(
                    "Enter Choice : ");

            choice =
                    sc.nextInt();

            switch (choice) {

                case 1:

                    createAccount();

                    break;

                case 2:

                    login();

                    break;

                case 3:

                    System.out.println(
                            "Thank You!");

                    break;

                default:

                    System.out.println(
                            "Invalid Choice.");
            }

        } while (choice != 3);
    }
}