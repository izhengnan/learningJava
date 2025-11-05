package type04;

public class manage extends person{
    private int Awardpay;

    public manage(){
        super();
    }
    public manage(String id,String name,int pay,int Awardpay){
        super(id,name,pay);
        this.Awardpay=Awardpay;
    }

    public int getAwardpay() {
        return Awardpay;
    }

    public void setAwardpay(int awardpay) {
        Awardpay = awardpay;
    }

    public void work(){
        System.out.println(super.getName()+"在管理其他人");
    }
}
