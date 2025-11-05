package attackgame;

import java.util.Random;

public class game {
    public static void main(String[] args) {
        Random r =new Random();
        role r1 = new role("乔峰",100,r.nextInt(10)+10);
        role r2 = new role("鸠摩智",100,r.nextInt(10)+10);
        if(r1.getSpeed()>r2.getSpeed()){
            attack(r1,r2);
        }else{
            attack(r2,r1);
        }
    }

    public static void attack(role r1,role r2){
        while(true){
            //增加一个等待一秒
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r1.attack(r2);
            if(r2.getBlood()<=0){
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r2.attack(r1);
            if(r1.getBlood()<=0){
                break;
            }
        }
    }
}
