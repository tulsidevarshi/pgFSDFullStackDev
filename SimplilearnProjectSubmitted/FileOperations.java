package SimplilearnProjectSubmitted;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOperations {
    public static void main(String[] args) {
        String fileName = "fileOp.txt";
        FilesOperations fOps = new FilesOperations();
        String pathToFile = fOps.getAbsoluteFilePath(fileName);
        // case 1 : write in a file
        String strData = "My name is Devarshi Singh.\nThis is files.";
        fOps.appendStrToFile(pathToFile, strData, false);
        File f = new File(pathToFile);
        if (f.exists()){
            System.out.println("File creation successful");
        }
        else{
            return;
        }
        // Validate file data, read the file
        String fileData = fOps.readFileToString(pathToFile);
        if (fileData.equals(strData+"\n")){
            System.out.println("All data validates");
        }
        else{
            System.out.println("Data after write file did not matched.");
        }
        // Append to file data
        String dataToBeAppended = "\nAppended Data";
        fOps.appendStrToFile(pathToFile, dataToBeAppended, true);

        String dataReturned = fOps.readFileToString(pathToFile);
        if (dataReturned.equals(strData+dataToBeAppended+"\n")){
            System.out.println("Appended Data found in file");
        }
        else{
            System.out.println("Appended data missing from files");
        }
    }
}

class FilesOperations{

    protected String getAbsoluteFilePath(String fileName){
        Path withFilePath = Paths.get("");
        Path currentRelativePath = Paths.get("");
        String folderPath = currentRelativePath.toAbsolutePath().toString();
        withFilePath = Paths.get(folderPath, "SimplilearnProjectSubmitted", fileName);
        return withFilePath.toString();
    }

    protected void appendStrToFile(String filePathStr, String str, boolean appendToFile){
        try{
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(filePathStr, appendToFile));
            bWriter.write(str);
            bWriter.close();
        }
        catch(Exception e){
            System.out.println("Exception: "+e);
        }
    }// writeFile

    protected String readFileToString(String filePathStr){
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
}//FilesOperations class
