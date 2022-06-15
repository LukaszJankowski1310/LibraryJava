package app;

abstract class LibraryExceptions extends Exception {
}

class InvalidNameFormatException extends LibraryExceptions {
    @Override
    public String toString() {
        return "Podaj dane w formacie: imię nazwisko" ;
    }
}

class InvalidTitleFormatException extends LibraryExceptions {
    @Override
    public String toString() {
        return "Podaj prawidłowy tytuł" ;
    }
}

class InvalidNumberFormatException extends LibraryExceptions {
    @Override
    public String toString() {
        return "Podaj prawidłową liczbę" ;
    }
}

class InvalidStringFormatException extends LibraryExceptions {
    @Override
    public String toString() {
        return "Nie prawidłowy foramt danych" ;
    }
}
