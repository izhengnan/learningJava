package type1;

import java.util.ArrayList;

public class type1 {
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        int size=list.size();
        for(int i=0;i<size;i++){
            System.out.print(list.get(i));
        }
        System.out.println("\n"+list);
    }
}
