public class Bank 
{
    private Account accounts[];
    private int accountCount;

    public int getAccountCount()
    {
        return accountCount;
    }

    public void addAccount(Account account) 
    {
        accounts[accountCount] = account;
        accountCount++;
    }


    public Account findAccount(String acc)
    {
        for(int i=0; i<accounts.length; i++)
        {
            if(acc.equalsIgnoreCase((accounts[i]).getAccountNumber()))
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
        }
    }
}
