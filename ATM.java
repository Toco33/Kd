import java.util.HashMap;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String pin;
    private double balance;

    public BankAccount(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean validatePin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: ₹" + balance);
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: ₹" + balance);
        } else {
            System.out.println("Invalid amount.");
        }
    }
}

public class ATM {
    private HashMap<String, BankAccount> accounts = new HashMap<>();
    private BankAccount currentAccount;

    public ATM() {
        // Initialize some accounts (for testing)
        accounts.put("123456789", new BankAccount("123456789", "1234", 10000.0));
        accounts.put("987654321", new BankAccount("987654321", "4321", 5000.0));
    }

    public boolean authenticate(String accountNumber, String pin) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null && account.validatePin(pin)) {
            currentAccount = account;
            return true;
        }
        return false;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 4) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Your balance is: ₹" + currentAccount.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    currentAccount.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = scanner.nextDouble();
                    currentAccount.deposit(depositAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using our ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM!");
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (atm.authenticate(accountNumber, pin)) {
            atm.displayMenu();
        } else {
            System.out.println("Authentication failed. Please check your account number and PIN.");
        }
        scanner.close();
    }
}
