package org.example;

import service.Libreria;
import utility.isbnvalidator;
import utility.isbnvalidator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String isbn1 = "9780140449136";  // l'ISBN valido
        System.out.println("Controllo validità isbn1: "+ isbnvalidator.isvalid(isbn1) );
    }
}