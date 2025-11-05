package type3;

public class paixu {
    public static void main(String[] args) {
        int[] arr ={20,35,77,52,26,71,65,76,24,31,57,59,73,96,5,12};

        //生成一个长度为100的arr数组，并填入随机数
//        int[] arr = new int[100000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int)(Math.random()*100000000);
//        }
        long a=System.currentTimeMillis();

        QuickSort(arr,0,arr.length-1);
//        bubbleSort(arr);
        long b=System.currentTimeMillis();
        System.out.println(b-a);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
    public static void bubbleSort(int[] arr){
        int temp=0;
        for (int i = 0; i < arr.length; i++) {
            for(int j = 0;j< arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }
    public static void chooseSort(int[] arr){
        for(int j = 0;j<arr.length-1;j++){
            for (int i = j+1; i < arr.length; i++) {
                if (arr[j] > arr[i]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }
    public static void insertSort(int[] arr){
        int startIndex=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]>arr[i+1]){
                startIndex=i+1;
                break;
            }
        }
        for(int i=startIndex;i<arr.length;i++){
            int j = i;
            while(j>0&&arr[j]<arr[j-1]){
                int temp=arr[j];
                arr[j]=arr[j-1];
                arr[j-1]=temp;
                j--;
            }
        }
    }
    public static void QuickSort(int[] arr,int i, int j){
        int low =i;
        int high=j;

        if(low>high){
            return;
        }
        int baseNum=arr[i];
        while(low!=high){
            while(true) {
                if(low>=high||arr[high]<baseNum){
                    break;
                }
                high--;
            }
            while(true) {
                if(low>=high||arr[low]>baseNum){
                    break;
                }
                low++;
            }
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
        }
        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;
        QuickSort(arr,i,low-1);
        QuickSort(arr,low+1,j);
    }
}
