package library;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Auth {
    Scanner sc = new Scanner(System.in);
    Library library = new Library();
     Map< String,User> users = new HashMap<>();
     public User validateUser(){
        System.out.println("Enter your E-mail : ");
        String email = sc.nextLine();
        System.out.println("Enter your password : ");
        String password = sc.nextLine();
        if(users.get(email) == null || !(users.get(email).getPassword()).equals(password)){
            return null;
        }
        return users.get(email);
     }
     // need to check for already entered email in hmap
     int borrowerId = 1001;
    public void createBorrower(){
        System.out.println("Enter username : ");
        String username = sc.nextLine();
        System.out.println("Enter your email : ");
        String email = sc.nextLine();
        System.out.println("Create your password : ");
        String password = sc.nextLine();
        users.put(email , new Borrower(borrowerId++ , username , email , password));
    }
    int adminId = 101;
    public void createAdmin(){
        System.out.println("Enter username : ");
        String username = sc.nextLine();
        System.out.println("Enter your email : ");
        String email = sc.nextLine();
        System.out.println("Create your password : ");
        String password = sc.nextLine();
        users.put(email , new Admin(adminId++ , username , email , password));
    }
    public void createAdmin(String email , String username , String password){
        users.put(email , new Admin(adminId++ , username , email , password));
    }
    public void adminMenu(String email){
        while(true){
            System.out.println("Welcome admin "+ users.get(email).getUserName());
            System.out.println("Enter your choice : ");
            System.out.println("1.Add new Admin");
            System.out.println("2.Add new Borrower");
            System.out.println("3.Add new Book");
            System.out.println("4.View all books in sorted order(a-z)");
            System.out.println("5.View all books in sorted order(Based n quantity)");
            System.out.println("6.Search a book");
            System.out.println("7.Edit a Book details");
            System.out.println("8.Exit");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch){
                case 1:
                    createAdmin();
                    break;
                case 2:
                    createBorrower();
                    break;
                case 3:
                    library.addBook();
                    break;
                case 4:
                    library.sortByTitle();
                    break;
                case 5:
                    library.sortByQty();
                    break;
                case 6:
                    library.searchBook();
                    break;
                case 7:
                    library.editBookDetails();
                    break;
                case 8:
                    System.out.println("Thankyou for your Service !!!");
                    return;
                default:
                    System.out.println("Enter a valid choice");
            }
        }
    }
    public void borrowerMenu(String email){
        while(true){
            System.out.println("Welcome "+users.get(email).getUserName()+" !!");
            System.out.println("Enter your choice ");
            System.out.println("1.View Books");
            System.out.println("2.Rent a Book");
            System.out.println("3.Return a Book");
//            System.out.println("4.Renew a book");
            System.out.println("5.Exit");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch){
                case 1:
                    System.out.println("List of all books avilable !");
                    library.displayAllBooks();
                    break;
                case 2:
                    System.out.println("Welcome to rental portal !!");
                    System.out.println("Avilable books");
                    library.displayAllBooks();
                    List<Book> cart = new ArrayList<>();
                    while(true){
                        System.out.println("Enter the book name you want to select : ");
                        String title = sc.nextLine();
                        Book book = library.findBook(title);

                        if(book==null || cart.contains(book)){
                            System.out.println("Invalid book name (or) same book cannot borrowed twice! try Again");
                            continue;
                        }
                        if(book.getQty()<1){
                            System.out.println("Book not avilable !!");
                            System.out.println("Try different books !!");
                        }
                        cart.add(book);
                        System.out.println("Do you want to add another book (press y to continue)?");
                        char c = sc.next().charAt(0);
                        if(c=='Y' || c=='y'){
                            continue;
                        }
                        break;
                    }
                    if(cart.size() +((Borrower)users.get(email)).getCurrentBorrows() >3){
                        System.out.println("only 3 books allowed per person");
                        return;
                    }
                    for (Book b:cart){
                        // just for now testing purposr manual date is given leter it ll be fixed on
                        library.borrowBook(b);
                        Transaction tra = new Transaction(b , (Borrower) users.get(email) , LocalDate.parse("2026-03-15"));
                        ((Borrower) users.get(email)).transactions.add(tra);
                    }
                    System.out.println("Your order is out now !!");
                    break;
                case 3:
                    System.out.println("Return book ");
                    System.out.println("List of Borrowed Books");
                    ((Borrower)users.get(email)).getCurrentBorrowsList();
                    while(true){
                        System.out.println("enter the book title you want to return");
                        String title = sc.nextLine();
                        Book book = library.findBook(title);
                        if(book==null){
                            System.out.println("Invalid book name try again");
                            continue;
                        }
                        Transaction t = ((Borrower)users.get(email)).findTransaction(book);
                        if(t==null){
                            System.out.println("No Borrow history found with this book !");
                            System.out.println("Try again !");
                            continue;
                        }
                        t.markAsReturned();
                        long daysBetween = ChronoUnit.DAYS.between(t.getBdate(), t.getRdate());
                        double fine = 0;
                        if(daysBetween > 15){
                            fine = (daysBetween-15)*2;
                            System.out.println("Fine for exceeding the Actual Return date : "+fine);
                            System.out.println("Pay your fine to the Librarien !!");
                        }

                        System.out.println("Do you want to return another book (press y to continue)?");
                        char c = sc.next().charAt(0);
                        if(c=='Y' || c=='y'){
                            continue;
                        }
                        break;

                    }
                    break;
                case 4:
                    //renewbook
                    break;
                case 5:
                    System.out.println("Thankyou for Using our library!!!");
                    System.out.println("Keep Reading !!");
                    return;
                default:
                    System.out.println("Enter a valid choice");
            }
        }
    }
}
