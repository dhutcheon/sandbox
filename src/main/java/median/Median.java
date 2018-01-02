package median;

import util.SortUtils;


public class Median {



    public static Float median(int[] arr1, int[] arr2) {
//        print("Arr1", arr1);
//        print("Arr2", arr2);

        int[] arr = SortUtils.mergeSortOrderedArrays(arr1, arr2);
        Float median;

        //Get the length of the array
        int length = arr.length;

        //Divide length by 2 to find the mid point index
        int mid = (length / 2);

        //Is the length even? (index % 2) = 0
        if (length % 2 == 0) {
            //median = (array[mid] + array[mid+1]) / 2
            median = (arr[mid-1] + arr[mid]) / 2F;
        } else { //Is the length odd (index % 2) = 1
            //median = array[index]
            median = Float.valueOf(arr[mid]);
        }

        return median;
    }




}
