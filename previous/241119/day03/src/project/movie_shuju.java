package project;

public class movie_shuju {
    private  int id;
    private String name;
    private int price;

    public movie_shuju(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public movie_shuju(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
