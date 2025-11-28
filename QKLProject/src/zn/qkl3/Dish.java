package zn.qkl3;

public abstract class Dish {
    public String name;//名字

    public abstract void prepare();//备菜

    public void cook() {//烹调
        System.out.println("正在烹调" + name);
    }

    public void plate() {//装盘
        System.out.println("正在将" + name + "装盘");
    }

    public void serve() {//上菜
        System.out.println("将" + name + "上菜");
    }
}
