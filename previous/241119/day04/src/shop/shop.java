package shop;

public class shop {
    private String id;
    private String name;
    private int price;
    private int remain;

    public shop(String id,String name,int price,int remain){
        this.id=id;
        this.name=name;
        this.price=price;
        this.remain=remain;
    }
    public shop(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }
}
