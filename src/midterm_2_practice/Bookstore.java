package midterm_2_practice;

import java.util.ArrayList;

class Book {
    private String title;
    private String author;
    private double price;
    private int numPages;

    public Book(String title, String author, double price, int numPages) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.numPages = numPages;
    }
    public Book(String title, String author, int numPages) {
        this.title = title;
        this.author = author;
        this.setPrice(9.99);
        this.numPages = numPages;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }
    public int getNumPAges() { return numPages; }
    void setPrice(double price) { this.price = price; }
}

class BorrowableBook  extends Book {
    private int daysLeft;
    private String borrowedBy;

    public BorrowableBook(String title, String author, int numPages) { 
        // the specific form is not specified in the problem, you can choose to set extra fields
        super(title,author,0.0,numPages);
    }

    public String getBorrowedBy() { return borrowedBy; }
    public int getdaysLeft() { return daysLeft; }
    void setDaysLeft(int daysLeft) { this.daysLeft = daysLeft; }
    void setBorrowedBy(String borrowedBy) { this.borrowedBy = borrowedBy; }

    public boolean isLate() {
        return daysLeft < 0;
    }
}

class Inventory {
    private double cash = 0;
    private ArrayList<Book> books;

    public Inventory() {
        this.cash = 0;
        books = new ArrayList<>();
    }
    public double getCash() { return cash; } // not asked for in the problem

    public void addBook(Book b) {
        books.add(b);
    }

    public void buyBook(Book b) {
        cash -= 0.8 * b.getPrice();
        addBook(b);
    }

    public Book sellBook(String title, String customer) {
        // it was not stated in the problem to return the book itself, so a 'void' method is also fine

        for(Book b: books) {
            if(title.equals(b.getTitle())) {

                if(b instanceof BorrowableBook) {
                    // set due date to 2 weeks
                    BorrowableBook bb = (BorrowableBook) b;
                    bb.setDaysLeft(14);
                    books.remove(b);
                    return b;
                } else {
                    cash += b.getPrice();
                    books.remove(b);
                    return b;
                }
            }
        }
        return null;
    }
}

public class Bookstore { // not asked for in the problem

    public static void main(String[] args) {
        Inventory inv = new Inventory();
        inv.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", 12.99, 180));
        inv.addBook(new BorrowableBook("Faust", "Johann Wolfgang von Goethe", 158));
        inv.buyBook(new Book("The Picture of Dorian Gray", "Oscar Wilde", 8.99, 250));
        inv.sellBook("The Great Gatsby", "Alice");
        inv.sellBook("Faust", "Bob");
        inv.sellBook("The Picture of Dorian Gray", "Charlie");

        System.out.printf("Cash: %.2f\n", inv.getCash());

    }
    
}
