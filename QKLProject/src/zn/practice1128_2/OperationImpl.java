package zn.practice1128_2;



public class OperationImpl {
    OperationFactory operationFactory = new OperationFactory();
    public int calculate(String operate,int a,int b){
        Operation operation = OperationFactory.createOperation(operate);
        return operation.calculate(a,b);
    }
}
