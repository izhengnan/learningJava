package zn.util;

public class ArrayUtil {
    public static void printArray(int[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length ; i++) {
            System.out.print(a[i]);
            if (i != a.length-1){
                System.out.print(",");
            }
        }
        System.out.println("]");
    }
    public static void bubbleSort(int[] a) {
        //外层控制排序轮数
        for (int i = 0; i < a.length-1 ; i++) {
            //内层控制相邻元素比对和交换
            for (int j = 0; j < a.length-1-i ; j++) {
                //如果前一个元素大于后一个元素，则交换位置
                if (a[j] > a[j+1]){
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
    }
}
