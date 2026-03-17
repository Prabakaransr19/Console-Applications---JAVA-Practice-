package library;

public class Borrower extends User{
    public Borrower(int UserId , String userName , String email , String password){
        setUserId(UserId);
        setUserName(userName);
        setEmail(email);
        setPassword(password);
    }
}
