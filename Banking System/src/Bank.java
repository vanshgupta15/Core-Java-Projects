public class Bank 
{
    private Account accounts[];
    private int accountCount;
    
    public Bank()
    {
        accounts=new Account[100];
        accountCount=0;
    }

    public void addAccount(Account account) 
    {
        accounts[accountCount] = account;
        accountCount++;
    }


    public Account findAccount(String accountNumber)
    {
        for(int i=0; i<accountCount; i++)
        {
            if(accounts[i].getAccountNumber().equals(accountNumber))
            {
                System.out.println("Account found");
                return accounts[i];
            }
        }
        return null;
    }

    public void displayAllAccounts()
    {
        for(int i = 0; i < accounts.length; i++) 
        {
            System.out.println(accounts[i]);
            System.out.println("-----------------------------");
        }
    }
}
