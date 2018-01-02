package util;

public class SortUtils {
    public static int[] mergeSortOrderedArrays(int[] arr1, int[] arr2) {
        //get length of result array arr1+arr2
        int length = arr1.length + arr2.length;

        //create result array that is length of arr1 and arr2
        int[] result = new int[length];

        //set pointer for arr1, arr2 and result to 0
        int p1=0,p2=0,pr=0;

        //create a loop for 0 < resultLength
        while (pr<length) {
            //get val1 for pointer of arr1
            Integer val1 = (p1 < arr1.length) ? arr1[p1] : null;

            //get val2 for pointer of arr2
            Integer val2 = (p2 < arr2.length) ? arr2[p2] : null;

            //if end of arr1 add arr2
            if (val1 == null) {
                result[pr] = val2;
                p2++;
                pr++;
                //if end of arr2 add arr1
            } else if (val2 == null) {
                result[pr] = val1;
                p1++;
                pr++;
            } else if (val1 < val2) { //if val1 < val2
                //add val1 to result
                result[pr] = val1;
                //increment val1 pointer
                p1++;
                //increment result pointer
                pr++;
            } else if (val1 > val2) { //if val1 > val2
                //add val2 to result
                result[pr] = val2;
                //increment val2 pointer
                p2++;
                //increment result pointer
                pr++;
            } else { //if val1 == val2
                //add val1 to result
                result[pr] = val1;
                //increment val1 pointer
                p1++;
                //increment result pointer
                pr++;
                //add val2 to result
                result[pr] = val2;
                //increment val2 pointer
                p2++;
                //increment result pointer
                pr++;
            }
        }

//        print("Sorted array", result);

        return result;
    }
}
