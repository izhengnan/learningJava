package type1;

public class main {
    public static void main(String[] args) {
        for(int i=10;i<Math.pow(10,2);i++){
            int a=i%10;
            int b=i/10;
            if(Math.pow(a,3)+Math.pow(b,3)==i){
                System.out.println(i);
            }
        }
        sxh();
        symg();
        wjxs();
    }

    public static void sxh(){
        for(int i=100;i<Math.pow(10,3);i++){
            int a=i%10;
            int b=(i/10)%10;
            int c=i/100;
            if(Math.pow(a,3)+Math.pow(b,3)+Math.pow(c,3)==i){
                System.out.println(i);
            }
        }
    }

    public static void symg(){
        for(int i=1000;i<Math.pow(10,4);i++){
            int a=i%10;
            int b=(i/10)%10;
            int c=(i/100)%10;
            int d=i/1000;
            if(Math.pow(a,4)+Math.pow(b,4)+Math.pow(c,4)+Math.pow(d,4)==i){
                System.out.println(i);
            }
        }
    }

    public static void wjxs(){
        for(int i=10000;i<Math.pow(10,5);i++){
            int a=i%10;
            int b=(i/10)%10;
            int c=(i/100)%10;
            int d=(i/1000)%10;
            int e=i/10000;
            if(Math.pow(a,5)+Math.pow(b,5)+Math.pow(c,5)+Math.pow(d,5)+Math.pow(e,5)==i){
                System.out.println(i);
            }
        }
    }
}


