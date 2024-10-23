
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static HashMap<Integer, Integer> accounts = new HashMap<Integer, Integer>();

    public static void main(String[] args) {

        accounts.put(1111111, 1234);
        accounts.put(2222222, 2003);
        accounts.put(3333333, 1111);
        HashMap<Integer, Double> balances = new HashMap<Integer, Double>();
        balances.put(1111111, 500000.00);
        balances.put(2222222, 280000.00);
        balances.put(3333333, 10000.00);

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.print("Enter your ATM number: ");
            int atmNumber = sc.nextInt();

            System.out.print("Enter your PIN: ");
            int pin = sc.nextInt();

            if (authenticate(atmNumber, pin)) {
                System.out.println("Welcome to the ATM!");

                boolean loggedIn = true;

                while (loggedIn) {
                    System.out.println("Please enter an option:");
                    System.out.println("1. Check balance");
                    System.out.println("2. Withdraw money");
                    System.out.println("3. Deposit money");
                    System.out.println("4. Exit");
                    System.out.print("Enter your choice: ");

                    int choice = sc.nextInt();

                    switch (choice) {
                        case 1:
                            checkBalance(balances.get(atmNumber));
                            break;
                        case 2:
                            withdraw(balances.get(atmNumber));
                            break;
                        case 3:
                            deposit(balances.get(atmNumber));
                            break;
                        case 4:
                            loggedIn = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }

                    System.out.println();
                }
                exit = true;
            } else {
                System.out.println("Invalid. Please try again.");
            }

            System.out.println();
            sc.nextLine();
        }

        sc.close();
        System.out.println("Thank you for using the ATM!");
    }

    private static boolean authenticate(int atmNumber, int pin) {

        if (accounts.containsKey(atmNumber) && accounts.get(atmNumber).equals(pin)) {
            return true;
        } else {
            return false;
        }
    }

    private static void checkBalance(double balance) {
        System.out.println("Your balance is: Rs." + balance);
    }

    private static void withdraw(double balance) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter amount to withdraw: Rs.");
        double amount = sc.nextDouble();

        if (amount <= balance) {
            balance -= amount;
            System.out.println("Please take your money.");
            System.out.println("Your remaining balance is: Rs." + balance);
        } else {
            System.out.println("Insufficient funds.");
        }

    }

    private static void deposit(double balance) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter amount to deposit: Rs.");
        double amount = sc.nextDouble();

        balance += amount;
        System.out.println("Your new balance is: RS." + balance);

    }
}
