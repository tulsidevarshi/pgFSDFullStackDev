package VirtualKeyRepo;
import VirtualKeyRepo.Sorting;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.StringJoiner;

class UserRelated{
    public Boolean createUser(String pathTillUsersDirectory, String userName) throws ExceptionRaised{
        try{
            FilesOperations fOps = new FilesOperations();
            pathTillUsersDirectory = fOps.getAbsoluteFilePath(pathTillUsersDirectory, userName);
            File f = new File(pathTillUsersDirectory);
            // if the directory already exists
            if (f.exists()) {
                System.out.println("The Directory already exists.");
                return true;
            }
            else {
                // creating the directory
                boolean val = f.mkdir();
                if (val){
                    System.out.println(pathTillUsersDirectory + " created successfully.");
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        catch(Exception ex){
            try{
                throw new ExceptionRaised("Issue while creating User.");
            }
            catch(Exception e){
                System.out.println(" "+e+ ". "+ ex+"");
                return false;
            }
        }
    }
}

class FilesOperations{

    protected static ArrayList<FolderFileDS> folderName = new ArrayList<FolderFileDS>();
    protected static ArrayList<FolderFileDS> fileName = new ArrayList<FolderFileDS>();
    protected static ArrayList<FolderFileDS> userFolder = new ArrayList<FolderFileDS>();
    protected static Dictionary<String, ArrayList<String>> userFileDict = new Hashtable<String, ArrayList<String>>();

    private static void addToDict(String user, String fileName){
        ArrayList<String> temp;
        if( (userFileDict.get(user) == null) || (userFileDict.get(user).size() == 0) ){
            temp = new ArrayList<String>();
            temp.add(fileName);
            userFileDict.put(user, temp);
        }
        else{
            temp = userFileDict.get(user);
            temp.add(fileName);
            userFileDict.put(user, temp);
        }
    }

    public void printSortedFileName(String inputpathUsersString){
        fetchAllFilesFolders(inputpathUsersString);
        Sorting.sortValues(fileName, 0, fileName.size());
        System.out.println("Sorted Files:");
        for (FolderFileDS file1 : fileName) {
            System.out.println("file name: "+file1.name+". User: "+file1.parentNameOrUserControlling);
        }
    }

    // private static void deleteFromDict(String user, String fileName){
    //     if( (userFileDict.get(user) == null) || (userFileDict.get(user).size() == 0) ){
    //         System.out.println("No Files found for the user");
    //     }
    //     ArrayList<String> temp = userFileDict.get(user);
    //     for(String t: temp){
    //         if(t.equals(fileName)){
    //             temp.remove(fileName);
    //         }
    //     }
    //     if(temp.size() == 0){
    //         userFileDict.remove(user);
    //     }
    //     else{
    //         userFileDict.put(user, temp);
    //     }
    // }
    
    private static void RecursiveFolderContents(File[] arr, int index, int level, String parentName)
    {
        // terminate condition
        if (index >= arr.length)
            return;

        FolderFileDS tempContent = new FolderFileDS();
        tempContent.level = level;
        tempContent.parentNameOrUserControlling = parentName;
        String userFolder;
        if (level == 1){
            userFolder = arr[index].getName();
        }
        else{
            userFolder = parentName;
        }

        
  
        // for files
        if (arr[index].isFile()){
            tempContent.isFolder = false;
            tempContent.name = arr[index].getName();
            addToDict(parentName, tempContent.name);
        }
 
        // for sub-directories
        else if (arr[index].isDirectory()) {
            tempContent.isFolder = true;
            tempContent.name = arr[index].getName();
            // recursion for sub-directories
            RecursiveFolderContents(arr[index].listFiles(), 0, level + 1, userFolder);
        }
 
        // recursion for main directory
        RecursiveFolderContents(arr, ++index, level, userFolder);
    }

    public void fetchAllFilesFolders(String mainDirPath){

        // File object
        File mainDir = new File(mainDirPath);
 
        if (mainDir.exists() && mainDir.isDirectory()) {
             
            // array for files and sub-directories
            // of directory pointed by mainDir
            File arr[] = mainDir.listFiles();
 
            // Calling recursive method
            RecursiveFolderContents(arr, 0, 0, "Users");
        }
    }

    private static String joinPath(String[] args){
        final boolean WINDOWS = System.getProperty("os.name").startsWith("Windows");
        String joiner = "";
        joiner = (WINDOWS) ? ("\\") : ("/") ;
        return String.join(joiner, args);
    }
    // use this function when you want to call a user entered root directory
    public String getAbsoluteFilePath(String path, String fileNameOrFoldeName){
        return joinPath(new String[] {path, fileNameOrFoldeName});
    }// Use this universally

    // use this function when you want to call a user entered root directory
    public String getAbsoluteFilePath(String path, String fileNameOrFoldeName, String fileNameOrFoldeName2){
        return joinPath(new String[] {path, fileNameOrFoldeName, fileNameOrFoldeName2});
    }// Use this universally

    public boolean isPathExist(String path){
        File mainDir = new File(path);
        return mainDir.exists();
    }

    private void appendStrToFile(String filePathStr, String str, boolean appendToFile){
        try{
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(filePathStr, appendToFile));
            bWriter.write(str);
            bWriter.close();
        }
        catch(Exception e){
            System.out.println("Exception: "+e);
        }
    }// writeFile

    private String readFileToString(String filePathStr){
        String myString = String.valueOf("");
        try {
            BufferedReader in = new BufferedReader(new FileReader(filePathStr));
            String tempStr;
            while ((tempStr = in.readLine()) != null) {
                myString = myString + tempStr + "\n";
            }
            in.close();
            return myString;
        }
        catch(Exception e){
            System.out.println("Exception: "+e);
            return "";
        }
    }//readFileToString

    protected Boolean createFile(String pathToFile, String dataToBeWritten, Boolean appendData){
        int Case = 1 ;
        File f = new File(pathToFile);
        if(appendData){
            Case = 2;
        } else{
            Case = 1;
        }
        Boolean returnFlags = false;
        FilesOperations fOps = new FilesOperations();
        switch(Case){
            case 1:
            fOps.appendStrToFile(pathToFile, dataToBeWritten, false);
            if (f.exists()){
                System.out.println("File creation successful");
            }
            else{
                return returnFlags;
            }
            // Validate file data, read the file
            String fileData = fOps.readFileToString(pathToFile);
            if (fileData.equals(dataToBeWritten+"\n")){
                System.out.println("All data validates");
                returnFlags = true;
            }
            else{
                System.out.println("Data after write file did not matched.");
                returnFlags = false;
            }
            return returnFlags;
            // Non need of break
            case 2:
            if (f.exists()){
                fOps.appendStrToFile(pathToFile, dataToBeWritten, true);
                String dataReturned = fOps.readFileToString(pathToFile);
                if (dataReturned.equals(dataToBeWritten+dataToBeWritten+"\n")){
                    System.out.println("Appended Data found in file");
                    returnFlags = true;
                }
                else{
                    System.out.println("Appended data missing from files");
                    returnFlags = false;
                }
            }
            else{
                System.out.println(String.format("File: %s Does not exsist",pathToFile));
                returnFlags = false;
            }
            return returnFlags;
            // no need of break
            default:
            return returnFlags;
        }//switch case closed
    }// Create Files, append data or write data

    public boolean deleteFile(String path)
    {
        try {
            Files.deleteIfExists(
                Paths.get(path));
        }
        catch (NoSuchFileException e) {
            System.out.println(path+ " No such file/directory exists");
                return false;
        }
        catch (DirectoryNotEmptyException e) {
            System.out.println("Directory is not empty.");
            return false;
        }
        catch (IOException e) {
            System.out.println("Invalid permissions to delete file: "+path);
            return false;
        }
        System.out.println("Deletion successful.");
        return true;
    } // delete file and folders
}//FilesOperations class

