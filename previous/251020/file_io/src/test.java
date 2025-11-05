import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class test {
    public static void main(String[] arg)throws Exception{
        InputStream is =new FileInputStream("src/test1.txt");
        OutputStream os = new FileOutputStream("src/test2.txt");
        byte[] b = is.readAllBytes();
        os.write(b);
        is.close();
        os.close();
    }
}
