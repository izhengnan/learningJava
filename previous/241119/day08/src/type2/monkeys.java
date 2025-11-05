package type2;

public class monkeys {
    public static void main(String[] args) {
        int[] array=new int[10];
        array[9]=1;
        for (int i=8;i>=0;i--){
            array[i] = (array[i+1]+1)*2;
        }
        System.out.println(array[0]);
    }
}
