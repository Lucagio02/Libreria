package utility;
public class isbnvalidator{
public static boolean isvalid(String isbn) {
    if (isbn == null || isbn.length() != 13 || !isbn.matches("\\d+")) return false;

    int sum = 0;
    for (int i = 0; i < 12; i++) {
        int digit = Character.getNumericValue(isbn.charAt(i));
        sum += (i % 2 == 0) ? digit : digit * 3;
    }

    int checkDigit = (10 - (sum % 10)) % 10;
    return checkDigit == Character.getNumericValue(isbn.charAt(12));
}
}