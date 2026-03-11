import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.*;

public class ATM {
    Scanner sc = new Scanner(System.in);
    private HashMap< Long , Account> accounts = new HashMap<>();
    private long nextAccNo = 1001;
    private double atmBalance = 5_00_000;
    private int dailtTrans = 0;
    private int totTrans = 0;

    public void createAccount(){
        System.out.println("Enter Your name : ");
        String name = sc.next();
        int pin;
        while(true){
            System.out.println("Create your PIN (4-6 digits only) :");
            pin = sc.nextInt();
            if(pin < 1000 || pin > 999999) {
                System.out.println("Enter a valid PIN !!!! ");
                continue;
            }
            break;
        }
        double amt;
        while(true){
            System.out.println("Enter the amount to deposit (minimum $500):");
            amt = sc.nextDouble();
            if(amt<500) {
                System.out.println("Minumum amount should be $500");
                continue;
            }
            break;
        }
        accounts.put(nextAccNo , new Account(name , nextAccNo , pin , amt));
        System.out.println("Account created Successfully !!!");
        accounts.get(nextAccNo).printAccDetails();
        nextAccNo++;
    }
    public void userLogin(){

        System.out.println("Enter your account number : ");
        long accNo = sc.nextLong();
        if(!accounts.containsKey(accNo)) {
            System.out.println("No user found with Account number "+accNo);
            System.out.println("Try Again");
            return;
        }
        System.out.println("Enter your PIN : ");
        int pin = sc.nextInt();
        if(!accounts.get(accNo).verifyPin(pin)){
            System.out.println("Wrong PIN entered");
            System.out.println("Try Again !!!");
        }

        while(true){
            System.out.println("Enter your Action");
            System.out.println("1.Deposit");
            System.out.println("2.Withdrawal");
            System.out.println("3.Change PIN");
            System.out.println("4.Balance check");
            System.out.println("5.Money Transfer");
            System.out.println("6.Print Ministatement");
            System.out.println("7.Print Account details");
            System.out.println("8.Exit");
            int choice = sc.nextInt();
            switch (choice){

                case 1:
                    System.out.println("--------Deposit---------");
                    System.out.println("Enter the no of 500's:");
                    int fhs = sc.nextInt();
                    System.out.println("Enter the no of 200's:");
                    int ths = sc.nextInt();
                    System.out.println("Enter the no of 100's:");
                    int ohs = sc.nextInt();
                    long totalAmount = (fhs*500)+(ths*200)+(ohs*100);
                    System.out.println("Your total amount is "+totalAmount);
                    System.out.println("Press 1 to confirm !!");
                    int confirmation = sc.nextInt();
                    if(confirmation!=1){
                        System.out.println("Try Again later !!");
                        break;
                    }
                    accounts.get(accNo).deposit(totalAmount);
                    System.out.println("Deposit Successfull !!!");
                    accounts.get(accNo).printMiniStatement();
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Enter amount to Withdraw:");
                    int withdrawAmt = sc.nextInt();
                    if(withdrawAmt>atmBalance){
                        System.out.println("Currently Cash not Available in ATM");
                        return;
                    }
                    int bal = withdrawAmt%500;
                    if(bal == 0){
                        accounts.get(accNo).withdraw(withdrawAmt);
                        System.out.println("Withdrawal Successfull !!");
                        atmBalance -= withdrawAmt;
                        accounts.get(accNo).printMiniStatement();
                        break;
                    }
                    bal = bal%200;
                    if(bal == 0){
                        accounts.get(accNo).withdraw(withdrawAmt);
                        System.out.println("Withdrawal Successfull !!");
                        atmBalance -= withdrawAmt;
                        accounts.get(accNo).printMiniStatement();
                        break;
                    }
                    bal = bal%100;
                    if(bal == 0){
                        accounts.get(accNo).withdraw(withdrawAmt);
                        System.out.println("Withdrawal Successfull !!");
                        atmBalance -= withdrawAmt;
                        accounts.get(accNo).printMiniStatement();
                        break;
                    }
                    System.out.println("Enter a Valid Amount (Only 100 , 200 and 500 notes are avilable) \n Try again !!");
                    break;
                case 3:
                    int newpin;
                    while(true){
                        System.out.println("Enter your new PIN (4-6 digits only) :");
                        newpin = sc.nextInt();
                        if(newpin < 1000 || newpin > 999999) {
                            System.out.println("Enter a valid PIN !!!! ");
                            continue;
                        }
                        break;
                    }
                    System.out.println("Enter your account no:");
                    long tempAcNo = sc.nextLong();
                    if(tempAcNo != accounts.get(accNo).getAccountNumber()){
                        System.out.println("Invalid credentials !!");
                        System.out.println("Try Again !!");
                        break;
                    }
                    accounts.get(accNo).changePin(newpin);
                    System.out.println("Pin changed Successfully !!!");
                    break;
                case 4:
                    System.out.println("Your balance : "+accounts.get(accNo).getBalance());
                    break;
                case 5:
                    System.out.println("Enter amount to transfer : ");
                    double transAmt = sc.nextDouble();
                    System.out.println("Enter Account number to transfer : ");
                    long transAccountNo = sc.nextLong();
                    if(!accounts.containsKey(transAccountNo)){
                        System.out.println("No user found with Account no:"+transAccountNo);
                        System.out.println("Enter a Valid Account Number");
                        break;
                    }
                    accounts.get(accNo).withdraw(transAmt);
                    accounts.get(transAccountNo).deposit(transAmt);
                    System.out.println("Transaction Successfull !!");
                    break;
                case 6:
                    accounts.get(accNo).printMiniStatement();
                    break;
                case 7:
                    accounts.get(accNo).printAccDetails();
                    break;
                case 8:
                    return;
                default :
                    System.out.println("Enter a valid choice");
                    break;
            }
        }
    }
    public void adminLogin(){
        Admin admin = new Admin();
        System.out.println("Enter Admin username : ");
        String adminUserName = sc.next();
        System.out.println("Enter Admin Password : ");
        long adminPass = sc.nextLong();
        if(adminPass!=admin.getPin() || !adminUserName.equals(admin.getUserName())){
            System.out.println("Invalid credentials \n Try Again!!!");
            return;
        }
        while(true){
            System.out.println("Select an Action");
            System.out.println("1.Refill cash");
            System.out.println("2.Check Daily transactions");
            System.out.println("3.Check total Transactions");
            System.out.println("4.Exit");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Enter the amount to be filled : ");
                    double fillAmt = sc.nextDouble();
                    atmBalance+=fillAmt;
                    System.out.println("Cash refilled successfully");
                    break;
                case 2:
                    System.out.println("today's transactions :"+dailtTrans);
                    break;
                case 3:
                    System.out.println("Total transactions : "+totTrans);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Enter a valid Choice");
            }
        }
    }
}
