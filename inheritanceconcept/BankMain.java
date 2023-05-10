package inheritanceconcept;

import java.lang.Math;
/*Bank class: Parent: findrateofInterest()
BankOfIndia: child: findrateofInterest()
SBI: findrateofInterest()
HDFC: findrateofInterest() */

public class BankMain{
    public static void main(String[] args){
        System.out.println();
        BankOfIndia boi = new BankOfIndia(40000, 80000, 10);
        System.out.printf("Rate of Intrest : %.2f per annum\n\n",boi.rateofIntrest());

        Sbi sbi = new Sbi(40000, 81000, 10);
        System.out.printf("Rate of Intrest : %.2f per annum\n\n",sbi.rateofIntrest());

        Hdfc hdfc = new Hdfc(40000, 81500, 10);
        System.out.printf("Rate of Intrest : %.2f per annum\n\n",hdfc.rateofIntrest());
    }
}


class Bank {
    double principal;
    double amount;
    double timePeriod;
    double frequency;
    String bankName;
    public Bank(double principal, double amount ,double timePeriod, double frequency, String bankName){
        this.principal = principal;
        this.amount = amount;
        this.timePeriod = timePeriod;
        this.frequency = frequency;
        this.bankName = bankName;
        System.out.println("Bank class is object is initialized by: "+bankName);
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
}

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

class Hdfc extends Bank{
    // Assume bank of india calculates compound intrest quaterly, compound 4 times a year
    static final double frequency = 4;
    public Hdfc(double principal, double amount ,double timePeriod){
        super(principal, amount, timePeriod, frequency, "Housing Development Finance Corporation Limited Bank");
    }

    public double rateofIntrest(){
        System.out.println("Calculating Your rate of intrest in HDFC");
        return super.rateofIntrest();
    }
}
