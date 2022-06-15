package app;

import java.io.Serializable;

public class Book implements Serializable {

    private String title;
    private String author;
    private int amount;
    private int id;



    public Book(String title, String author, int amount, int id) {
        this.title = title;
        this.author = author;
        this.amount = amount;
        this.id = id;
    }

    public Book(String title, String author,  int id) {
        this.title = title;
        this.author = author;
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void updateBook(String title, String author, int amount) {
        this.title = title;
        this.author = author;
        this.amount = amount;
    }


}
