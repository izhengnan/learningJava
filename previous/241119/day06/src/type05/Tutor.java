package type05;

public class Tutor extends Teacher{
    public Tutor(){}
    public Tutor(String id, String name){
        super(id, name);
    }
    public void work(){
        System.out.println("助教:"+super.getName()+"正在工作");
    }
}
