package ATM;

import java.util.Scanner;

class BankAccount {

    private int balance;

    public BankAccount(int balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = balance;
    }

    public boolean withdraw(int amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than 0.");
            return false;
        }
        if (balance < amount) {
            System.out.println("Insufficient funds. Withdrawal failed.");
            return false;
        }
        balance -= amount;
        return true;
    }

    public boolean deposit(int amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than 0.");
            return false;
        }
        balance += amount;
        return true;
    }

    public int getBalance() {
        return balance;
    }
}

public class ATM {

    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount you want to withdraw: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
        }
        int amount = scanner.nextInt();

        if (bankAccount.withdraw(amount)) {
            System.out.println("Amount successfully withdrawn: " + amount);
        }
    }

    public void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount you want to deposit: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
        }
        int amount = scanner.nextInt();

        if (bankAccount.deposit(amount)) {
            System.out.println("Amount successfully deposited: " + amount);
        }
    }

    public void checkBalance() {
        System.out.println("Your account balance is: " + bankAccount.getBalance());
    }

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(1000); // Initial balance
        ATM atm = new ATM(bankAccount);

        int choice;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\nATM Menu");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Please enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    atm.withdraw();
                    break;
                case 2:
                    atm.deposit();
                    break;
                case 3:
                    atm.checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
