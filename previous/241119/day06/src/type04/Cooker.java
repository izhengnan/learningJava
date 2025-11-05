package type04;

public class Cooker extends person{

    public Cooker(){
        super();
    }
    public Cooker(String id,String name,int pay){
        super(id,name,pay);
    }
    public void work(){
        System.out.println(super.getName()+"在做菜");
    }
}
