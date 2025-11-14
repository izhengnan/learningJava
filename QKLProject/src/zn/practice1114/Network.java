package zn.practice1114;

public class Network extends Employee{
    public static final String PROJECT_NAME = "维护网络防火墙";

    public Network(int i, String name) {
        super(i, name);
    }
    @Override
    public void work() {
        System.out.println("工号"+ id + "员工" +name + "正在" + PROJECT_NAME);

    }

}
