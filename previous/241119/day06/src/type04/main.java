package type04;

public class main {
    public static void main(String[] args) {
        Cooker c1=new Cooker("001","小王",5000);
        c1.work();
        c1.eat();
        manage m1=new manage("002","小李",6000,3000);
        m1.work();
        m1.eat();
    }
}
