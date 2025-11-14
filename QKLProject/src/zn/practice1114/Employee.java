package zn.practice1114;


public abstract class Employee {
    Integer id;
    String name;

    public abstract void work();

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
