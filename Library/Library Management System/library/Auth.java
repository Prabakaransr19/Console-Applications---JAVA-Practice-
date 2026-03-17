package library;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
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
            System.out.println("7.Exit");
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
                    System.out.println("Thankyou for your Service !!!");
                    return;
                default:
                    System.out.println("Enter a valid choice");
            }
        }
    }
    public void borrowerMenu(String email){

    }
}
