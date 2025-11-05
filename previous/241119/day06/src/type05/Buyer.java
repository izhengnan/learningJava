package type05;

public class Buyer extends AdminStaff{
    public Buyer(){}
    public Buyer(String id, String name){
        super(id, name);
    }
    public void work(){
        System.out.println("采购专员:"+super.getName()+"正在工作");
    }
}
