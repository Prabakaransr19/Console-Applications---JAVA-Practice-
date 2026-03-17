package library;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Auth a = new Auth();
        a.createAdmin("admin123@gmail.com" , "Admin 1" , "Admin@123");
        while(true){
            System.out.println("-----Welcome to Padichutu ponga Library------");
            System.out.println("Select your Action");
            System.out.println("1.Login");
            System.out.println("2.Exit ");
            System.out.println();
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    User user;
                    while(true){
                        System.out.println("Enter your login credentials : ");
                        user = a.validateUser();
                        if(user == null){
                            System.out.println("Invalid credentials \nTry again !!");
                            continue;
                        }
                        break;
                    }
                    if(user instanceof Admin){
                        a.adminMenu(user.getEmail());
                    }else{
                        a.borrowerMenu(user.getEmail());
                    }
                    break;
                case 2:
                    System.out.println("Thankyou for using Padichutu ponga library");
                    System.out.println("Visit us Again!! \nThankyou!!");
                    return;
                default:
                    System.out.println("Enter a valid choice !!!!");
                    break;
            }
        }
    }
}

