package UserDefinedException;

public class UserDefinedExceptions {
    public static void validateDenominator(Double value) throws DivideByZero{
        if(value == 0){
            throw new DivideByZero("Denominator can not be zero.");
        }
    }
}

class DivideByZero extends Exception{
    DivideByZero(String msg){
        super(msg);
    }
}
