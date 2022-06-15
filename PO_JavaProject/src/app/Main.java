package app;

import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
     public static void main(String[] args) {
         // wczytnie pliku //

         FileDataSource file = new FileDataSource("./data.txt");

         Library library = file.readFromFile();
         SwingUtilities.invokeLater(new Runnable() {
             @Override
             public void run() {
                 new AppFrame(library, "./data.txt");
             }
         });
    }

}
