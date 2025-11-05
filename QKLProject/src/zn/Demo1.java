package zn;

public class Demo1 {
    public static void main(String[] args) {
        int sum =0;
        for (int i =0;i<10;i++){
            if(i==5){
//                break;
                continue;
            }
            sum +=i;
        }
        System.out.println(sum);
    }
}
