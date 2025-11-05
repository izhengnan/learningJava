package type2;

import java.util.Arrays;

public class main {
    public static void main(String[] args)
    {
        girlFriends[] array = new girlFriends[4];
        girlFriends gf1 = new girlFriends("小红", 19, 155.9);
        girlFriends gf2 = new girlFriends("小明", 18, 170.7);
        girlFriends gf3 = new girlFriends("小刚", 20, 188.1);
        girlFriends gf4 = new girlFriends("小辉", 20, 168.2);
        array[0] = gf1;
        array[1] = gf2;
        array[2] = gf3;
        array[3] = gf4;

//        Arrays.sort(array,(a,b)->a.getAge()-b.getAge());
        Arrays.sort(array,(a, b)->{
            if(a.getAge()!=b.getAge()) {
                return a.getAge() - b.getAge();
            }else if(a.getHeight()!=b.getHeight()){
                if(a.getHeight()-b.getHeight()>0) {
                    return 1;
                }else if (a.getHeight()-b.getHeight()<0){
                    return -1;
                }else{
                    return 0;
                }
            }else{
                return a.getName().compareTo(b.getName());
            }
        });
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i].getAge()+" "+array[i].getName()+" "+array[i].getHeight());
        }
    }
}
