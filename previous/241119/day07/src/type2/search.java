package type2;

public class search {
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
        int num =1234;
        System.out.println(search_2(arr,num));
    }
    public static int search_2(int[] arr,int num){
        int minCount=0;
        int maxCount=arr.length-1;
        while(minCount<=maxCount) {
            int mid=(minCount+maxCount)/2;
            if (num < arr[mid]) {
                maxCount = mid - 1;
            } else if (num > arr[mid]) {
                minCount = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
