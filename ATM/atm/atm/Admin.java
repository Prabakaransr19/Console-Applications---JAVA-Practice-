package atm;
public class Admin {
    final private String userName = "Admin";
    final private long pin = 1234;
    public long getPin(){
        return this.pin;
    }
    public String getUserName() {
        return userName;
    }
}