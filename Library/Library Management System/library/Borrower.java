package library;
import java.util.*;

public class Borrower extends User{
    List<Transaction> transactions = new ArrayList<>();
    public Borrower(int UserId , String userName , String email , String password){
        setUserId(UserId);
        setUserName(userName);
        setEmail(email);
        setPassword(password);
    }
    public Transaction findTransaction(Book book){
        for(Transaction t:transactions){
            if(t.getBook() == book && t.isReturned()==false){
                return t;
            }
        }
        return null;
    }
    public int getCurrentBorrows(){
        int count = 0;
        for(Transaction t : transactions){
            if(t.isReturned()==false){
                count++;
            }
        }
        return count;
    }
    public void getCurrentBorrowsList(){
        for (Transaction t:transactions){
            if(t.isReturned()==false){
                System.out.println(t.getBook().getTitle() + ":" + t.getBook().getId());
            }
        }
    }
}
