package SimplilearnProjectSubmitted;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LISDP {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Length of array you are going to pass: ");
        Integer n = Integer.parseInt(br.readLine());
        System.out.println();
        System.out.print("Enter single space seperated array: ");
        String listOfNumbers = br.readLine();
        br.close();
        System.out.println();
        String arr[] = listOfNumbers.strip().split(" ");
        Double arrlist[] = new Double[n];
        for(int i=0; i<n; i++){
            arrlist[i] = Double.parseDouble(arr[i]);
        }
        LIS lis = new LIS();
        Integer max = lis.lis(arrlist, n);
        System.out.println("MAX LIS: "+String.valueOf(max));
    }
}

class LIS{
    protected Integer lis(Double arr[], Integer n)
    {
        Integer lis[] = new Integer[n];
        Integer i, j, max= 0;
 
        // Initialize LIS values to 1
        for (i = 0; i < n; i++)
            lis[i] = 1;
 
        // Here we do dynamic programming.
        // if arr[i] < arr[j] the values wil not be set
        for (i = 1; i < n; i++)
            for (j = 0; j < i; j++)
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;
 
        // Pick maximum of all LIS values
        for (i = 0; i < n; i++)
            if (max < lis[i])
                max = lis[i];
 
        return max;
    }
}

