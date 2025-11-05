package zn;

public class Demo2 {
    public static void main(String[] args) {
        int[] list=new int[]{1,9,7,4,3,8,5};
        for (int i =0;i<list.length;i++){
            for (int j =0;j<list.length-1-i;j++){
                if(list[j]<list[j+1]){
                    int tmp = list[j];
                    list[j] = list[j+1];
                    list[j+1] = tmp;
                }
            }
        }
        System.out.println("冒泡排序：");
        for (int i =0;i<list.length;i++){
            System.out.print(list[i]+"\t");
        }
        System.out.println();
        //max
        int i =0;
        int max = list[0];
        while(list[i]<list[i+1]){
            max = list[i+1];
            i++;
        }
        System.out.println("max:"+max);
        //min
        int min = list[0];
        while(list[i+1]<list[i]){
            min = list[i+1];
            i++;
            if(i==list.length-1){
                break;
            }
        }
        System.out.println("min:"+min);
        //sum、avg
        float sum = 0;
        float avg = 0;
        for (int j =0;j<list.length;j++){
            sum += list[j];
        }
        avg = sum/list.length;
        System.out.println("sum:"+sum);
        System.out.println("avg:"+avg);

    }
}


