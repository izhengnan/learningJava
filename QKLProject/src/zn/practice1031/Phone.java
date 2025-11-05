package zn.practice1031;

public class Phone {
    private String brand;
    private double price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void showInfo(){
        System.out.println("手机品牌是："+this.brand+"，价格是："+this.price);
    }
}
