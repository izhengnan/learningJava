package type05;

public class Maintainer extends AdminStaff{
    public Maintainer(){}
    public Maintainer(String id, String name){
        super(id, name);
    }
    public void work(){
        System.out.println("维护专员:"+super.getName()+"正在工作");
    }
}
