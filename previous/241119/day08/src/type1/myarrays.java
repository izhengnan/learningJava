package type1;

import java.util.Arrays;

public class myarrays{
    public static void main(String[] args) {
        Integer[] arr = {20,35,77,52,26,71,65,76,24,31,57,59,73,96,5,12};
        Arrays.sort(arr, (a,b)->b-a);
        System.out.println(Arrays.toString(arr));
    }
}
