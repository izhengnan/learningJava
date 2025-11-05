package shuangseqiu;
import java.util.Random;
import java.util.Scanner;
public class type1 {//双色球游戏
    public static void main(String[] args) {
        int red[]=DefineResult(33,6);
        int blue[]=DefineResult(16,1);
        guess(red,blue);
    }
    public static int[] DefineResult(int num,int num2){
        Random r = new Random();
        int arr[]=new int[num2];
        for (int i = 0; i < arr.length;i++) {
            int guess;
            do{guess = r.nextInt(num) + 1;}
            while (contains(arr,guess,i));
//            System.out.println("生成的随机数: " + guess); // 调试输出
            arr[i] = guess;
        }
        return arr;
    }
    public static void guess(int red[],int blue[]){
        System.out.println("==输入红色球号码==(不要重复)");
        Scanner sc = new Scanner(System.in);
        int guess[]=new int[6];
        for(int i=0;i<guess.length;){
            do{
                System.out.print("输入1-33的号码(第"+(i+1)+"项)：");
                guess[i]=sc.nextInt();
            }while(contains(guess,guess[i],i));
            if(guess[i]>0&&guess[i]<=33) {
                i++;
            }else{
                System.out.print("输入有误，请");
            }
        }
        System.out.println("你猜的红色球号码为：");
        print(guess);
        System.out.print("请输入你猜的蓝色球号码：");
        int GuessBlue=sc.nextInt();
        while(GuessBlue<1||GuessBlue>16){
            System.out.print("输入有误，请重新输入1-16的号码：");
            GuessBlue=sc.nextInt();
        }
        int countRed =0;
        for(int k=0;k<red.length;k++){
            for(int j=0;j<guess.length;j++){
                if(red[k]==guess[j]){
                    countRed++;
                }
            }
        }
        int countBlue =0;
        if(GuessBlue==blue[0]){countBlue++;}
        if(countBlue==1&&countRed == 6){System.out.println("恭喜你，你中了一等奖！");}
        else if(countBlue==0&&countRed == 6){System.out.println("恭喜你，你中了二等奖！");}
        else if((countBlue==1&&countRed == 5)){System.out.println("恭喜你，你中了三等奖！");}
        else if((countBlue==1&&countRed == 4)||(countBlue==0&&countRed == 5)){System.out.println("恭喜你，你中了四等奖！");}
        else if((countBlue==0&&countRed == 4)||(countBlue==1&&countRed == 3)){System.out.println("恭喜你，你中了五等奖！");}
        else if((countBlue==1&&countRed == 0)||(countBlue==1&&countRed == 1)||(countBlue==1&&countRed == 2)){System.out.println("恭喜你，你中了六等奖！");}
        else{System.out.println("很遗憾，你没有中奖");}
        System.out.print("开奖红色球号码为：");
        print(red);
        System.out.print("\n开奖蓝色球号码为：");
        print(blue);
    }
    public static void print(int guess[]){
        for(int i=0;i<guess.length;i++){
            System.out.print(guess[i]+" ");
        }
    }
    public static boolean contains(int arr[],int num,int limit){
        for(int i=0;i<limit;i++){
            if(arr[i]==num){
                return true;
            }
        }
        return false;
    }
}