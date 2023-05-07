/*
 * write java Program for calculate the salary of emp: 
 * Accept eid, ename, age, basic salary, DA and TA 
 * using constrort
 */
import static java.lang.Math.min;
public class Emp {
    static final double travel_allowance_upper_limit = (double)200000;
    private long eid;
    private String ename;
    private short age;
    private double base_salary;
    private double rent_paid_by_emp;
    private boolean metro_city_residence;
    private double travel_allowance_allocated_so_far;
    private double housing_rent_allowance = 0;
    private double basic_salary = 0;
    public Emp(long eid, String ename, short i, double base_salary, double rent_paid_by_emp, boolean metro_city_residence, double d){
        this.setEID(eid);
        this.setName(ename);
        this.setAge(i);
        this.setBasicSalary(base_salary);
        this.setRentPaid(rent_paid_by_emp);
        this.setMetroCityRes(metro_city_residence);
        printString();
        this.setTravelAllowance(d);
        if (this.housing_rent_allowance == 0){
            this.hra();
        }
        setBasicSalary();
    }

    public void setBasicSalary(){
        this.basic_salary = this.base_salary * 0.4;
        System.out.println("EID: "+this.eid+" named: "+this.ename+" has basic salary: "+this.basic_salary);
    }

    public void printString(){
		System.out.println("Data Entry successful for eid: "+this.eid+" and employee name as : "+this.ename+" with age: "+this.age);
	}

    public void setEID(long eid){
        this.eid = eid;
    }

    public void setName(String ename){
        if((ename.strip().length()) == 0){
            throw new IllegalArgumentException("Name can not be empty");
        }
        else{
            this.ename = ename;
        }

    }

    public void setAge(short age){
        if (age < 18.0){
            throw new IllegalArgumentException("Age can not be less than 18");
        }
        else{
            this.age = age;
        }
    }

    public void setBasicSalary(double base_salary){
        if(base_salary <= 0){
            System.out.println("Salary can not be zero or negative. Minumum salary set is 2000");
            this.base_salary = 2000;
        }
        else{
            this.base_salary = base_salary;
        }
    }

    public void setRentPaid(double rent_paid_by_emp){
        if(rent_paid_by_emp < 0){
            System.out.println("Rent paid can not be negative. Value is set at zero");
            this.rent_paid_by_emp = 0;
        }
        else{
            this.rent_paid_by_emp = rent_paid_by_emp;
        }
    }

    public void setMetroCityRes(boolean metro_city_residence){
        this.metro_city_residence = metro_city_residence;
    }

    public void setTravelAllowance(double travel_allowance_allocated_so_far){
        if(travel_allowance_allocated_so_far <= travel_allowance_upper_limit){
            this.travel_allowance_allocated_so_far = travel_allowance_allocated_so_far;
        }
        else{
            throw new IllegalArgumentException(String.format("Travel allowance can not go over: %s", travel_allowance_upper_limit));
        }
    }

    public double hra(){
        double hra1 = 0;
        double hra2 = 0;
        /*Here it is assumed that HRA payed by company is also according to metro_city_residence flag, henace hra1*/
        if (this.rent_paid_by_emp <= 0){
            return 0;
        }
        if (this.metro_city_residence){
            hra1 = 0.5 * this.base_salary;
        }
        else{
            hra1 = 0.4 * this.base_salary;
        }
        hra2 = this.rent_paid_by_emp - (0.1 * this.base_salary);
        if (hra2 <= 0){
            return 0;
        }
        this.housing_rent_allowance = min(hra1, hra2);
        System.out.println("Employee ID: "+this.eid+" and employee name: "+this.ename+" has hra can be claimed: "+this.housing_rent_allowance+" which is minimum of: "+hra1+" and "+hra2);
        return min(hra1, hra2);
    }

    public double dA(){
        /* Inflation or bonus given is 5% min for private employee and remains contant for a grade */
        System.out.println("Employee ID: "+this.eid+" and employee name: "+this.ename+" has DA assigned: "+(this.base_salary * 0.05));
        return this.base_salary * 0.05;
    }

    public double getTravelAllowanceLimitleft(){
        double value_returned = travel_allowance_upper_limit - this.travel_allowance_allocated_so_far;
        System.out.println(String.format("Travel allowance left for this financial year: %s", (value_returned)));
        return value_returned;
    }

    public double ctc(){
        double cost_to_company = (double)(this.base_salary + this.dA());
        System.out.println("Employee: "+this.ename+" with employee code; "+this.eid+" has ctc: "+ cost_to_company);
        return cost_to_company;
    }

    public static void main(String[] args) {
        Emp emp1 = new Emp(1234567890, "Devarshi Singh",(short)29, 1000000.0, 150000.0, false, 10000.0);
        emp1.ctc();
        emp1.getTravelAllowanceLimitleft();
    }
}
