public class SavingAccount extends Account
{
    private double interestRate=0.03;

    public SavingAccount(String accountNumber, String accountHolderName, double balance) 
    {
        super(accountNumber, accountHolderName, balance);
    }
    double b=super.getBalance();
    double a;
    @Override
    public void deposits(double d)
    {
        a=b+(b*interestRate)+d;
        setBalance(a);
    }
    @Override
    public void withdraw(double w)
    {
        if((b-w)<0)
        {
            System.out.println("Withdrawal cannot be more than your balance");
        }
        else
        {
            b=b-w;
            setBalance(b);
        }
    }
    @Override
    public void displayAccountDetails()
    {
        System.out.println("Account Number: "+getAccountNumber());
        System.out.println("Account type: Saving Account");
        System.out.println("Holder name: "+getAccountHolderName());
        System.out.println("Balance :"+getBalance());
    }
}
