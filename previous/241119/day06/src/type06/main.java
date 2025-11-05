package type06;

public class main {
    public static void main(String[] args) {
        person p1=new person("老王",30);
        person p2 = new person("老李",25);
        animal a = new dog(2,"黑");
        animal b = new cat(3,"白");
        p1.keepPet(a,"骨头");
        p2.keepPet(b,"鱼");
    }
}
