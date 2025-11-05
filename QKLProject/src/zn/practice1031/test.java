package zn.practice1031;

public class test {
    public static void main(String[] args) {
        Phone p = new Phone();
        p.setBrand("小米");
        p.setPrice(1999);
//        System.out.println("手机品牌是："+p.getBrand()+"，价格是："+p.getPrice());
        p.showInfo();
    }
}
