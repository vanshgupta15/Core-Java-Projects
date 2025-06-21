abstract public class Account extends Bank
{
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public Account(String accountNumber, String accountHolderName, double balance)
    {
        this.accountNumber=accountNumber;
        this.accountHolderName=accountHolderName;
        this.balance=balance;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }
    public String getAccountHolderName()
    {
        return accountHolderName;
    }
    public double getBalance()
    {
        return balance;
    }
    public void setBalance(double b)
    {
        balance=b;
    }
    abstract public void deposits(double d);
    abstract public void withdraw(double w);
    abstract public void displayAccountDetails();

}
