package type05;

public class Main {
    public static void main(String[] args) {
        Lecturer l = new Lecturer("002","李四");
        l.work();
        Tutor t = new Tutor("003","王五");
        t.work();
        Maintainer m = new Maintainer("004","赵六");
        m.work();
        Buyer b = new Buyer("005","小王");
        b.work();
    }
}
