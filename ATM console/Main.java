import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("-----Welcome to kaasu podunga ATM------");
            System.out.println("Select your Action");
            System.out.println("1.New User");
            System.out.println("2.User Login");
            System.out.println("3.Admin ");
            System.out.println("4.Exit ");
            System.out.println();
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    atm.createAccount();
                    break;
                case 2:
                    atm.userLogin();
                    break;
                case 3:
                    atm.adminLogin();
                    break;
                case 4:
                    System.out.println("Thank you for using kaasu podunga ATM !!! \n Visit Us Again ");
                    return;
                default:
                    System.out.println("Enter a valid choice !!!!!!!!!!!");
            }
        }
    }
}