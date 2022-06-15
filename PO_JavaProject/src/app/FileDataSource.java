package app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileDataSource {

    final private String path;

    public FileDataSource(String path) {
        this.path = path;
    }


    void saveToFile(Library lib) {
        try {
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(path));
            o.writeObject(lib);
            o.close();
            System.out.println("Zapisanie do pliku się udalo");
        }
        catch (Exception ex)
        {
            System.out.println("Zapisanie do pliku się nie udalo");
        }
    }

    Library readFromFile() {
        Library lib;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
            lib = (Library) in.readObject();
            in.close();
            System.out.println("Wczytanie pliku się udalo");
            return lib;
        } catch(Exception ex) {
            System.out.println("Wczytanie pliku się nie udalo");
            return  new Library();
        }

    }
}
