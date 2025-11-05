package FileInputStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class demo {
    public static void main(String[] args) throws Exception{
        InputStream is =new FileInputStream("src/test.txt");
//        byte[] b = is.readAllBytes();
//        String s =new String(b);
//        System.out.print(s);
        byte[] b = new byte[3];
        int i;
        while((i =is.read(b))!=-1){
            String s =new String(b,0,i);
            System.out.print(s);
        }

        is.close();
    }
}
