package attackgame;

import java.util.Random;

public class role {
    private String name;
    private int blood;
    private int speed;

    String[] attacks_desc={
            "%s使出了一招【背心钉】，转到对方的身后，一掌向%s背心的灵台穴拍去。",
            "%s使出了一招【游空探爪】，飞起身形自半空中变掌为抓锁向%s。",
            "%s大喝一声，身形下伏，一招【劈雷坠地】，捶向%s双腿。",
            "%s运气于掌，一瞬间掌心变得血红，一式【掌心雷】，推向%s。",
            "%s阴手翻起阳手跟进，一招【没遮拦】，结结实实的捶向%s。",
            "%s上步抢身，招中套招，一招【劈挂连环】，连环攻向%s。"
    };
    String[] injureds_desc={
            "结果%s退了半步，毫发无损",
            "结果给%s造成一处瘀伤",
            "结果一击命中，%s痛得弯下腰",
            "结果%s痛苦地闷哼了一声，显然受了点内伤",
            "结果%s摇摇晃晃，一跤摔倒在地",
            "结果%s脸色一下变得惨白，连退了好几步",
            "结果『轰』的一声，%s口中鲜血狂喷而出",
            "结果%s一声惨叫，像滩软泥般塌了下去"
    };

    public role(String name,int blood,int speed){
        this.name=name;
        this.blood=blood;
        this.speed=speed;
    }
    public role(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public  void speed(role r){
        if(this.speed>r.speed) {
            System.out.println(this.getName()+"先手");
        }else{
            System.out.println(r.getName()+"先手");
        }
    }
    public void attack(role r){
        Random random = new Random();
        int attack = random.nextInt(100)-30;
        if(attack<0){attack=0;}
        int remain= r.getBlood()-attack;
        if(remain<0){
            remain=0;
        }
        r.setBlood(remain);
        System.out.printf(attacks_desc[random.nextInt(6)],this.getName(),r.getName());
        if(attack==0){
            System.out.printf(injureds_desc[0],r.getName());
        }else if(attack>0&&attack<=10){
            System.out.printf(injureds_desc[1],r.getName());
        }else if(attack>10&&attack<=20){
            System.out.printf(injureds_desc[2],r.getName());
        } else if(attack>20&&attack<=30){
            System.out.printf(injureds_desc[3],r.getName());
        } else if(attack>30&&attack<=40){
            System.out.printf(injureds_desc[4],r.getName());
        } else if(attack>40&&attack<=50){
            System.out.printf(injureds_desc[5],r.getName());
        } else if(attack>50&&attack<=60){
            System.out.printf(injureds_desc[6],r.getName());
        } else if(attack>60){
            System.out.printf(injureds_desc[7],r.getName());
        }
        System.out.println("，造成了"+attack+"点伤害，"+r.getName()+"还剩"+remain+"点血量。");
        if(remain==0){
            System.out.println(r.getName()+"受伤过重，已经犹如风中残烛，无力再战了。");
        }
    }
}
