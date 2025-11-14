package zn.practice1114;

public class Hardware extends Employee{
    public static final String PROJECT_NAME = "维护主板";

    public Hardware(int id, String name) {
        super(id, name);
    }
    @Override
    public void work() {
        System.out.println("工号"+ id + "员工" +name + "正在" + PROJECT_NAME);

    }

}
