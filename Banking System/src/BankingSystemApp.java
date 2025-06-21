import java.util.Scanner;
public class BankingSystemApp 
{
    public static void main(String[] args) 
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("===== Java Bank Menu =====");
        System.out.println("1. Create New Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Display Account Details");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        int choice=sc.nextInt();
        switch(choice)
        {
            case 1:
            {
                System.out.println("Enter account holder name: ");
                String name= sc.nextLine();
                System.out.println("Enter account number: ");
                String accountNumber= sc.nextLine();
                System.out.println("Enter your initial balance: ");
                double balance= sc.nextDouble();
                System.out.println("Type of account:-\n1.Saving Account\n2.Current Account");
                int t= sc.nextInt();
                switch(t)
                {
                    case 1:
                    {
                        SavingAccount account= new SavingAccount(accountNumber, name, balance);
                        break;
                    }
                    case 2:
                    {
                        CurrentAccount account= new CurrentAccount(accountNumber, name, balance);
                        break;
                    }
                }
                break;
            }
            case 2:
            {
                System.out.println("Enter account number: ");
                String accountNumber= sc.nextLine();
                Account account = null;
                account.findAccount(accountNumber);
                System.out.println("Enter the amount to deposit: ");
                double deposit= sc.nextDouble();
                account.deposits(deposit);
            }
        }
    }
}
