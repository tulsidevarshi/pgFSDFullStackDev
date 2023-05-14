package inheritanceconcept;

import java.lang.Math;
import java.util.*;

/*Bank class: Parent: findrateofInterest()
BankOfIndia: child: findrateofInterest()
SBI: findrateofInterest()
HDFC: findrateofInterest() 


1)	Write a java program with abstract Bank class which has abstract methods deposit and withdraw. 
When customer try to withdraw some amount 
if available balance is less than withdraw amount it should give error message. Make use of proper access modifiers. */

public class BankMain{
    public static void main(String[] args){
        System.out.println();
        Hdfc hdfc = new Hdfc(40000, 1234567890);
        hdfc.deposit(1234567890, 10);
        hdfc.withdraw(1234567890, 40010);
        hdfc.accountExsistence(123456789);
        System.out.println(hdfc.getBalance(123456789));
        // BankOfIndia boi = new BankOfIndia(40000, 80000, 10);
        // System.out.printf("Rate of Intrest : %.2f per annum\n\n",boi.rateofIntrest());

        // Sbi sbi = new Sbi(40000, 81000, 10);
        // System.out.printf("Rate of Intrest : %.2f per annum\n\n",sbi.rateofIntrest());

        // Hdfc hdfc = new Hdfc(40000, 81500, 10);
        // System.out.printf("Rate of Intrest : %.2f per annum\n\n",hdfc.rateofIntrest());
    }
}

class AccountBalance{
    double accountBalance;
    long accountNumber;
}

abstract class Bank {
    double principal;
    double amount;
    double timePeriod;
    double frequency;
    String bankName;
    private double balance;
    protected List<AccountBalance> CustBalance = new ArrayList<>(Arrays.asList());
    public Bank(double principal, double amount ,double timePeriod, double frequency, String bankName){
        this.principal = principal;
        this.amount = amount;
        this.timePeriod = timePeriod;
        this.frequency = frequency;
        this.bankName = bankName;
        System.out.println("Bank class is object is initialized by: "+bankName);
    }

    public Bank(double balance, long accountNumber){
        AccountBalance ab = new AccountBalance();
        ab.accountBalance = balance;
        ab.accountNumber = accountNumber;
        CustBalance.add(ab);
    }

    /*
    r = n((A/P)1/nt - 1)
    */
    public double rateofIntrest(){
        double rate;
        System.out.printf("Frequency of compunding is: %s in %s",frequency, bankName);
        rate = (frequency * ( Math.pow((amount/principal), (1.0/(frequency*timePeriod))) - 1 ))*100;
        return rate;
    }

    protected abstract boolean deposit(long account_number, double amnt);
    protected abstract boolean withdraw(long account_number, double amnt);

    public boolean accountExsistence(long account_number){
        for (AccountBalance accountBalance : CustBalance) {
            if (accountBalance.accountNumber == account_number){
                return true;
            }
        }
        System.out.println(String.format("Account Number %d Not Found", account_number));
        return false;
    }
    
    public String getBalance(long account_number){
        if (!accountExsistence(account_number)){
            return "0";
        }
        return Double.toString(balance);
    }
}

class Hdfc extends Bank{
    // Assume bank of india calculates compound intrest quaterly, compound 4 times a year
    static final double frequency = 4;
    public Hdfc(double principal, double amount ,double timePeriod){
        super(principal, amount, timePeriod, frequency, "Housing Development Finance Corporation Limited Bank");
    }

    public Hdfc(double balance, long accountNumber){
        super(balance, accountNumber);
    }

    public double rateofIntrest(){
        System.out.println("Calculating Your rate of intrest in HDFC");
        return super.rateofIntrest();
    }

    @Override
    protected boolean deposit(long account_number, double amnt){
        // Validation to be added
        for (AccountBalance accountBalance : CustBalance) {
            if (accountBalance.accountNumber == account_number){
                accountBalance.accountBalance += amnt;
                System.out.println(String.format("Amount left: %.5f for account number %d", accountBalance.accountBalance, account_number));
                return true;
            }
        }
        System.out.println(String.format("Account Number %d Not Found", account_number));
        return false;
    }

    @Override
    protected boolean withdraw(long account_number, double amnt){
        for (AccountBalance accountBalance : CustBalance) {
            if ((accountBalance.accountNumber == account_number)){
                if (accountBalance.accountBalance >= amnt){
                    accountBalance.accountBalance -= amnt;
                    System.out.println(String.format("Amount left: %.5f for account number %d", accountBalance.accountBalance, account_number));
                    return true;
                }
                else{
                    System.out.println(String.format("Insufficient balance in Account Number %d", account_number));
                    return false;
                }
            }
        }
        System.out.println(String.format("Account Number %d Not Found", account_number));
        return false; 
    }
}

/*
class BankOfIndia extends Bank{
    // Assume bank of india calculates compound intrest yearly
    static final double frequency = 1;
    public BankOfIndia(double principal, double amount ,double timePeriod){
        super(principal, amount, timePeriod, frequency, "Bank Of India");
    }

    public double rateofIntrest(){
        System.out.println("Calculating Your rate of intrest in Bank of india");
        return super.rateofIntrest();
    }
}

class Sbi extends Bank{
    // Assume bank of india calculates compound intrest half Yearly
    static final double frequency = 2;
    public Sbi(double principal, double amount ,double timePeriod){
        super(principal, amount, timePeriod, frequency, "State Bank Of India");
    }

    public double rateofIntrest(){
        System.out.println("Calculating Your rate of intrest in SBI");
        return super.rateofIntrest();
    }
}
 */
