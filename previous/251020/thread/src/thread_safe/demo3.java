package thread_safe;

public class demo3 {
    public static void main(String[] args) {
        Account acc =new Account("ICBC-888",100000);
        new Thread(new MyRunnable(acc),"小明").start();
        new Thread(new MyRunnable(acc),"小红").start();
    }
}
class MyRunnable implements Runnable{
    private Account acc;
    public MyRunnable(Account acc) {
        this.acc =acc;
    }

    public void run(){
        acc.pay_money(acc.getMoney());
    }
}
//用继承Thread的方法重写这段代码
//public class demo3 {
//    public static void main(String[] args) {
//        Account acc =new Account("ICBC-888",100000);
//        new MyThread("小明",acc).start();
//        new MyThread("小红",acc).start();
//
//    }
//}
//
//class MyThread extends Thread{
//    private Account acc;
//    public MyThread(String name,Account acc) {
//        super(name);
//        this.acc =acc;
//    }
//
//    public void run(){
//        acc.pay_money(acc.getMoney());
//    }
//}
