package FileReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class demo {
    public static void main(String[] arg){
        try (Reader rd = new FileReader("src/test1.txt");){
            char[] buffer =new char[4];
            int len;
            while((len=rd.read(buffer))!=-1){
                String s = new String(buffer,0,len);
                System.out.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
