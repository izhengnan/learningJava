package zn.practice1128_2;

public class Division implements  Operation{

    @Override
    public int calculate(int a, int b) {
        if (b == 0)
            throw new ArithmeticException("除数不能为0");
        return a / b;
    }
}
