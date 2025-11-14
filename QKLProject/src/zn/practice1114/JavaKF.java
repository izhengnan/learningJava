package zn.practice1114;

public class JavaKF extends Development{
    public static final String PROJECT_NAME = "开发电商平台网页端";


    @Override
    public void work() {
        System.out.println("工号"+ id + "员工" +name + "正在" + PROJECT_NAME);

    }


    public JavaKF(int i, String name) {
        super(i, name);
    }
}
