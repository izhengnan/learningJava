package type02;

public class extend {
    public static void main(String[] args) {
        hashiqi hq = new hashiqi();
        hq.WatchDoor();
        hq.TearDoor();
        hq.eat();
        hq.sleep();
        System.out.println("------------------------");
        lihuamao lhm = new lihuamao();
        lhm.catchMouse();
        lhm.eat();
        lhm.sleep();
        System.out.println("------------------------");
        buoumao bm = new buoumao();
        bm.catchMouse();
        bm.eat();
        bm.sleep();
        System.out.println("------------------------");
        taidi td = new taidi();
        td.Cenyicen();
        td.WatchDoor();
        td.eat();
        td.sleep();
        System.out.println("------------------------");

    }
}
