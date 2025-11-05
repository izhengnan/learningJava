package student_system;

public class User {
    private String UserName;
    private String Password;
    private String sfzNumber;
    private String number;

    public User() {}
    public User(String UserName,String Password,String sfzNumber,String number) {
        this.UserName=UserName;
        this.Password=Password;
        this.sfzNumber=sfzNumber;
        this.number=number;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSfzNumber() {
        return sfzNumber;
    }

    public void setSfzNumber(String sfzNumber) {
        this.sfzNumber = sfzNumber;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
