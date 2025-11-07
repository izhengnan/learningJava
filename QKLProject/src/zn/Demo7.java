package zn;
import zn.util.ArrayUtil;

public class Demo7 {
    public static void main(String[] args) {
        int[] a = {10,11,12,5,6,3,4,7,8,9,13,14,1,2};
        //1、输出排序前的数组
        ArrayUtil.printArray(a);
        //2、进行冒泡排序
        ArrayUtil.bubbleSort(a);
        //3、输出排序后的数组
        ArrayUtil.printArray(a);
    }


}
