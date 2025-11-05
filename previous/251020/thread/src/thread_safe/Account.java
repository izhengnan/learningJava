package thread_safe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String card_id;
    private double money;

    public void pay_money(double money){
        String name =Thread.currentThread().getName();
        synchronized (this) {
            if(this.money>=money&&money!=0){
                this.money-=money;
                System.out.println(name+"取钱成功,取走了"+money+"余额还有："+this.money);
            }
            else{
                System.out.println("余额不足，取钱失败");
            }
        }
    }
}
