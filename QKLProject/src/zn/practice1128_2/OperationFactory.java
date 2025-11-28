package zn.practice1128_2;

public class OperationFactory {
    public static Operation createOperation(String operate){
        if (operate.equals("+")){
            return new Addition();
        }else if (operate.equals("-")){
            return new Subtraction();
        }else if (operate.equals("*")){
            return new Multiplication();
        }else if (operate.equals("/")){
            return new Division();
        }else {
            throw new IllegalArgumentException("没有此运算符");
        }
    }
}
