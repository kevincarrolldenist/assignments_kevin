package org.assignment8;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Book implements Comparable<Book> {
    private int bookId;
    private String title;
    private String author;
    private double price;

    public Book(int bookId, String title, String author, double price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return "Book{id=" + bookId + ", title='" + title + "', author='" + author + "', price=" + price + "}";
    }
}

class BookPriceComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return Double.compare(b1.getPrice(), b2.getPrice());
    }
}

class BookAuthorComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getAuthor().compareTo(b2.getAuthor());
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book(101, "The Great Gatsby", "F. Scott Fitzgerald", 12.50));
        books.add(new Book(102, "1984", "George Orwell", 9.99));
        books.add(new Book(103, "To Kill a Mockingbird", "Harper Lee", 15.75));
        books.add(new Book(104, "Pride and Prejudice", "Jane Austen", 10.25));
        books.add(new Book(105, "Moby Dick", "Herman Melville", 18.00));
        books.add(new Book(106, "Animal Farm", "George Orwell", 8.50));

        System.out.println("--- Original List ---");
        for (Book book : books) {
            System.out.println(book);
        }

        Collections.sort(books);
        System.out.println("\n--- Sorted by Title (Comparable) ---");
        for (Book book : books) {
            System.out.println(book);
        }

        Collections.sort(books, new BookPriceComparator());
        System.out.println("\n--- Sorted by Price (Comparator) ---");
        for (Book book : books) {
            System.out.println(book);
        }

        Collections.sort(books, new BookAuthorComparator());
        System.out.println("\n--- Sorted by Author (Comparator) ---");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
