public class demo1 {
    public static void main(String[] args) {
        Thread t1 =new MyThread();
        t1.start();
        for (int i = 0; i < 999; i++) {
            System.out.println("主线程输出："+i);
        }
    }
}

class MyThread extends Thread{
    public void run(){
        for (int i = 0; i < 999; i++) {
            System.out.println("子线程输出："+i);
        }
    }
}

