package atm;
public class Account {
    private String userName;
    private long accountNumber;
    private int pin;
    private double balance;
    private String[] transHistory;
    private int tIdx;

    public Account(String userName , long accountNumber , int pin , double balance){
        this.userName = userName;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.transHistory = new String[5];
        this.tIdx = 0;
        addTranscation("Deposit" , balance);
    }

    public void printAccDetails(){
        System.out.println("-----------Account Details----------------");
        System.out.println("Name : "+this.userName);
        System.out.println("Account number : "+this.accountNumber);
        System.out.println("Current balance : "+this.balance);
    }
    public double getBalance(){
        return this.balance;
    }

    public String getUserName(){
        return this.userName;
    }

    public long getAccountNumber(){
        return this.accountNumber;
    }

    public boolean verifyPin(int pin){
        return this.pin == pin;
    }

    public void addTranscation(String type, double amt){
        int idx = (tIdx%5);
        transHistory[idx] = type+" "+amt;
        tIdx++;
    }

    public boolean deposit(double amt){
        if(amt<0) return false;
        this.balance += amt;
        addTranscation("Deposit" , amt);
        return true;
    }

    public boolean withdraw(double amt){
        if(this.balance < amt) return false;
        this.balance -= amt;
        addTranscation("Withdrawal" , amt);
        return true;
    }

    public void changePin(int newPin){
        this.pin = newPin;
    }

    public void printMiniStatement(){
        System.out.println("------------Mini Statement--------------");
        if(tIdx==0){
            System.out.println("No transactions happened Yet");
            return;
        }
        System.out.println("Name : " + this.userName);
        int idx = ((tIdx-1)%5);
        System.out.println("Your transaction of " + transHistory[idx] + " was successfull !!!!!");
        System.out.println("Your current balance is "+this.balance);
        System.out.println("Thankyou!");
        System.out.println("Visit us again !!");
    }
}