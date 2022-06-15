package app;

public class Validator {

    static String validateAuthor(String s) throws InvalidNameFormatException {

        String[] strArr = s.split(" ");

        if (s.equals(" ") ||
                strArr.length != 2 ||
                !strArr[0].matches("[a-zA-Z]+") ||
                !strArr[1].matches("[a-zA-Z]+")) {

            throw new InvalidNameFormatException();

        }
        //   System.out.println("Działa");
        return s;

    }

    static String validateTitle(String s) throws InvalidTitleFormatException {
        String[] strArr = s.split(" ");

        if (s.length() < 2) {
            throw new InvalidTitleFormatException();
        }

        for (String s1 : strArr) {
            if (!s1.matches("[a-zA-Z]+")) {
                throw new InvalidTitleFormatException();
            }
            ;
        }
        return s;
    }

    static Integer validateAmount(String strNumber) throws InvalidNumberFormatException {

        Integer intNumber;
        try {
            intNumber = Integer.parseInt(strNumber);
            if (intNumber < 0) {
                throw new InvalidNumberFormatException();
            }

        } catch (NumberFormatException e) {
            throw new InvalidNumberFormatException();
        }

        return intNumber;
    }


    static String validateString(String s) throws InvalidStringFormatException {

        String[] strArr = s.split(" ");

        if (s.equals(" ") ||
                strArr.length != 1 ||
                !strArr[0].matches("[a-zA-Z]+") )
                 {

            throw new InvalidStringFormatException();

        }
        //   System.out.println("Działa");
        return s;
    }



}