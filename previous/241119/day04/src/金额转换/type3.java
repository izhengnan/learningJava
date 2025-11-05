package 金额转换;

public class type3
{
    public static void main(String[] args) {
        String sfz="350322200111093010";
        String year=sfz.substring(6,10);
        String month=sfz.substring(10,12);
        String day=sfz.substring(12,14);
        char sex=sfz.charAt(16);
        String sex_2=(int)sex%2==0?"女":"男";
        System.out.println("出生日期："+year+"年"+month+"月"+day+"日"+"性别："+sex_2);
    }
}
