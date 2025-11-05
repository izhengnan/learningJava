package type04;

public class person {
    private String id;
    private String name;
    private int pay;

    public person()
    {
    }
    public person(String id,String name,int pay)
    {
        this.id = id;
        this.name = name;
        this.pay = pay;
    }

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

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public void eat(){
        System.out.println(this.name+"在吃饭");
    }
    public void work(){
        System.out.println(this.name+"在工作");
    }

}
