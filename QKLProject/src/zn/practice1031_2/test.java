package zn.practice1031_2;

public class test {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("郑楠");
        student.setStudentId("2406030354");
        student.setBirthday(new MyDate());
        student.getBirthday().setDay("31");
        student.getBirthday().setMonth("10");
        student.getBirthday().setYear("2025");
        student.showInfo();
    }
}
