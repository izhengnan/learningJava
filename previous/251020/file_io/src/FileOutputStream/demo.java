package FileOutputStream;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class demo {
    public static void main(String[] arg)throws Exception{
//        OutputStream os =new FileOutputStream("src/test1.txt");
        OutputStream os =new FileOutputStream("src/test1.txt",true);//追加写
        os.write('A');
        os.write('2');

        byte[] b =("我爱你66").getBytes();
        os.write(b);


        os.close();
    }
}
