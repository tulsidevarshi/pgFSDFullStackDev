package SimplilearnProjectSubmitted;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*System.out.println("Hello World!");*/
        System.out.println("\n**************************************\n");
        System.out.println("\tWelcome to TheDesk \n");
        System.out.println("**************************************");
        optionsSelection();

    }
    private static void optionsSelection() {
        String[] arr = {"1. I wish to review my expenditure",
                "2. I wish to add my expenditure",
                "3. I wish to delete my expenditure",
                "4. I wish to sort the expenditures",
                "5. I wish to search for a particular expenditure",
                "6. Close the application"
        };
        int[] arr1 = {1,2,3,4,5,6};
        int  slen = arr1.length;
        for(int i=0; i<slen;i++){
            System.out.println(arr[i]);
            // display the all the Strings mentioned in the String array
        }
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        ArrayList<Integer> expenses = new ArrayList<Integer>();
        expenses.add(1000);
        expenses.add(2300);
        expenses.add(45000);
        expenses.add(32000);
        expenses.add(110);
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("\nEnter your choice:\t");
            int  options =  sc.nextInt();
                switch (options){
                    case 1:
                        System.out.println("Your saved expenses are listed below: \n");
                        System.out.println(expenses+"\n");
                        //optionsSelection();
                        break;
                    case 2:
                        System.out.println("Enter the value to add your Expense: \n");
                        int value = sc.nextInt();
                        expenses.add(value);
                        System.out.println("Your value is updated\n");
                        expenses.addAll(arrayList);
                        System.out.println(expenses+"\n");
                        //optionsSelection();
                        break;
                    case 3:
                        System.out.println("You are about the delete all your expenses! \nConfirm again by selecting the same option...\n");
                        int con_choice = sc.nextInt();
                        if(con_choice==options){
                               expenses.clear();
                            System.out.println(expenses+"\n");
                            System.out.println("All your expenses are erased!\n");
                        } else {
                            System.out.println("Oops... try again!");
                        }
                        //optionsSelection();
                        break;
                    case 4:
                    sortExpenses(expenses, 0, expenses.size()-1);
                        //optionsSelection();
                        break;
                    case 5:
                        searchExpenses(expenses, sc);
                        //optionsSelection();
                        break;
                    case 6:
                        closeApp();
                        return;
                    default:
                        System.out.println("You have made an invalid choice!");
                        break;
                }//switch
            }//while loop
        }//options selection
    private static void closeApp() {
        System.out.println("Closing your application... \nThank you!");
        return;
            }
    private static void searchExpenses(ArrayList<Integer> arrayList, Scanner sc) {
        int leng = arrayList.size();
        System.out.println("Enter the expense you need to search:\t");
        Integer value = Integer.parseInt(sc.next());
        //Complete the method
        // Will use linear search here as sorting will take at most nlogn
        for(int i=0; i< leng; i++){
            Integer arrayValue = arrayList.get(i);
            if(  Integer.compare(arrayValue, value) == 0){
                System.out.println("Value found at index: "+String.valueOf(i));
                return;
            }
        }
        System.out.println("Value: "+String.valueOf(value)+" not found");
    }
    private static void sortExpenses(ArrayList<Integer> arrayList, int low, int high) {
        //int arrlength =  arrayList.size();
       //Complete the method. The expenses should be sorted in ascending order. -> Using quick sort. It's industry standard
       if(Integer.compare(low, high) < 0){
        int pivot = partition(arrayList, low, high);
        sortExpenses(arrayList, low, pivot -1);
        sortExpenses(arrayList, pivot+1, high);
       }
    }
    private static int partition(ArrayList<Integer> arrayList, int low, int high){
        Integer pivot = arrayList.get(high);
        int pointer = low;
        for(int i = low; i< high; i++){
            Integer tempVal = arrayList.get(i);
            if (Integer.compare(tempVal, pivot) <= 0){
                arrayList = swap(arrayList, pointer, i);
            }
        }
        arrayList = swap(arrayList, pointer, high);
        return pointer;
    }
    private static ArrayList<Integer> swap(ArrayList<Integer> arrayList, int i, int j){
        Integer temp = arrayList.get(i);
        arrayList.set(i, arrayList.get(j));
        arrayList.set(j , temp);
        return arrayList;
    }
}

