import java.util.Scanner;

public class BankingSystemApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean check = true;

        while (check) {
            System.out.println("\n===== Java Bank Menu =====");
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display Account Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); 
            Bank bank = new Bank();

            switch (choice) {
                case 1: 
                {
                    System.out.print("Enter account holder name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter account number: ");
                    String accountNumber = sc.nextLine();
                    System.out.print("Enter your initial balance: ");
                    double balance = sc.nextDouble();
                    sc.nextLine(); 
                    System.out.println("Type of account:-\n1. Saving Account\n2. Current Account");
                    int t = sc.nextInt();
                    sc.nextLine();
                    switch (t) 
                    {
                        case 1:
                            SavingAccount acc1 = new SavingAccount(accountNumber, name, balance);
                            bank.addAccount(acc1);
                            break;
                        case 2:
                            CurrentAccount acc2 = new CurrentAccount(accountNumber, name, balance);
                            bank.addAccount(acc2);
                            break;
                    }
                    break;
                }
                case 2: 
                {
                    System.out.print("Enter account number: ");
                    String depositAccountNumber = sc.next();
                    Account depositAccount = bank.findAccount(depositAccountNumber);
                    if (depositAccount != null) {
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = sc.nextDouble();
                        depositAccount.deposits(depositAmount);;
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                }
                case 3: 
                {
                    System.out.print("Enter account number: ");
                    String withdrawAccountNumber = sc.next();
                    Account withdrawAccount = bank.findAccount(withdrawAccountNumber);
                    if (withdrawAccount != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = sc.nextDouble();
                        withdrawAccount.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                }
                case 4: 
                {
                    bank.displayAllAccounts();
                    break;
                }
                case 5: {
                    System.out.println("Thank you for using Java Bank. Goodbye!");
                    check = false;
                    break;
                }
                default:
                    System.out.println("Invalid choice. Please select between 1 to 5.");
            }
        }
    }
}