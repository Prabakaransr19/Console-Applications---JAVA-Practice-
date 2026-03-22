package library;

import java.util.*;

public class Library {
    Scanner sc = new Scanner(System.in);
    Map<Integer , Book> books = new HashMap<>();
    public void addBook(){
        System.out.println("Enter the id : ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the title : ");
        String title = sc.nextLine();
        System.out.println("Enter the Author : ");
        String author = sc.nextLine();
        System.out.println("Enter the price : ");
        double price = sc.nextDouble();
        System.out.println("Enter the Quantity of the book : ");
        int qty = sc.nextInt();
        sc.nextLine();
        if(books.containsKey(id)){
            System.out.println("This book already exists , you want to increase the quantity (press y to continue):");
            char ch = sc.next().charAt(0);
            if(ch=='y' || ch=='Y'){
                books.get(id).increaseQty(qty);
                System.out.println("The book "+ books.get(id).getTitle() +"'s quantity is increased !!");
                System.out.println("New quantity is : "+ books.get(id).getQty());
            }
            return;
        }else{
            books.put(id , new Book(id , title , author , price , qty));
            System.out.println(books.get(id).getTitle()+"was Added to the library !! ");
        }
    }
    public void displayAllBooks(){
        for(Integer id : books.keySet()){
            books.get(id).displayBookDetails();
        }
    }
    public void borrowBook(Book book){
        book.setQty(book.getQty()-1);
    }
    public void editBookDetails(){
        System.out.println("Enter the book id to edit : ");
        int id = sc.nextInt();
        while(true){
            System.out.println("Choose what to change !!");
            System.out.println("1.Title");
            System.out.println("2.Author name");
            System.out.println("3.Price");
            System.out.println("4.Exit");
            int ch = sc.nextInt();
            switch(ch){
                case 1:
                    System.out.println("Enter new title to change : ");
                    String title = sc.nextLine();
                    books.get(id).setTitle(title);
                    System.out.println("Title changed successfully !!");
                    break;
                case 2:
                    System.out.println("Enter new Author name : ");
                    String name  = sc.nextLine();
                    books.get(id).setAuthor(name);
                    System.out.println("Author name changed successfully !!");
                    break;
                case 3:
                    System.out.println("Enter new price : ");
                    double price = sc.nextDouble();
                    books.get(id).setPrice(price);
                    System.out.println("Price changed success fully !!");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Enter a valid choice !!!");
                    break;
            }
            break;
        }
    }
    public void searchBook(){
        System.out.println("Enter a choice to search :");
        System.out.println("1.Search by ID ");
        System.out.println("2.Search by Title");
        System.out.println("3.Exit");
        int ch = sc.nextInt();
        sc.nextLine();
        switch (ch){
            case 1:
                System.out.println("Enter book id : ");
                int bid = sc.nextInt();
                sc.nextLine();
                if(books.get(bid)==null){
                    System.out.println("No book found with given ID");
                    return;
                }
                books.get(bid).displayBookDetails();
                break;
            case 2:
                System.out.println("Enter the book title : ");
                String title = sc.nextLine();
                for(Integer id : books.keySet()){
                    if((books.get(id).getTitle()).equals(title)){
                        books.get(id).displayBookDetails();
                    }
                }
            case 3:
                return;
            default :
                System.out.println("Enter a valid choice");
                break;
        }
    }
    public Book findBook(String title){
        for(Integer id : books.keySet()){
            if((books.get(id).getTitle()).equals(title)){
                return books.get(id);
            }
        }
        return null;
    }
    public void sortByTitle(){
        List<String> list = new ArrayList<>();
        for(Integer id : books.keySet()){
            list.add(books.get(id).getTitle());
        }
        Collections.sort(list);
        int counter=1;
        System.out.println("Books list Sorted(A-Z):");
        for(String str : list){
            System.out.println(counter++ +"."+ str);
        }
    }
    public void sortByQty(){
        TreeMap<Integer , String> map = new TreeMap<>();
        for(Integer id : books.keySet()){
            map.put(books.get(id).getQty() , books.get(id).getTitle());
        }
        int counter = 1;
        System.out.println("Books Sorted by Quantity:");
        for(Map.Entry<Integer , String> m : map.entrySet()){
            System.out.println(counter++ +"."+m.getValue() +" : "+m.getKey());
        }
    }
}

