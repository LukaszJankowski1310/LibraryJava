package app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class User implements Serializable {

    private String name;
    private String surname;
    private int id;
    private int numberBorrowedBooks;
    public ArrayList<BorrowedBook> borrowedBooks;

    public User(String name, String surname, int id) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.numberBorrowedBooks = 0;
        this.borrowedBooks = new ArrayList<>();


    }

    public int borrowBook(Library lib, int id) {
            Book b = lib.getBook(id);
            Calendar expiredDate = new GregorianCalendar();
            expiredDate.add(Calendar.DATE, 10);

            if (b.getAmount() > 0) {
                b.setAmount(b.getAmount() - 1);
                BorrowedBook borrowedBook = new BorrowedBook(b.getTitle(), b.getAuthor(), b.getId(), expiredDate);
                borrowedBooks.add(borrowedBook);
                this.numberBorrowedBooks++;
                return 0;
            }
            else return -1;
    }

    public void returnBook(Library lib, int id, int index) {
        BorrowedBook borBook = borrowedBooks.get(index);

        this.borrowedBooks.remove(index);
        if (lib.checkBookExist(id)) {
            Book bInLib = lib.getBook(id);
            bInLib.setAmount(bInLib.getAmount()+1);
        } else {
            Book b = new Book(borBook.getTitle(), borBook.getAuthor(), 1, id);
            lib.addBook2(b);
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getId() {
        return id;
    }

    public int getNumberBorrowedBooks() {
        return numberBorrowedBooks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void updateUser(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

}
