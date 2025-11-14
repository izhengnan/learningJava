package zn.practice1114;
public class Demo {
    public static void main(String[] args) {
        JavaKF javaKF = new JavaKF(1001,"郑楠1");
        javaKF.work();

        AndroidFKF androidFKF = new AndroidFKF(1002,"郑楠2");
        androidFKF.work();

        Hardware hardware = new Hardware(1003,"郑楠3");
        hardware.work();

        Network network = new Network(1004,"郑楠4");
        network.work();

    }

}
