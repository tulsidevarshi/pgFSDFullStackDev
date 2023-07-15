package VirtualKeyRepo;
import VirtualKeyRepo.FilesOperations;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.*;
import java.nio.file.Path;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.io.*;

public class LockedMe {
    public static void main(String[] args) throws IOException, ExceptionRaised{
        new Input().mainChoice();
    }
}

class FolderFileDS{
    boolean isFolder;
    String name;
    Integer level;
    String parentNameOrUserControlling;
}


class ReturnData2String{
    boolean dataCorrect;
    String stringOne;
    String stringTwo;

    public ReturnData2String(){
        this.dataCorrect = false;
        this.stringOne = "";
        this.stringTwo = "";
    }
}

class ReturnData{
    boolean pathIsValid;
    String stringPath;

    public ReturnData(){
        this.pathIsValid = false;
        this.stringPath = "";
    }
}

class Input{
    public static final String REGEX_PATTERN_FILE = "^[A-za-z][A-za-z0-9_]*{1,255}.[txt|png|jpeg|jpg]$";
    public static final String REGEX_PATTERN_FOLDER = "^[A-za-z][A-za-z0-9_\\\\s]*{1,255}$";
    static final boolean WINDOWS = System.getProperty("os.name").startsWith("Windows");
    public static String folderPath;
    protected static final String parentFolder = "Users";
    private static Integer mainMessageInputChecker(int selectedOption, BufferedReader br) throws IOException{
            try{
                selectedOption = Integer.parseInt(br.readLine());
            }
            catch (Exception e){
                System.out.println(String.format("Wrong integer option choosen. Exception: %s",e));
            }
        return selectedOption;
    }// main message input checker

    private static String getAbsoluteFilePathSpecific(String fileNameorFolderName){
        Path withFilePath = Paths.get("");
        Path currentRelativePath = Paths.get("");
        String folderPath = currentRelativePath.toAbsolutePath().toString();
        withFilePath = Paths.get(folderPath, "VirtualKeyRepo", fileNameorFolderName);
        return withFilePath.toString();
    }//One can use this if he or she only knows relative path from project to file Or Folder

    private static boolean validateStringFileNameORFolderNameUsingRegex(String filenameOrFoldername, Boolean isFolder) {
    if (filenameOrFoldername == null) {
        return false;
        }
    if(isFolder)
        return filenameOrFoldername.matches(REGEX_PATTERN_FOLDER);
    else return filenameOrFoldername.matches(REGEX_PATTERN_FILE);
	}

    private static ReturnData validatePath(BufferedReader br, Boolean attachUsers)throws IOException, ExceptionRaised{
        FilesOperations fileOps = new FilesOperations();
        ReturnData returnData = new ReturnData();
        try{
            // Path validity check 1
            String pathString = Paths.get(br.readLine()).toString();
            if( (pathString.contains("\\") && (! WINDOWS) )){
                throw new ExceptionRaised(String.format("Path entered is Invalid. OS Type is : %s. It has \"\\ in path.\"", System.getProperty("os.name")));
            }
            if( (pathString.contains("/") && (WINDOWS) )){
                throw new ExceptionRaised(String.format("Path entered is Invalid. OS type is Windows: %s. It has \"/\" in path.", System.getProperty("os.name")));
            }
            if(attachUsers){
                if( pathString.endsWith("Users") || pathString.endsWith("Users/") || pathString.endsWith("Users\\")){
                //do nothing
                }
                else{
                    if(new UserRelated().createUser(pathString, "Users")){
                        pathString = fileOps.getAbsoluteFilePath(pathString, "Users");
                    }
                    else{
                        throw new InvalidPathException("Invalid Path", "Unable to create Users Directory.");
                    }
                }
            }
            returnData.pathIsValid = true;
            returnData.stringPath = pathString;
        }
        catch (InvalidPathException e){
            System.out.println(e);
        }
        return returnData;
    }// validating path

    protected void mainChoice() throws IOException, ExceptionRaised{
        String messageMain = """
            Please input numbers from below option to select corresponding action:
            1. To enter the path of exsisting parent folder or create parent folder.
            2. To let system select the directory creation path and folder.
            3. Terminate this session.
                """;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int selectedOption = 0;
        do{
            System.out.println("\n"+"*".repeat(100));
            System.out.println(messageMain);
            System.out.println("*".repeat(100));
            System.out.print("\nPlease choose from option above: ");
            selectedOption = mainMessageInputChecker(selectedOption, br);
            String inputPath="";
            switch(selectedOption){
                case 1:
                ReturnData pathInputAndValidation = validatePath(br, false);
                if(pathInputAndValidation.pathIsValid){
                    inputPath = pathInputAndValidation.stringPath;
                }
                UserChoice(br, inputPath);
                break;
                case 2:
                inputPath = getAbsoluteFilePathSpecific("");
                if(new UserRelated().createUser(inputPath, "Users")){
                    UserChoice(br, getAbsoluteFilePathSpecific("Users"));
                } else {
                    System.out.println("Failed to create Users directory!");
                }
                break;
                case 3:
                System.out.println("Thanks for using the application");
                System.exit(0);
                default:
                // do nothing
                continue;
            }
        }while(true);
    }// function close mainChoice

    private static ReturnData inputString(BufferedReader br, String text) throws IOException, ExceptionRaised{
        ReturnData rd = new ReturnData();
        try{
            System.out.print(text+": ");
            rd.stringPath = br.readLine();
            rd.pathIsValid = true;
        }catch (Exception e) {
            System.out.println("Wrong Input for user");
        }
        return rd;
    } // returns 2 attributes, one boolean to check if data can be used and other as string

    private static ReturnData2String inputString(BufferedReader br, String text1, String text2) throws IOException, ExceptionRaised{
        ReturnData2String rd = new ReturnData2String();
        try{
            System.out.print(text1+": ");
            String str1 = br.readLine();
            System.out.print(text2+": ");
            String str2 = br.readLine();
            rd.dataCorrect = true;
            rd.stringOne = str1;
            rd.stringTwo = str2;
        }
        catch(Exception e){
            System.out.println("Wrong Input User");
        }
        return rd;
    } // return 2 string and boolean to check if data can be used

    protected static void UserChoice(BufferedReader br, String inputPathWithUsers) throws IOException, ExceptionRaised{
        
        String messageUserRelated="""
            Please input numbers from below option to select corresponding action:
            1. Create User
            2. Create a File for User
            3. Delete a File for User
            4. Search a User Specified File
            5. Sort all files by Name
            6. Return to main Context
            7. Exit from this Application
            """;
        FilesOperations fOps = new FilesOperations();
        int selectedOption = 0;
        do{
            System.out.println("\n"+"*".repeat(100));
            System.out.println(messageUserRelated);
            System.out.println("*".repeat(100));
            System.out.print("\nPlease choose from option above: ");
            selectedOption = mainMessageInputChecker(selectedOption, br);
            switch(selectedOption){
                case 1:
                ReturnData rd = inputString(br, "Please enter name of user you intend to enter");
                if(! rd.pathIsValid)
                break;
                else{
                    String userInput = rd.stringPath;
                    if (validateStringFileNameORFolderNameUsingRegex(userInput, true)){
                        UserRelated ur = new UserRelated();
                        if (ur.createUser(inputPathWithUsers, userInput)){
                            //inputPathWithUsers = fOps.getAbsoluteFilePath(inputPathWithUsers, userInput);
                            System.out.println(userInput+ " created successfully");
                        }
                        else{
                            System.out.println(userInput+" user Creation failed");
                        }
                    }
                    else{
                        break;
                    }
                }
                break;
                case 2:
                ReturnData2String rd1 = inputString(br, "Enter name of the User you want to create file", 
                "Enter file name you want to create");
                String user = "";
                String fileName = "";
                if(rd1.dataCorrect){
                    user = rd1.stringOne;
                    fileName = rd1.stringTwo;
                } else {
                    break;
                }
                String folderPath = fOps.getAbsoluteFilePath(inputPathWithUsers, user);
                if (fOps.isPathExist(folderPath)){
                    folderPath = fOps.getAbsoluteFilePath(folderPath, fileName);
                    if (fOps.createFile(folderPath, "This is data in file", false)){
                        System.out.println("File created. If exists. Data will be overwritten.");
                    }
                    else{
                        System.out.println("File creation failed.");
                    }
                }
                else{
                    System.out.println(user+" not found. Hence file could not be created.");
                    break;
                }
                break;
                case 3:
                ReturnData2String rd2 = inputString(br, "Enter name of the User you want to create file", 
                "Enter file name you want to create");
                String user2 = "";
                String fileName2 = "";
                if(rd2.dataCorrect){
                    user2 = rd2.stringOne;
                    fileName2 = rd2.stringTwo;
                } else {
                    break;
                }
                String pathFileDeletion = fOps.getAbsoluteFilePath(inputPathWithUsers, user2, fileName2);
                fOps.deleteFile(pathFileDeletion);
                break;
                case 4:
                ReturnData2String rd4 = inputString(br, "Enter name of the user you want to search file for",
                "Enter name of the file you want to search");
                if(rd4.dataCorrect){
                    String fileToBeSearched = fOps.getAbsoluteFilePath(inputPathWithUsers, rd4.stringOne, rd4.stringTwo);
                    if(fOps.isPathExist(fileToBeSearched)){
                        System.out.println("File: "+rd4.stringTwo+" found for the user: "+rd4.stringOne);
                    }
                    else{
                        System.out.println("File: "+rd4.stringTwo+" not found for the user: "+rd4.stringOne);
                    }
                }
                break;
                case 5:
                fOps.printSortedFileName(inputPathWithUsers);
                break;
                case 6:
                return;
                case 7:
                System.out.println("Thanks for using the application");
                System.exit(0);
                default:
                // do nothing
                continue;
            }
        }while(true);
    }
}

