package inheritanceconcept;

import java.lang.Math;
import java.util.*;
import java.util.stream.IntStream;
import java.io.*;
/*Bank class: Parent: findrateofInterest()
BankOfIndia: child: findrateofInterest()
SBI: findrateofInterest()
HDFC: findrateofInterest() 


1)	Write a java program with abstract Bank class which has abstract methods deposit and withdraw. 
When customer try to withdraw some amount 
if available balance is less than withdraw amount it should give error message. Make use of proper access modifiers. 

Create bank application in which yo are creating men driven program

Accept using input via Scanner class

Case 1: opening new accont
Case 2: Deposit Operation
case 3: Withdraw Operation
Case 4: exit 

*/

public class BankMain{
    private static final String messageString = """
        1 -> Create a new account
        2 -> Withdraw Money from your account
        3 -> Deposit Money to your account
        4 -> Check balance of your account
        5 -> Exit
        """;
    private static Hdfc obj1;
    public static void main(String[] args) throws IOException  {
        System.out.println();
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Hello User! What should I call you ? ");
        Integer max_tries = 3;
        String name = sc.readLine();
        while( !(nameValidation(name)) && (max_tries > 0)){
            max_tries -= 1;
            if(max_tries <= 0){
                break;
            }
            else{
                System.out.print("Hello User! Please re-enter your name:  ");
                name = sc.readLine();
            }
        }
        if(max_tries <= 0){
            System.out.print("Please try again after sometime.");
            System.exit(0);
        }
        max_tries = 3;
        while(max_tries > 0){
            System.out.println("\n\nHi! "+name+ ". We are glad to have you hear. So, How may I assist you today!\n");
            System.out.println("*".repeat(100));
            System.out.print("Please choose any number below to perform corresponding action:  ");
            System.out.print("\n\n"+messageString+"\n");
            System.out.println("*".repeat(100));
            System.out.print("\nInput your choice here:  ");
            Integer choice;
            try{
                choice = Integer.parseInt(sc.readLine());
            }
            catch(Exception e){
                choice = 10;
            }
            switch(choice){
                case 1:
                max_tries = takeAccountCreationInput(sc, max_tries);
                break;
                case 2:
                max_tries = takeAccountWithdrawlInput(sc, max_tries, true);
                break;
                case 3:
                max_tries = takeAccountWithdrawlInput(sc, max_tries, false);
                break;
                case 4:
                max_tries = getAccountBalanceInput(sc, max_tries);
                break;
                case 5:
                System.out.print("Thank you for banking with us !");
                max_tries = 0;
                break;
                default:
                max_tries = retriesReturn(max_tries);
            }
        }
        System.out.println("Hoping to see you soon !");
    }

    private static Integer retriesReturn(Integer max_tries){
        max_tries -= 1;
        System.out.print("You have "+max_tries+" left for correct queries this session.");
        return max_tries;
    }

    private static Integer takeAccountWithdrawlInput(BufferedReader sc, Integer max_tries, boolean withdraw){
        Long accountNumber;
        Double amount;
        try{
            System.out.print("Please Enter Your account Number opened with us:  ");
            accountNumber = Long.parseLong(sc.readLine());
            if(withdraw){
                System.out.print("Please Enter amount you want to withdraw:  ");
            }
            else{
                System.out.print("Please Enter amount you want to deposit:  ");
            }
            amount = Double.parseDouble(sc.readLine());
        }
        catch(Exception e){
            return retriesReturn(max_tries);
        }
        obj1 = new Hdfc(accountNumber, amount);
        if(withdraw){
            obj1.withdraw(accountNumber, amount);
        }
        else{
            obj1.deposit(accountNumber, amount);
        }
        return max_tries;
    }

    private static Integer getAccountBalanceInput(BufferedReader sc, Integer max_tries){
        Long accountNumber;
        try{
            System.out.print("Please Enter Your account Number opened with us:  ");
            accountNumber = Long.parseLong(sc.readLine());
        }
        catch(Exception e){
            return retriesReturn(max_tries);
        }
        obj1 = new Hdfc(accountNumber);
        obj1.accountBalance(accountNumber);
        return max_tries;
    }

    private static Integer takeAccountCreationInput(BufferedReader sc, Integer max_tries) throws IOException{
        System.out.print("Please Enter First Name and Middle Name:  ");
        String fName = sc.readLine();
        fName = fName.strip();
        System.out.print("Please Enter Last Name:  ");
        String lName = sc.readLine();
        lName = lName.strip();
        if( (!nameValidation(fName)) || (! nameValidation(lName))){
            return retriesReturn(max_tries);
        }
        System.out.print("Please Enter Opening Balance:  ");
        Double openingBalance;
        try{
            openingBalance = Double.parseDouble(sc.readLine().strip());
        }
        catch(Exception e){
            return retriesReturn(max_tries);
        }
        obj1 = new Hdfc(openingBalance, fName, lName);
        if(obj1.createAccount(0, fName, lName)){
            System.out.print("Congratulations "+fName+"! Your account is created successfully. Please note down account number stated above for all further communications with us. Thank you.");
        }
        else{
            System.out.print("Account creation failed. Please try after some time");
            System.exit(0);
        }
        return max_tries;
    }

    private static boolean nameValidation(String name){
        if(name.strip().length()<2){
            System.out.print("Please enter your real name:  ");
            return false;
        }
        return true;
    }
}

class AccountBalance{
    double accountBalance;
    long accountNumber;
    String fName;
    String lName;
}

abstract class Bank {
    double principal;
    double amount;
    double timePeriod;
    double frequency;
    String bankName;
    AccountBalance currObjectDetails;
    private double amountCreditorDebit;
    public Bank(double principal, double amount ,double timePeriod, double frequency, String bankName){
        this.principal = principal;
        this.amount = amount;
        this.timePeriod = timePeriod;
        this.frequency = frequency;
        this.bankName = bankName;
        System.out.println("Bank class is object is initialized by: "+bankName);
    }

    public Bank(double openingBalance, String fName, String lName){
        currObjectDetails = new  AccountBalance();
        currObjectDetails.accountBalance = openingBalance;
        currObjectDetails = setName(currObjectDetails, fName, lName);
    }

    public Bank(long accountNumber, double amountCreditorDebit){
        currObjectDetails = new AccountBalance();
        currObjectDetails.accountNumber = accountNumber;
        this.amountCreditorDebit = amountCreditorDebit;
    }

    public Bank(long accountNumber){
        currObjectDetails = new AccountBalance();
        currObjectDetails.accountNumber = accountNumber;
    }

    public AccountBalance setName(AccountBalance personDetails, String fName, String lName){
        personDetails.fName = fName;
        personDetails.lName = lName;
        return personDetails;
    }

    protected void createAccount(double openingBalance, String fName, String lName, long accountNumber){
        currObjectDetails.accountNumber = accountNumber;
    }

    protected boolean accountExsistence(long account_number, List<AccountBalance> CustBalance){
        for (AccountBalance accountBalance : CustBalance) {
            if (accountBalance.accountNumber == account_number){
                return true;
            }
        }
        System.out.println(String.format("Account Number %d Not Found or created", account_number));
        return false;
    }
    
    protected abstract boolean deposit(long account_number, double amnt);
    protected abstract boolean withdraw(long account_number, double amnt);

    /*
    r = n((A/P)1/nt - 1)
    */
    public double rateofIntrest(){
        double rate;
        System.out.printf("Frequency of compunding is: %s in %s",frequency, bankName);
        rate = (frequency * ( Math.pow((amount/principal), (1.0/(frequency*timePeriod))) - 1 ))*100;
        return rate;
    }
}

class Hdfc extends Bank{
    // Assume bank of india calculates compound intrest quaterly, compound 4 times a year
    private static final double frequency = 4;
    protected static final String hdfcStartString = "HDFC";
    private static final Integer bankCode = 50;
    public static final Integer branchCode = 4807;
    private static final Integer savingsAccountPrefix = 324;
    protected static Integer nextAvaAccountNumber = 10000;
    protected static List<AccountBalance> CustBalance = new ArrayList<>(Arrays.asList());

    public Hdfc(double principal, double amount ,double timePeriod){
        super(principal, amount, timePeriod, frequency, "Housing Development Finance Corporation Limited Bank");
    }

    // This constructor is called to create a new account
    public Hdfc(double balance, String fName, String lName){
        super(balance, fName, lName);
    }

    protected Hdfc(long accountNumber, double amountCreditorDebit){
        super(accountNumber, amountCreditorDebit);
    }

    protected Hdfc(long accountNumber){
        super(accountNumber);
    }

    private static String getNewAccountNumber(){
        return ""+Hdfc.bankCode+Hdfc.branchCode+Hdfc.savingsAccountPrefix+(++Hdfc.nextAvaAccountNumber);
    }

    protected boolean createAccount(double openingBalance, String fName, String lName){
        long accountNumber = Long.parseLong(getNewAccountNumber());
        super.createAccount(openingBalance, fName, lName, accountNumber);
        CustBalance.add(super.currObjectDetails);
        if (super.accountExsistence(accountNumber, CustBalance)){
            System.out.println(String.format("Hi! %s . Account Creation Successful. Welcome to %s. Your account Number is: %s",fName, hdfcStartString, accountNumber));
            return true;
        }
        else{
            System.out.println(String.format("Hi! %s. Your account creation failed. Please try after sometime.", fName));
            return false;
        }
    }

    protected void accountBalance(long accountNumber){
        for (AccountBalance accountBalance : CustBalance) {
            if (accountBalance.accountNumber == accountNumber){
                System.out.println(String.format("Account Balance: %.2f for account number: %d", accountBalance.accountBalance, accountNumber));
                return;
            }
        }
        System.out.println(String.format("Account Number %d Not Found or created", accountNumber));
        return;
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
