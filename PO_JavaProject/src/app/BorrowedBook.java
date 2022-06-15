package app;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BorrowedBook extends Book implements Serializable {

    private final Calendar expiredDate;

    public BorrowedBook(String title, String author, int id, Calendar expiredDate) {
        super(title, author, id);
        this.expiredDate = expiredDate;
    }

    public String expiredDateToString() {
        return  expiredDate.get(Calendar.DAY_OF_MONTH) + "." + (expiredDate.get(Calendar.MONTH) + 1) +  "." + expiredDate.get(Calendar.YEAR);
    }
}
