package SimplilearnProjectSubmitted;
import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.nio.file.Path;
import java.nio.file.Paths;

public class EnglishEmailIdAuthenticator {
    public static void main(String[] args) throws IOException{
        ArrayList<String> emailArrayList = takeInput();
        if (emailArrayList.size() <= 0){
            Print.print("No Emails to validate", UnixColorCode.ANSI_RED);
            return;
        }
        EmailAuthenticator.authenticateEmailsArray(emailArrayList);
    }

    public static ArrayList<String> takeInput() throws IOException{
        Path withFilePath = Paths.get("");
        Path currentRelativePath = Paths.get("");
        String folderPath = currentRelativePath.toAbsolutePath().toString();
        withFilePath = Paths.get(folderPath, "SimplilearnProjectSubmitted", "emailsLineSeperated.txt");
        Print.print("Current absolute path is: " + withFilePath, UnixColorCode.ANSI_PURPLE);
        
        ArrayList<String> emailArrayList = new ArrayList<String>();

        File f = new File(withFilePath.toString());
        if (! (f.exists()) ){
            Print.print(withFilePath+" does not exists.", UnixColorCode.ANSI_RED);
            return emailArrayList;
        }
        String tempEmail;
        BufferedReader br = new BufferedReader(new FileReader(f));
        while( (tempEmail=br.readLine()) != null){
            if (tempEmail.strip().length() > 0){
                emailArrayList.add(String.valueOf(tempEmail));
            }
        }
        br.close();
        return emailArrayList;
    }
 
}

class Print{
    String str;
    String colorCode;
    protected static void print( String str, String colorCode){
        System.out.println(colorCode+str+UnixColorCode.ANSI_RESET);
    }
}

class EmailAuthenticator{

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    protected static boolean emailValidation(String emailStr){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    protected static void authenticateEmailsArray(ArrayList<String> emailList){
        ArrayList<String> validationFailed = new ArrayList<String>();
        ArrayList<String> validationPassed = new ArrayList<String>();

        for(String tempStr : emailList){
            boolean emailVerifiedBool = emailValidation(tempStr);
            if (emailVerifiedBool){
                validationPassed.add(tempStr);
            }
            else{
                validationFailed.add(tempStr);
            }
        }

        Print.print("*".repeat(100)+"\n", UnixColorCode.ANSI_PURPLE);
        Print.print("Email Passed Validation: ", UnixColorCode.ANSI_CYAN);
        int i = 1;
        for(String passEmail: validationPassed){
            Print.print("Email "+String.valueOf(i)+" : "+passEmail, UnixColorCode.ANSI_BLUE);
            i += 1;
        }

        Print.print("*".repeat(100)+"\n", UnixColorCode.ANSI_PURPLE);
        Print.print("Email Failed Validation: ", UnixColorCode.ANSI_CYAN);
        int j = 1;
        for(String invalidEmail: validationFailed){
            Print.print("Email "+String.valueOf(j)+" : "+invalidEmail, UnixColorCode.ANSI_BLUE);
            j += 1;
        }

        Print.print("*".repeat(100)+"\n", UnixColorCode.ANSI_PURPLE);

    }
}
