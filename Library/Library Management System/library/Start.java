package library;
import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("-----Welcome to Padichutu ponga Library------");
            System.out.println("Select your Action");
            System.out.println("1.Admin login");
            System.out.println("2.User Login");
            System.out.println("3.Exit ");
            System.out.println();
            int choice = sc.nextInt();
            switch (choice){
                case 1:
//                    Library.adminLogin();
                    break;
                case 2:
//                    Library.userLogin();
                    break;
                case 3:
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

