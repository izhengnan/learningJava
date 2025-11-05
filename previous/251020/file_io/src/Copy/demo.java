package Copy;

import java.io.*;

public class demo {
    public static void main(String[] msg){
        copy("src/test.jpg","src/test_copy.jpg");
    }
    public static void copy(String file1,String file2){
        try (InputStream is = new FileInputStream(file1);
             OutputStream os = new FileOutputStream(file2)) {
//        byte[] b =is.readAllBytes();
//        os.write(b);
            byte[] b = new byte[1024];
            int len;
            while((len=is.read(b))!=-1){
                os.write(b,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
