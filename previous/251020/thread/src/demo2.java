public class demo2 {
    public static void main(String[] args) {
        Runnable r = new MyRunnable();
        Thread t1 = new Thread(r,"子线程1");
        System.out.println(t1.getName());
        t1.start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                System.out.println("子线程2输出："+i);
            }
        }).start();
        for (int i = 0; i < 5; i++) {
            System.out.println("主线程输出："+i);
        }
        System.out.println(Thread.currentThread().getName());
    }
}
class MyRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("子线程输出："+i);
        }
    }
}