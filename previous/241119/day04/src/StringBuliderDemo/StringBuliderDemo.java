package StringBuliderDemo;

public class StringBuliderDemo {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        StringBuilder sb = new StringBuilder();
//        String s = sc.next();
//        sb.append(s);
//        sb.reverse();
//        String s_1 = sb.toString();
//        if(s.equals(s_1)){
//            System.out.println("是对称字符串");
//        }else{
//            System.out.println("不是对称字符串");
//        }
        int[] arr={1,2,3,4,5,6};
        String s = ArrToString(arr);
        System.out.println(s);

    }
    public static String ArrToString(int[] arr){
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for(int i=0;i<arr.length;i++){
            if(i!=arr.length-1){
                sb.append(arr[i]);
                sb.append(",");
            }else{
                sb.append(arr[i]);
            }
        }
        sb.append("]");
        String s = sb.toString();

        return s;
    }
}
