package VirtualKeyRepo;

import java.util.ArrayList;
/*
class FolderFileDS{
    boolean isFolder;
    String name;
    Integer level;
    String parentNameOrUserControlling;
}
 */
public class Sorting {
    
    public static void sortValues(ArrayList<FolderFileDS> arrayList, int low, int high) {
        //int arrlength =  arrayList.size();
       //Complete the method. The expenses should be sorted in ascending order. -> Using quick sort. It's industry standard
       if(Integer.compare(low, high) < 0){
        int pivot = partition(arrayList, low, high);
        sortValues(arrayList, low, pivot -1);
        sortValues(arrayList, pivot+1, high);
       }
    }
    private static int partition(ArrayList<FolderFileDS> arrayList, int low, int high){
        FolderFileDS pivot = arrayList.get(high);
        int pointer = low;
        for(int i = low; i< high; i++){
            FolderFileDS tempVal = arrayList.get(i);
            //
            if ((tempVal.name).compareTo(pivot.name) <= 0){
                arrayList = swap(arrayList, pointer, i);
            }
        }
        arrayList = swap(arrayList, pointer, high);
        return pointer;
    }
    private static ArrayList<FolderFileDS> swap(ArrayList<FolderFileDS> arrayList, int i, int j){
        FolderFileDS temp = arrayList.get(i);
        arrayList.set(i, arrayList.get(j));
        arrayList.set(j , temp);
        return arrayList;
    }
}
