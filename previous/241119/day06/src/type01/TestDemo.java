package type01;

import java.util.ArrayList;

public class TestDemo {
    private TestDemo(){}


    public static int GetMaxAge(ArrayList<Student> s){
        int max =0;
        for(int i=0;i<s.size();i++){
            if(s.get(i).getAge()>max){
                max=s.get(i).getAge();
            }
        }
        return max;
    }
}
