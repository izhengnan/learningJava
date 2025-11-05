package commons_io;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class demo {
    public static void main(String[] args)throws Exception{
        FileUtils.copyFile(new File("src/test.txt"),new File("src/test3.txt"));
    }
}
