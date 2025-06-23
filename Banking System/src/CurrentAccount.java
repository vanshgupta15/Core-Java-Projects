public class CurrentAccount extends Account
{
    private double overdraft=0.0;

    public CurrentAccount(String accountNumber, String accountHolderName, double balance) 
    {
        super(accountNumber, accountHolderName, balance);
    }
    double b=super.getBalance();
    
    @Override
    public void deposits(double d)
    {
        b=b+d;
        setBalance(b);
    }
    @Override
    public void withdraw(double w)
    {
        if((b-w)<-5000)
        {
            System.out.println("Withdrawal cannot be more than your overdraft limit");
        }
        else
        {
            b=b-w;
            setBalance(b);
            if(b<0)
            {
                overdraft=b;
            }
        }
    }
    @Override
    public void displayAccountDetails()
    {
        System.out.println("Account Number: "+getAccountNumber());
        System.out.println("Account type: Current Account");
        System.out.println("Holder name: "+getAccountHolderName());
        System.out.println("Balance :"+getBalance());
    }
}
