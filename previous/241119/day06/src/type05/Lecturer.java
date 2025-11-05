package type05;

public class Lecturer extends Teacher{
    public Lecturer(){}
    public Lecturer(String id, String name){
        super(id, name);
    }
    public void work(){
        System.out.println("讲师:"+super.getName()+"正在工作");
    }
}
