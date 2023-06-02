package SimplilearnProjectSubmitted;
import java.io.*;
import SimplilearnProjectSubmitted.*;
import UserDefinedException.*;


public class ArithematicCalculator {
    public static void main(String [] args) throws IOException{
        // Dispatcher
        new Input().takeInput();
    }
}

class RegisterStorage{
    public String firstNumber;
    public String secondNumber;
    public String result;
    public String operationPerformed;
}

class Print{
    String str;
    String colorCode;
    protected static void print( String str, String colorCode){
        System.out.println(colorCode+str+UnixColorCode.ANSI_RESET);
    }
}

class Input{

    private boolean validateInput(String input){
        try{
            Double.parseDouble(input);
            return true;
        }
        catch(Exception e){
            Print.print( "Please Choose the correct action from below",UnixColorCode.ANSI_RED);
            return false;
        }
    }

    private static final String messageString = """
        1 -> Addition of two numbers
        2 -> Subtraction of two numbers
        3 -> Multiplication of two numbers
        4 -> Division of two numbers
        5 -> Get last ten operations summary
        6 -> Exit
        """;

    protected void takeInput() throws IOException{
        System.out.println();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            Print.print( "*".repeat(100), UnixColorCode.ANSI_PURPLE);
            Print.print( "\n\n"+messageString+"\n", UnixColorCode.ANSI_GREEN);
            System.out.print("Please choose any number from above to perform corresponding action:  ");
            Integer choice;
            try{
                choice = Integer.parseInt(br.readLine());
            }
            catch(Exception e){
                Print.print( "Please Choose the correct action from below",UnixColorCode.ANSI_RED);
                continue;
            }
            CalcOperation calOp;
            if (choice >=1  && choice<=4){
                System.out.println();
                System.out.print("Please input your first number to be kept at left of this binary operation: ");
                String number1 = br.readLine();
                System.out.println();
                System.out.print("Please input your second number to be kept at right of this binary operation: ");
                String number2 = br.readLine();
                System.out.println();
                if (!(validateInput(number2)) || (!(validateInput(number1)))){
                    continue;
                }
                Double num1 = Double.parseDouble(number1);
                Double num2 = Double.parseDouble(number2);
                calOp = new CalcOperation(num1, num2);    
            }
            else{
                calOp = new CalcOperation();
            }
            switch(choice){
                case 1: calOp.add();
                break;
                case 2: calOp.subtract();
                break;
                case 3: calOp.multiply();
                break;
                case 4: calOp.divide();
                break;
                case 5: calOp.revealLastTenOperations();
                break;
                case 6: Print.print( "\n\nThank You for Utilizing Me\n\n", UnixColorCode.ANSI_BLUE);
                return;
                default:
                Print.print( "Please choose from options carefully", UnixColorCode.ANSI_CYAN);
                break;
            } 
        } // while loop
    } // takeInput function
} // calss closing


class CalcOperation{
    private static FixedSizeStack<RegisterStorage> memoryRegister = new FixedSizeStack<RegisterStorage>(10);
    private static Double firstNum;
    private static Double secondNum;
    private RegisterStorage regStorage;

    CalcOperation(){}

    CalcOperation(Double num1, Double num2){
        firstNum = num1;
        secondNum = num2;
        regStorage = new RegisterStorage();
        regStorage.firstNumber = String.valueOf(num1);
        regStorage.secondNumber = String.valueOf(num2);
        regStorage.result = String.valueOf("ERROR");
        regStorage.operationPerformed = String.valueOf("No Operation Performed");
    }

    protected void add(){
        Double finalResult = firstNum + secondNum;
        addToRegister(String.valueOf(finalResult), "Addition");
        Print.print( "Addition of two number: "+ String.valueOf(finalResult), UnixColorCode.ANSI_GREEN);
    }

    protected void subtract(){
        Double finalResult = firstNum - secondNum;
        addToRegister(String.valueOf(finalResult), "Subtraction");
        Print.print( "Difference of two numbers: "+String.valueOf(finalResult), UnixColorCode.ANSI_GREEN);
    }

    protected void multiply(){
        Double finalResult = firstNum * secondNum;
        addToRegister(String.valueOf(finalResult), "Multiplication");
        Print.print( "Multiplication of two numbers: "+String.valueOf(finalResult), UnixColorCode.ANSI_GREEN);
    }

    protected void divide(){
        Double finalResult;
        try{
            UserDefinedExceptions.validateDenominator(secondNum);
            finalResult = firstNum/secondNum;
        }
        catch(Exception ae){
            Print.print( String.format("Exception: %s", ae), UnixColorCode.ANSI_RED);
            return;
        }
        addToRegister(String.valueOf(finalResult), "Division");
        Print.print( "Divide two number of two numbers: "+String.valueOf(finalResult), UnixColorCode.ANSI_GREEN);
    }

    private void addToRegister(String finalResult, String operationPerformed){
        this.regStorage.result = finalResult;
        this.regStorage.operationPerformed = operationPerformed;
        memoryRegister.push(this.regStorage);
    }

    protected void revealLastTenOperations(){
        for(RegisterStorage temp: memoryRegister){
            String printable = "Operation: "+temp.operationPerformed+" Result: "+temp.operationPerformed+" on number 1: "+temp.firstNumber+" and number 2:"+temp.secondNumber;
            if ( (temp.result == "ERROR") || (temp.operationPerformed == "No Operation Performed") ){
                Print.print( printable, UnixColorCode.ANSI_RED);
            }
            else{
                Print.print( printable, UnixColorCode.ANSI_GREEN);
            }
        }
    }
}// class calcOperation

