package library;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {
    private Book book;
    private Borrower borrower;
    private LocalDate bdate;
    private LocalDate rdate;
    private boolean isReturned;
    public Transaction(Book book , Borrower borrower , LocalDate bdate){
        this.book = book;
        this.borrower = borrower;
        this.bdate = bdate;
        this.isReturned = false;
    }
    public void markAsReturned(){
        setReturned(true);
        setRdate(LocalDate.now());
    }
    public Book getBook() {
        return this.book;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public LocalDate getBdate() {
        return bdate;
    }

    public LocalDate getRdate() {
        return rdate;
    }

    public void setBdate(LocalDate bdate) {
        this.bdate = bdate;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public void setRdate(LocalDate rdate) {
        this.rdate = rdate;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }
}
