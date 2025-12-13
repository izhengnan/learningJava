package zn.practice1212;

import java.io.File;

public class test {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\16048\\Pictures\\1.jpg");
        System.out.println(file.exists());
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
        System.out.println(file.isAbsolute());
        System.out.println(file.canRead());
        System.out.println(file.canWrite());
    }
}
