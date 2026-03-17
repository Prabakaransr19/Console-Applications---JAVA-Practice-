package library;

public class Book {
    private int id;
    private String title;
    private String author;
    private int qty;
    private double price;
    // need to implement increase quantity
    //decrease quantity
    //check availability
    public Book(int id ,String title , String author,double price , int qty){
        this.id = id;
        this.author = author;
        this.title = title;
        this.qty = qty;
        this.price = price;
    }
    //getters
    public double getPrice() {
        return price;
    }

    public void increaseQty(int count){
        this.qty = qty+count;
    }

    public int getId() {
        return id;
    }

    public int getQty() {
        return qty;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
    //setters
    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void displayBookDetails(){
        System.out.println("--------Book Detail---------");
        System.out.println("Title : "+this.title);
        System.out.println("Author : "+this.author);
        System.out.println("Price : "+this.price);
        System.out.println("Available Quantity : "+this.qty);
    }

}

