import java.util.HashMap;
import java.util.Scanner;

class Account {
    protected int atmNumber;
    protected int pin;
    protected double balance;

    public Account(int atmNumber, int pin, double balance) {
        this.atmNumber = atmNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean authenticate(int atmNumber, int pin) {
        return this.atmNumber == atmNumber && this.pin == pin;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount entered.");
        } else if (amount <= balance) {
            balance -= amount;
            System.out.println("Please take your cash.");
            System.out.println("Remaining balance: Rs." + balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount entered.");
        } else {
            balance += amount;
            System.out.println("Deposited successfully. New balance: Rs." + balance);
        }
    }
}

class SavingsAccount extends Account {
    public SavingsAccount(int atmNumber, int pin, double balance) {
        super(atmNumber, pin, balance);
    }

    @Override
    public void withdraw(double amount) {
        
        if (amount <= 0) {
            System.out.println("Invalid amount entered.");
        } else if (amount <= balance - 500) {
            balance -= amount;
            System.out.println("Please take your cash.");
            System.out.println("Remaining balance: Rs." + balance);
        } else {
            System.out.println("Minimum balance of Rs.500 must be maintained.");
        }
    }
}

class CurrentAccount extends Account {
    public CurrentAccount(int atmNumber, int pin, double balance) {
        super(atmNumber, pin, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount entered.");
        } else if (amount <= balance + 10000) {
            balance -= amount;
            System.out.println("Withdraw successful (with overdraft if needed).");
            System.out.println("Current balance: Rs." + balance);
        } else {
            System.out.println("Overdraft limit exceeded.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

       
        HashMap<Integer, Account> accounts = new HashMap<>();
        accounts.put(1111111, new SavingsAccount(1111111, 1234, 50000.00));
        accounts.put(2222222, new CurrentAccount(2222222, 2003, 280000.00));
        accounts.put(3333333, new SavingsAccount(3333333, 1111, 10000.00));

        boolean exit = false;
        while (!exit) {
            System.out.print("Enter your ATM number: ");
            int atmNumber = sc.nextInt();

            System.out.print("Enter your PIN: ");
            int pin = sc.nextInt();

            Account acc = accounts.get(atmNumber);

            if (acc != null && acc.authenticate(atmNumber, pin)) {
                System.out.println("\nWelcome to the ATM!");

                boolean loggedIn = true;
                while (loggedIn) {
                    System.out.println("\n1. Check Balance");
                    System.out.println("2. Withdraw Money");
                    System.out.println("3. Deposit Money");
                    System.out.println("4. Exit");
                    System.out.print("Enter your choice: ");

                    int choice = sc.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.println("Your balance is: Rs." + acc.getBalance());
                            break;
                        case 2:
                            System.out.print("Enter amount to withdraw: Rs.");
                            double wAmt = sc.nextDouble();
                            acc.withdraw(wAmt);
                            break;
                        case 3:
                            System.out.print("Enter amount to deposit: Rs.");
                            double dAmt = sc.nextDouble();
                            acc.deposit(dAmt);
                            break;
                        case 4:
                            loggedIn = false;
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                }
                System.out.println("\nThank you for using the ATM!\n");
            } else {
                System.out.println("Invalid ATM number or PIN. Please try again.\n");
            }
        }

        sc.close();
    }
}
