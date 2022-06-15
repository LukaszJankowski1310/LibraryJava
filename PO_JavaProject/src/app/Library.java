package app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Library implements Serializable {

    public  ArrayList <User> users;
    public ArrayList <Book> books;
    private int idNextBook;
    private int idNextUser;


    private int loggedUser;


    public Library() {
        users = new ArrayList<>();
        books = new ArrayList<>();
        this.idNextBook = 0;
        this.idNextUser = 0;
        this.loggedUser = -1;
    }

    public void addUser(User u) {
        users.add(u);
        this.idNextUser++;

    }

    public void addBook(Book b) {
        books.add(b);
        this.idNextBook++;
    }

    public void addBook2(Book b) {
        books.add(b);
      //  this.idNextBook++;
    }


    public void deleteUser(int index) {
        this.users.remove(index);
    }

    public void deleteBook(int index) {
        this.books.remove(index);
    }


    public User getUser(int id) {
        if(this.checkUserExist(id)) {
            int k = -1;
            for (int i = 0; i < this.users.size(); i++) {
                if (this.users.get(i).getId() == id) {
                    k = i;
                    break;
                }
            }
            return this.users.get(k);
        }
        return null;
    }

    public Book getBook(int id) {
        if(this.checkBookExist(id)) {
            int k = -1;
            for (int i = 0; i < this.books.size(); i++) {
                if (this.books.get(i).getId() == id) {
                    k = i;
                    break;
                }
            }
            return this.books.get(k);
        }
        return null;
    }



    public boolean checkUserExist(int id) {
        AtomicBoolean isUser = new AtomicBoolean(false);
        this.users.forEach(user -> {
            if (user.getId() == id) {
                isUser.set(true);

            }
        });

        return isUser.get();
    }

    public boolean checkBookExist(int id) {
        AtomicBoolean isBook = new AtomicBoolean(false);
        this.books.forEach(book -> {
            if (book.getId() == id) {
                isBook.set(true);

            }
        });

        return isBook.get();
    }


    public int getIdNextBook() {
        return idNextBook;
    }

    public void setIdNextBook(int idNextBook) {
        this.idNextBook = idNextBook;
    }


    public int getIdNextUser() {
        return idNextUser;
    }

    public void setIdNextUser(int idNextUser) {
        this.idNextUser = idNextUser;
    }


    public int getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(int loggedUser) {
        this.loggedUser = loggedUser;
    }

}
