import java.util.Scanner;

//Class representing the user's bank account
class BankAccount {
 private double balance;

 // Constructor to initialize the balance
 public BankAccount(double initialBalance) {
     this.balance = initialBalance;
 }

 // Method to get the current balance
 public double getBalance() {
     return balance;
 }

 // Method to deposit money into the account
 public void deposit(double amount) {
     balance += amount;
     System.out.println("Amount credited to your Bank Account. Current balance: " + balance);
 }

 // Method to withdraw money from the account
 public boolean withdraw(double amount) {
     if (amount <= balance) {
         balance -= amount;
         System.out.println("Amount debited from your Bank Account. Current balance: " + balance);
         return true;
     } else {
         System.out.println("Insufficient funds. Withdrawal failed.");
         return false;
     }
 }
}

//Class representing the ATM machine
public class ATM {
 private BankAccount userAccount;

 // Constructor to link the ATM with a user's bank account
 public ATM(BankAccount userAccount) {
     this.userAccount = userAccount;
 }

 // Method to display the main menu and handle user input
 public void displayMenu() {
     Scanner scanner = new Scanner(System.in);

     while (true) {
         System.out.println("\nServices Available:");
         System.out.println("1. Withdraw");
         System.out.println("2. Deposit");
         System.out.println("3. Check Balance");
         System.out.println("4. Exit");
         System.out.print("Choose an option (1-4) : ");

         int choice;

         // Validate user input
         while (true) {
             try {
                 choice = Integer.parseInt(scanner.next());
                 break;
             } catch (NumberFormatException e) {
                 System.out.println("Invalid entry!! Please enter a valid operation to perform.");
                 System.out.print("Choose an option (1-4): ");
             }
         }

         switch (choice) {
             case 1:
                 // Withdraw
                 System.out.print("Enter the amount to withdraw: ");
                 double withdrawAmount;

                 // Validate user input for withdrawal amount
                 while (true) {
                     try {
                         withdrawAmount = Double.parseDouble(scanner.next());
                         break;
                     } catch (NumberFormatException e) {
                         System.out.println("Invalid entry!! Please enter a number.");
                         System.out.print("Enter withdrawal amount: ");
                     }
                 }

                 if (withdrawAmount > 0) {
                     userAccount.withdraw(withdrawAmount);
                 } else {
                     System.out.println("Invalid withdrawal amount. Please enter a positive number.");
                 }
                 break;

             case 2:
                 // Deposit
                 System.out.print("Enter deposit amount: ");
                 double depositAmount;

                 // Validate user input for deposit amount
                 while (true) {
                     try {
                         depositAmount = Double.parseDouble(scanner.next());
                         break;
                     } catch (NumberFormatException e) {
                         System.out.println("Invalid entry!! Please enter a number.");
                         System.out.print("Enter deposit amount: ");
                     }
                 }

                 if (depositAmount > 0) {
                     userAccount.deposit(depositAmount);
                 } else {
                     System.out.println("Invalid deposit amount. Please enter a positive number.");
                 }
                 break;

             case 3:
                 // Check Balance
                 System.out.println("Current balance: " + userAccount.getBalance());
                 break;

             case 4:
                 // Exit
                 System.out.println("Thank you for using the ATM. Have a nice day!");
                 scanner.close();
                 System.exit(0);

             default:
                 System.out.println("Invalid option. Please choose a valid option.");
         }
     }
 }

 public static void main(String[] args) {
     // Create a user's bank account with an initial balance
     BankAccount userAccount = new BankAccount(1000.0);

     // Create an ATM linked to the user's bank account
     ATM atm = new ATM(userAccount);

     // Display the ATM menu
     atm.displayMenu();
 }
}

