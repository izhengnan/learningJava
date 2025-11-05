package shop;

import java.util.Scanner;

public class ShopMain {
    public static void main(String[] args) {
        shop shops[]=new shop[3];
        for(int j=0;j<3;j++) {
            shops[j] = new shop();
            Scanner sc =new Scanner(System.in);
            System.out.println("欢迎光临，请输入第"+(j+1)+"项商品编号：");
            shops[j].setId(sc.next());
            System.out.println("请输入第"+(j+1)+"项商品名称：");
            shops[j].setName(sc.next());
            System.out.println("请输入第"+(j+1)+"项商品价格：");
            shops[j].setPrice(sc.nextInt());
            System.out.println("请输入第"+(j+1)+"项商品剩余数量：");
            shops[j].setRemain(sc.nextInt());
//            shop s1 = new shop("001", "倚天剑", 9999, 1);
//            shop s2 = new shop("002", "屠龙刀", 8888, 1);
//            shop s3 = new shop("003", "九阴白骨爪", 7777, 1);
        }
        double sum=0.0;
        for(int i= 0;i<shops.length; i++){
            shop Shop = shops[i];
            System.out.println(Shop.getId()+" "+Shop.getName()+" "+Shop.getPrice()+" "+Shop.getRemain());
            sum+=Shop.getPrice();
        }
        double avg=sum/shops.length;
        System.out.println("平均价格是："+avg);


    }
}
