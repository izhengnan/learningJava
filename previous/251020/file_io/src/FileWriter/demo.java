package FileWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

public class demo {
    public static void main(String[] args) {
        write("src/test1.txt","src/test2.txt");
    }
    public static void write(String file1,String file2){
        try(Writer wr =new FileWriter(file2);
            Reader rr =new FileReader(file1);
        ){
            char[] buffer = new char[5];
            int len;
            while((len=rr.read(buffer))!=-1){
                wr.write(buffer,0,len);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
